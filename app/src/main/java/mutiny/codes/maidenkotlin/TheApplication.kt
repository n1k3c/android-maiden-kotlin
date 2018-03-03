package mutiny.codes.maidenkotlin

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.facebook.stetho.Stetho
import mutiny.codes.maidenkotlin.di.components.AppComponent
import mutiny.codes.maidenkotlin.di.components.DaggerAppComponent
import timber.log.Timber

/**
 * Created by nikola on 5/24/17.
 */

class TheApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())
        Kotpref.init(this)
        instance = this
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: TheApplication

        lateinit var appComponent: AppComponent
    }
}