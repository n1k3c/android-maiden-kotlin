package mutiny.codes.maidenkotlin.mvp.presenters

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by nikola on 5/24/17.
 */

abstract class BasePresenter {

    var compositeDisposable = CompositeDisposable()

    fun unsubscribe() {
        compositeDisposable.clear()
    }

}
