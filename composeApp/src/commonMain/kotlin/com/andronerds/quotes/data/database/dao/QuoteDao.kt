package com.andronerds.quotes.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andronerds.quotes.data.database.entities.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: QuoteEntity)

    @Query("SELECT * FROM quotes")
    fun getAll(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM quotes WHERE id=:id")
    fun getQuote(id: Int): Flow<QuoteEntity>

    @Query("DELETE FROM quotes")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(quote: QuoteEntity)
}
