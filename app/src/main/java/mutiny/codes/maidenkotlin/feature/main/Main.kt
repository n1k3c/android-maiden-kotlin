package mutiny.codes.maidenkotlin.feature.main

import mutiny.codes.maidenkotlin.base.BasePresenter
import mutiny.codes.maidenkotlin.base.BaseView

/**
 * Created by nikola on 5/24/17.
 */
interface Main {

    interface View : BaseView {

        fun sayHello(text: String)
    }

    interface Presenter : BasePresenter<View> {

        fun saySomething()
    }
}