package com.example.dreamcinema.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieInfoDao {

    @Query("SELECT * FROM movie_overview ORDER BY popularity DESC")
    fun getMovieInfoList(): LiveData<List<MovieInfoDbModel>>

    @Query("SELECT * FROM movie_overview WHERE title == :title LIMIT 1")
    fun getMovieDetailInfo(title: String): LiveData<MovieInfoDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(priceList: List<MovieInfoDbModel>)
}
