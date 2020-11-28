package com.example.recipeapp.data.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(user: User)

    @Query("SELECT COUNT(*) FROM users_table")
    fun getUsersCount(): Int

    @Query("SELECT * FROM users_table WHERE username = :username LIMIT 1")
    fun getUser(username: String): User
}