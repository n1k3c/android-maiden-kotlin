package mutiny.codes.maidenkotlin.feature.login

import mutiny.codes.maidenkotlin.R
import mutiny.codes.maidenkotlin.base.BaseActivity
import mutiny.codes.maidenkotlin.di.components.AppComponent

/**
 * Created by nikola on 03.03.18..
 */

class LoginActivity : BaseActivity() {

    override val contentViewResource: Int
        get() = R.layout.activity_main

    override fun injectDependencies(appComponent: AppComponent) {
    }

}