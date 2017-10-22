package ru.nuts_coon.atlanteam.di

import dagger.Module
import dagger.Provides
import ru.nuts_coon.atlanteam.AppModel
import ru.nuts_coon.atlanteam.screen.userScreen.UserModel


@Module
class ApplicationModule {
    @Provides fun appModel() = AppModel()
    @Provides fun userModel() = UserModel()
}