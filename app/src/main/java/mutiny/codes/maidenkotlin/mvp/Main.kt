package mutiny.codes.maidenkotlin.mvp

import mutiny.codes.maidenkotlin.base.BasePresenter

/**
 * Created by nikola on 5/24/17.
 */
interface Main {

    interface View {

        fun sayHello(text: String)
    }

    interface Presenter : BasePresenter<Main.View> {

        fun saySomething()
    }
}