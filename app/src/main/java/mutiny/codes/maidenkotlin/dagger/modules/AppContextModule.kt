package mutiny.codes.maidenkotlin.dagger.modules

import dagger.Module
import dagger.Provides
import mutiny.codes.maidenkotlin.TheApplication

/**
 * Created by nikola on 5/24/17.
 */

@Module
class AppContextModule {

    @Provides
    fun provideAppContext(): TheApplication? {
        return TheApplication.instance
    }

}

