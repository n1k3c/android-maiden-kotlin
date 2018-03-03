package mutiny.codes.maidenkotlin.base

/**
 * Created by nikola on 01.12.17..
 */
interface BasePresenter<V> {
    fun onAttach()

    fun onDetach()
}