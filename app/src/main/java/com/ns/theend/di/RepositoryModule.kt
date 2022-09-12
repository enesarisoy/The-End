package com.ns.theend.di

import com.google.firebase.database.FirebaseDatabase
import com.ns.theend.data.repository.FirebaseRepository
import com.ns.theend.data.repository.FirebaseRepositoryImpl
import com.ns.theend.ui.OnClickStar
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {


    @Provides
    @Singleton
    fun provideFirebaseRepository(database: FirebaseDatabase): FirebaseRepository {
        return FirebaseRepositoryImpl(database)
    }

}