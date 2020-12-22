package com.example.recipeapp.data.model.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UserDatabase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun registerUser() = runBlockingTest {
        val user = User(1, "muragulm@gmail.com", "m", "m")
        dao.registerUser(user)
        val allUsers = dao.getAllUsers()
        assertThat(allUsers.contains(user), `is`(true))
    }

    @Test
    fun deleteUser() = runBlockingTest {
        val user = User(1, "muragulm@gmail.com", "m", "m")
        dao.registerUser(user)
        dao.deleteUser(user)
        val allUsers = dao.getAllUsers()
        assertThat(allUsers.contains(user), `is`(false))
    }

    @Test
    fun getUserCount() = runBlockingTest {
        val user1 = User(1, "a@gmail.com", "a", "a")
        val user2 = User(2, "b@gmail.com", "b", "b")
        val user3 = User(3, "c@gmail.com", "c", "c")
        dao.registerUser(user1)
        dao.registerUser(user2)
        dao.registerUser(user3)

        val totalUserCount = dao.getUsersCount()
        assertThat(totalUserCount, `is`(3))
    }

    @Test
    fun getUser() = runBlockingTest {
        val user1 = User(1, "a@gmail.com", "a", "a")
        val user2 = User(2, "b@gmail.com", "b", "b")
        val user3 = User(3, "c@gmail.com", "c", "c")
        dao.registerUser(user1)
        dao.registerUser(user2)
        dao.registerUser(user3)

        val targetUser = dao.getUser("a")
        assertThat(targetUser, `is`(user1))

    }

}