package com.laughat.funnymemes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.laughat.funnymemes.base.models.MemesItem

@Database(entities = [MemesItem::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MemesDatabase : RoomDatabase() {

    abstract val memesDatabaseDao: MemesDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: MemesDatabase? = null

        fun getInstance(context: Context): MemesDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                        MemesDatabase::class.java,
                            "MEMES_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}