package mutiny.codes.maidenkotlin.mvp.presenters

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import mutiny.codes.maidenkotlin.base.BasePresenter
import mutiny.codes.maidenkotlin.mvp.Main
import mutiny.codes.maidenkotlin.mvp.interactors.Interactor
import javax.inject.Inject


/**
 * Created by nikola on 5/24/17.
 */

class MainPresenter @Inject constructor(val view: Main.View,
                                        private val helloInteractor: Interactor.HelloInteractor)
    : Main.Presenter, LifecycleObserver {

    private lateinit var compositeDisposable: CompositeDisposable

    init {
        if (view is LifecycleOwner) {
            (view as LifecycleOwner).lifecycle.addObserver(this)
        }
        compositeDisposable = CompositeDisposable()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onAttach() {
        Log.d("Presenter", "onAttach")
        saySomething()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    override fun onDetach() {
        Log.d("Presenter", "onDetach")
        compositeDisposable.clear()
    }

    override fun saySomething() {
        compositeDisposable.add(
                helloInteractor.getHello()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(object : DisposableCompletableObserver() {
                            override fun onError(e: Throwable) {
                                view.sayHello("Error")
                            }

                            override fun onComplete() {
                                // Log.d("presenter", "onSuccess")
                                view.sayHello("Hello World!")
                            }
                        })
        )
    }
}



