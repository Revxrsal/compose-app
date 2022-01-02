package com.pose.app.di

import android.content.Context
import androidx.room.Room
import com.pose.app.db.CompDao
import com.pose.app.db.CompDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CompDatabase {
        return Room.databaseBuilder(
            context,
            CompDatabase::class.java,
            "comp_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDao(database: CompDatabase): CompDao {
        return database.dao()
    }
}
