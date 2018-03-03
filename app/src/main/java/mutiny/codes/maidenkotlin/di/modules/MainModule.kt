package mutiny.codes.maidenkotlin.di.modules


import dagger.Module
import dagger.Provides
import mutiny.codes.maidenkotlin.base.Interactor
import mutiny.codes.maidenkotlin.feature.main.Main
import mutiny.codes.maidenkotlin.feature.main.MainPresenter
import mutiny.codes.maidenkotlin.feature.main.interactors.HelloInteractor

/**
 * Created by nikola on 5/24/1.
 */

@Module
class MainModule(var view: Main.View) {

    @Provides
    fun provideMainView(): Main.View = view

    @Provides
    fun provideMainPresenter(mainPresenter: MainPresenter): Main.Presenter = mainPresenter

    @Provides
    fun provideHelloInteractor(helloInteractor: HelloInteractor): Interactor.HelloInteractor = helloInteractor
}
