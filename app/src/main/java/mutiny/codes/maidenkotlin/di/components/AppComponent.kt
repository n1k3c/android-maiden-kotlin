package mutiny.codes.maidenkotlin.di.components

import dagger.Component
import mutiny.codes.maidenkotlin.di.modules.ApiModule
import mutiny.codes.maidenkotlin.di.modules.ApiService
import mutiny.codes.maidenkotlin.di.modules.AppContextModule
import mutiny.codes.maidenkotlin.di.modules.MainModule
import javax.inject.Singleton


/**
 * Created by nikola on 5/24/17.
 */

@Component(modules = arrayOf(AppContextModule::class, ApiModule::class))
@Singleton
interface AppComponent {

    fun plus(module: MainModule): MainComponent

    fun apiService(): ApiService
}
