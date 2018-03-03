package mutiny.codes.maidenkotlin.base

import io.reactivex.Completable

/**
 * Created by nikola on 5/24/17.
 */
interface Interactor {

    interface HelloInteractor {

        fun getHello(): Completable
    }
}