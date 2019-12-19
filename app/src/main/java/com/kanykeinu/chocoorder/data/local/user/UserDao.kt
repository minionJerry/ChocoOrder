package com.kanykeinu.chocoorder.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kanykeinu.chocoorder.data.entity.login.User
import com.kanykeinu.chocoorder.data.entity.product.Product

@Dao
interface UserDao {

    @Query("SELECT token FROM User")
    fun getToken(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToken(user: User)
}