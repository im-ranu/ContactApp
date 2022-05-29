package com.kisannetwork.contactsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kisannetwork.contactsapp.data.models.Message


@Database(entities = [Message::class], version = 1)
abstract class AppDatabase(): RoomDatabase() {

    abstract fun messageDao() : MessgaeDao


    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context : Context):AppDatabase{
            if (instance == null) {
                // if the instance is null we
                // are creating a new instance
                instance =  // for creating a instance for our database
                        // we are creating a database builder and passing
                        // our database class with our database name.
                    Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase::class.java, "message_database"
                    )
                        .build()
            }
            return instance!!

        }

    }
}