package com.kaito.kmoney.data.repository

import com.kaito.kmoney.data.model.entity.User
import com.kaito.kmoney.data.source.local.dao.UserDao
import kotlinx.coroutines.flow.Flow

interface IUserRepo {

    fun getAllUsers(): Flow<List<User>>

    suspend fun addUser(user: User)

    suspend fun deleteUser(user: User)
}

class UserRepoImpl(
    private val userDao: UserDao
): IUserRepo {
    override fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    override suspend fun addUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

}