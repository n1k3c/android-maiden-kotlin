package mutiny.codes.maidenkotlin.mvp.presenters

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import mutiny.codes.maidenkotlin.feature.main.Main
import mutiny.codes.maidenkotlin.base.Interactor
import mutiny.codes.maidenkotlin.feature.main.MainPresenter
import org.junit.Test
import org.mockito.Mock

/**
 * Created by nikola on 6/16/17.
 */

class MainPresenterTest : BasePresenterTest() {

    lateinit var presenter: MainPresenter

    @Mock
    lateinit var view: Main.View

    @Mock
    lateinit var helloInteractor: Interactor.HelloInteractor

    override fun setUp() {
        presenter = MainPresenter(view, helloInteractor)
    }

    @Test
    fun shouldPassHelloWorldToView() {
        whenever(helloInteractor.getHello()).thenReturn(Completable.complete())

        presenter.saySomething()

        verify(view).sayHello("Hello World!")
    }

    @Test
    fun shouldHandleError() {
        whenever(helloInteractor.getHello()).thenReturn(Completable.error(Throwable()))

        presenter.saySomething()

        verify(view).sayHello("Error")
    }
}