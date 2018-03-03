package mutiny.codes.maidenkotlin.feature.main.interactors

import io.reactivex.Completable
import mutiny.codes.maidenkotlin.base.Interactor
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