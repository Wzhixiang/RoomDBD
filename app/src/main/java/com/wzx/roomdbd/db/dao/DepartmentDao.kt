package com.wzx.roomdbd.db.dao

import android.arch.persistence.room.*
import com.wzx.roomdbd.db.Department

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/25
 * 更新时间：
 * 更新内容：
 */
@Dao
interface DepartmentDao {

    @Query("select * from department")
    fun queryAll(): List<Department>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(d: Department): Long

    @Update
    fun update(d: Department): Int

    @Delete
    fun delect(vararg d: Department)
}