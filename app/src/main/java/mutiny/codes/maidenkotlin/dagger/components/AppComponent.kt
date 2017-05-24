package mutiny.codes.maidenkotlin.dagger.components

import dagger.Component
import mutiny.codes.maidenkotlin.dagger.modules.AppContextModule
import mutiny.codes.maidenkotlin.dagger.modules.MainModule
import javax.inject.Singleton


/**
 * Created by nikola on 5/24/17.
 */

@Component(modules = arrayOf(AppContextModule::class))
@Singleton
interface AppComponent {

    fun plus(module: MainModule): MainComponent
}
