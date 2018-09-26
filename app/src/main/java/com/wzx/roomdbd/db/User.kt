package com.wzx.roomdbd.db

import android.arch.persistence.room.*

/**
 * 描述：用户表
 *
 * tableName 表名
 * foreignKeys 外键
 * indices 索引
 *
 * @ColumnInfo 字段命名，默认是参数名
 * @Ignore 字段不写入数据库
 *
 * （注意地方：表中字段名最好不要相同）
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/21
 * 更新时间：
 * 更新内容：
 */

@Entity(tableName = "user",
        foreignKeys = [(ForeignKey(entity = Department::class, parentColumns = ["id"], childColumns = ["dId"], onDelete = ForeignKey.CASCADE))],
        indices = [Index(value = ["dId"])])
data class User(@ColumnInfo(name = "u_name") var name: String, var dId: Int) {
    @PrimaryKey
    var id: Int? = null
}