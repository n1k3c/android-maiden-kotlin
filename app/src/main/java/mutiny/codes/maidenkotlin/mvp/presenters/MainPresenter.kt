package mutiny.codes.maidenkotlin.mvp.presenters

import android.util.Log
import com.pawegio.kandroid.d
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
                helloInteractor.helloInteractor()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(object : DisposableCompletableObserver() {
                            override fun onComplete() {
                                Log.d("presenter", "onSuccess")
                                view.sayHello("HelloWorld!")
                            }

                            override fun onError(e: Throwable?) {
                                d("onError -> ${e.toString()}")
                            }
                        })

        )
    }

}



