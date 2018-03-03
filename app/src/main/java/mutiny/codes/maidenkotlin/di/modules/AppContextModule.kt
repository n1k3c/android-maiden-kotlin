package mutiny.codes.maidenkotlin.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import mutiny.codes.maidenkotlin.TheApplication

/**
 * Created by nikola on 5/24/17.
 */

@Module
class AppContextModule {

    @Provides
    fun provideAppContext(): Context = TheApplication.instance

}

