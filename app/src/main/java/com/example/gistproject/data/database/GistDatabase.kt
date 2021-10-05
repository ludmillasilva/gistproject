package com.example.gistproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gistproject.data.dao.GistFavoriteDao
import com.example.gistproject.data.model.GistEntity

@Database(
    entities = [GistEntity::class],
    version = 1,
    exportSchema = false
)

abstract class GistDatabase: RoomDatabase() {
    abstract fun gistFavoriteDao(): GistFavoriteDao
    companion object{
        @Volatile
        private var INSTANCE: GistDatabase?= null
        fun getInstance(context: Context):GistDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GistDatabase::class.java,
                        "gist_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}