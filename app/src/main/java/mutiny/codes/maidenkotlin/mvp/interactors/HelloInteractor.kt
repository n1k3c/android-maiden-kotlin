package mutiny.codes.maidenkotlin.mvp.interactors

import io.reactivex.Completable
import javax.inject.Inject


/**
 * Created by nikola on 5/24/17.
 */

class HelloInteractor @Inject constructor() : Interactor.HelloInteractor {

    override fun getHello(): Completable {
        return Completable.create { subscriber ->
            try {
                subscriber.onComplete()
            } catch (e: Throwable) {
                subscriber.onError(e)
            }
        }
    }
}