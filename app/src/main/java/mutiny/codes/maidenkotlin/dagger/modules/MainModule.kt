package mutiny.codes.maidenkotlin.dagger.modules


import dagger.Module
import dagger.Provides
import mutiny.codes.maidenkotlin.mvp.Main
import mutiny.codes.maidenkotlin.mvp.interactors.HelloInteractor
import mutiny.codes.maidenkotlin.mvp.interactors.Interactor
import mutiny.codes.maidenkotlin.mvp.presenters.MainPresenter

/**
 * Created by nikola on 5/24/1.
 */

@Module
class MainModule(var view: Main.View) {

    @Provides
    fun provideMainView(): Main.View {
        return view
    }

    @Provides
    fun provideMainPresenter(mainPresenter: MainPresenter): Main.Presenter {
        return mainPresenter
    }

    @Provides
    fun provideHelloInteractor(helloInteractor: HelloInteractor): Interactor.HelloInteractor {
        return helloInteractor
    }
}
