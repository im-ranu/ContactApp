package com.kisannetwork.contactsapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kisannetwork.contactsapp.data.models.Message

@Dao
interface MessgaeDao {

    @Query("SELECT * FROM Message ORDER BY timestamp DESC")
    fun getAll(): List<Message>

    @Insert
    fun insertMessage(users: Message)


}