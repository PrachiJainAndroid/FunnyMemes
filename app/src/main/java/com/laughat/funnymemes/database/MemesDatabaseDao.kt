package com.laughat.funnymemes.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.laughat.funnymemes.base.models.MemesItem

@Dao
interface MemesDatabaseDao {

    @Query("SELECT * from Memes_Table")
    fun getAllValues(): LiveData<List<MemesItem>?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(MemeList: List<MemesItem>)


}
