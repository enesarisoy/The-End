package com.ns.theend.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    @Provides
    @Singleton
    fun provideDatabaseInstance(): FirebaseDatabase {
        return FirebaseDatabase.getInstance("https://the-end-3fdda-default-rtdb.europe-west1.firebasedatabase.app")
    }
}