package mutiny.codes.maidenkotlin.di.components

import dagger.Subcomponent
import mutiny.codes.maidenkotlin.di.modules.MainModule
import mutiny.codes.maidenkotlin.feature.main.MainActivity


/**
 * Created by nikola on 5/24/17.
 */

@Subcomponent(modules = [(MainModule::class)])
interface MainComponent {

    fun inject(activity: MainActivity)
}
