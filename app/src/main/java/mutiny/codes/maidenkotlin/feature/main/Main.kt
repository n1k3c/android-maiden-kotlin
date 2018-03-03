package mutiny.codes.maidenkotlin.feature.main

import mutiny.codes.maidenkotlin.base.BasePresenter

/**
 * Created by nikola on 5/24/17.
 */
interface Main {

    interface View {

        fun sayHello(text: String)
    }

    interface Presenter : BasePresenter<View> {

        fun saySomething()
    }
}