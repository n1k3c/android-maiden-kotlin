package mutiny.codes.maidenkotlin.mvp

/**
 * Created by nikola on 5/24/17.
 */
interface Main {

    interface View {

        fun sayHello(text: String)
    }

    interface Presenter {

        fun saySomething()

        fun unsubscribe()
    }
}