package mutiny.codes.maidenkotlin.activities

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import mutiny.codes.maidenkotlin.R
import mutiny.codes.maidenkotlin.dagger.components.AppComponent
import mutiny.codes.maidenkotlin.dagger.modules.MainModule
import mutiny.codes.maidenkotlin.mvp.Main
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

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun sayHello(text: String) {
        tvHelloWorld.text = text
    }
}
