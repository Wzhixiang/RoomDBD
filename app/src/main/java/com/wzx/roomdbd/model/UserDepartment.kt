package com.wzx.roomdbd.model

import android.arch.persistence.room.ColumnInfo

/**
 * 描述：user和department联表查询结果
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/25
 * 更新时间：
 * 更新内容：
 */
data class UserDepartment(var id: Int,
                          var dId: Int,
                          @ColumnInfo(name = "u_name") var userName: String,
                          @ColumnInfo(name = "d_name") var department: String)