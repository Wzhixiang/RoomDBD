package com.wzx.roomdbd.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * 描述：部门表
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/25
 * 更新时间：
 * 更新内容：
 */
@Entity(tableName = "department")
data class Department(@ColumnInfo(name = "d_name") var name: String) {
    @PrimaryKey
    var id: Int? = null
}