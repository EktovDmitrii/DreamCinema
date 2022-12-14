package main.pack.dreamcinema.data.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieDbModel: MovieDbModel)

    @Query("DELETE FROM favourite_movies WHERE id=:movieId")
    suspend fun deleteMovie(movieId: Int)

    @Query("SELECT * FROM favourite_movies")
    fun getMovieList(): List<MovieDbModel>
}