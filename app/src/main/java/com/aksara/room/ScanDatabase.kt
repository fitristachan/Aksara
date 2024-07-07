package com.aksara.room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [ScanEntity::class], version = 1, exportSchema = false)
abstract class ScanDatabase: RoomDatabase(){
    abstract val dao: ScanDao
}

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideScanDataBase(app: Application): ScanDatabase {
        return Room.databaseBuilder(
            app,
            ScanDatabase::class.java, "scan_database"
        )
           .fallbackToDestructiveMigration()
           .build()
    }



    @Provides
    @Singleton
    fun provideMyRepository(scanDatabase:ScanDatabase): ScanRepository {
        return ScanRepositoryImpl(scanDatabase.dao)
    }
}
