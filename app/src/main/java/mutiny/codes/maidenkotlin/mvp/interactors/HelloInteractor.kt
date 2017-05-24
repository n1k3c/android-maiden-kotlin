package mutiny.codes.maidenkotlin.mvp.interactors

import io.reactivex.Completable
import javax.inject.Inject


/**
 * Created by nikola on 5/24/17.
 */

class HelloInteractor @Inject constructor() : Interactor.HelloInteractor {

    override fun helloInteractor(): Completable {
        return Completable.create { subscriber ->
            subscriber.onComplete()
        }
    }
}