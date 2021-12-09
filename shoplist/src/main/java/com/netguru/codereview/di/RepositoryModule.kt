package com.netguru.codereview.di

import androidx.lifecycle.ViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import com.netguru.codereview.network.ShopListApiMock
import com.netguru.codereview.network.ShopListRepository


@Module
@InstallIn(ViewModelComponent::class,FragmentComponent::class)
class RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideRepository(
        @shopRepositoryqualifier shopListApiMock: ShopListApiMock
    ): ShopListRepository {
        return ShopListRepository(shopListApiMock)
    }

    @ViewModelScoped
    @shopRepositoryqualifier
    @Provides
    fun providerShopListMock(
    ): ShopListApiMock {
        return ShopListApiMock()
    }
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class shopRepositoryqualifier


}