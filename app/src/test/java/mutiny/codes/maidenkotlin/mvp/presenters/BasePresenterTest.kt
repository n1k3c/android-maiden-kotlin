package mutiny.codes.maidenkotlin.mvp.presenters

import mutiny.codes.maidenkotlin.util.RxSchedulersOverrideRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.reflect.KClass

/**
 * Created by nikola on 6/16/17.
 */

@RunWith(MockitoJUnitRunner::class)
abstract class BasePresenterTest()  {

    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulersOverrideRule()

    @Before
    abstract fun setUp()
}