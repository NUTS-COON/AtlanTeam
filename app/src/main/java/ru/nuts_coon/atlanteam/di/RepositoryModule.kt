package ru.nuts_coon.atlanteam.di

import dagger.Module
import dagger.Provides
import ru.nuts_coon.atlanteam.Application.RetrofitAPI
import ru.nuts_coon.atlanteam.realmRepository.RealmRepository

@Module
class RepositoryModule {
    @Provides fun repository() = RealmRepository()


    @Provides fun api() = RetrofitAPI.retrofit.create(RetrofitAPI::class.java)
}