package com.arcanesecurity.resumofinal.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.arcanesecurity.resumofinal.model.Image

@Dao
interface PixabayDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(list: List<Image>)

    @Query("select * from image")
    suspend fun fetch() : List<Image>

}