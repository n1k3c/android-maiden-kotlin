package mutiny.codes.maidenkotlin

import android.app.Application
import mutiny.codes.maidenkotlin.dagger.components.AppComponent
import mutiny.codes.maidenkotlin.dagger.components.DaggerAppComponent

/**
 * Created by nikola on 5/24/17.
 */

class TheApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        appComponent = DaggerAppComponent.create()
    }

    object Holder {
        val INSTANCE_HOLDER = TheApplication()
    }

    companion object {
        val instance: TheApplication by lazy { Holder.INSTANCE_HOLDER }

        lateinit var appComponent: AppComponent
    }
}