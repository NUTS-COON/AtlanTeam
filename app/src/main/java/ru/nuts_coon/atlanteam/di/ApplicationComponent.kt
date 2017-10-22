package ru.nuts_coon.atlanteam.di

import dagger.Component
import ru.nuts_coon.atlanteam.AppModel
import ru.nuts_coon.atlanteam.AppViewModel
import ru.nuts_coon.atlanteam.screen.userScreen.UserModel
import ru.nuts_coon.atlanteam.screen.userScreen.UserViewModel


@Component(modules = arrayOf(ApplicationModule::class, RepositoryModule::class))
interface ApplicationComponent {
    fun inject(appViewModel: AppViewModel)
    fun inject(appModel: AppModel)

    fun inject(userViewModel: UserViewModel)
    fun inject(userModel: UserModel)
}