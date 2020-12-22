package com.example.recipeapp.data.model.user

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerUser(user: User)

    @Query("SELECT COUNT(*) FROM users_table")
    fun getUsersCount(): Int

    @Query("SELECT * FROM users_table")
    fun getAllUsers(): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM users_table WHERE username = :username LIMIT 1")
    fun getUser(username: String): User
}