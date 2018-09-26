package com.wzx.roomdbd.db.dao

import android.arch.persistence.room.*
import com.wzx.roomdbd.db.User
import com.wzx.roomdbd.model.UserDepartment

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/21
 * 更新时间：
 * 更新内容：
 */

@Dao
interface UserDao {

    @Query("select User.id, dId, u_name, d_name from department inner join user on User.dId = Department.id")
    fun queryAll(): List<UserDepartment>

    @Query("select * from user")
    fun query(): List<User>

    @Query("select * from user where id = :id")
    fun query(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Update
    fun update(user: User): Int

    @Delete
    fun delete(vararg user: User): Int
}