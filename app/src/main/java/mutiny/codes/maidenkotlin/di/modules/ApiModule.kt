package mutiny.codes.maidenkotlin.di.modules

import android.content.Context
import mutiny.codes.maidenkotlin.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.ajalt.timberkt.d
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import mutiny.codes.maidenkotlin.network.ApiService
import mutiny.codes.maidenkotlin.util.CheckInternetConnection
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by nikola on 5/24/17.
 */

const val DISK_CACHE_SIZE: Long = 10 * 1024 * 1024

const val CONNECTION_TIMEOUT: Long = 3

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient = createOkHttpClient(context)

    private fun createOkHttpClient(context: Context): OkHttpClient {
        val cacheDir = File(context.cacheDir, "http")
        val cache = Cache(cacheDir, DISK_CACHE_SIZE)
        return OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(StethoInterceptor())
                .addNetworkInterceptor(rewriteOnlineResponseInterceptor())
                .addInterceptor(rewriteOfflineResponseInterceptor(context))
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BuildConfig.API_KEY)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create<ApiService>(ApiService::class.java)

    private fun rewriteOnlineResponseInterceptor() = Interceptor { chain ->
        val originalResponse = chain.proceed(chain.request())
        val cacheControl = originalResponse.header("Cache-Control")
        if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
            originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + 5000)
                    .build()
        } else {
            originalResponse
        }
    }

    private fun rewriteOfflineResponseInterceptor(context: Context) = Interceptor { chain ->
        var request = chain.request()
        if (!CheckInternetConnection().isNetworkAvailable(context)) {
            d { "This is offline request" }
            request = request.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached")
                    .build()
        } else {
            d { "This is online request" }
        }
        chain.proceed(request)
    }
}