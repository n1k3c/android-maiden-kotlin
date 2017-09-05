package mutiny.codes.maidenkotlin.mvp.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import mutiny.codes.maidenkotlin.mvp.Main
import mutiny.codes.maidenkotlin.mvp.interactors.Interactor
import javax.inject.Inject


/**
 * Created by nikola on 5/24/17.
 */

class MainPresenter @Inject constructor(val view: Main.View, val helloInteractor: Interactor.HelloInteractor)
    : BasePresenter(), Main.Presenter {

    override fun saySomething() {
        compositeDisposable.add(
                helloInteractor.getHello()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(object : DisposableCompletableObserver() {
                            override fun onError(e: Throwable) {
                                view.sayHello("Error")
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onComplete() {
                                // Log.d("presenter", "onSuccess")
                                view.sayHello("Hello World!")
                            }
                        })
        )
    }
}



