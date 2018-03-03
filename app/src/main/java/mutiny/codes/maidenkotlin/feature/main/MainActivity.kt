package mutiny.codes.maidenkotlin.feature.main

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import mutiny.codes.maidenkotlin.R
import mutiny.codes.maidenkotlin.base.BaseActivity
import mutiny.codes.maidenkotlin.di.components.AppComponent
import mutiny.codes.maidenkotlin.di.modules.MainModule
import javax.inject.Inject

class MainActivity : BaseActivity(), Main.View {

    @Inject
    lateinit var presenter: Main.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.saySomething()
    }

    override val contentViewResource: Int = R.layout.activity_main

    override fun injectDependencies(appComponent: AppComponent) {
        appComponent.plus(MainModule(this))
                .inject(this)
    }

    override fun sayHello(text: String) {
        tvHelloWorld.text = text
    }
}
