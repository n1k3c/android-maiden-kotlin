package mutiny.codes.maidenkotlin.dagger.components

import dagger.Subcomponent
import mutiny.codes.maidenkotlin.activities.MainActivity
import mutiny.codes.maidenkotlin.dagger.modules.MainModule


/**
 * Created by nikola on 5/24/17.
 */

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(activity: MainActivity)
}
