package com.wzx.roomdbd.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import android.support.annotation.CheckResult
import com.wzx.roomdbd.db.dao.DepartmentDao
import com.wzx.roomdbd.db.dao.UserDao

/**
 * 描述：数据库
 *
 * entities 所包含表
 * version 版本
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/21
 * 更新时间：
 * 更新内容：
 */

@Database(entities = [User::class, Department::class], version = 1)
abstract class DataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun departmentDao(): DepartmentDao

    companion object {
        private const val DataBaseName = "DataBase.db"

        private val versionUpdate = MigrationImpl(1, 2)

        @Volatile
        private var instance: DataBase? = null

        /**
         * 单例创建唯一变量
         */
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: createDataBase(context.applicationContext).also {
                instance = it
            }
        }

        /**
         *
         */
        @CheckResult(suggest = "多次创建数据库会出错")
        private fun createDataBase(context: Context): DataBase =
                Room.databaseBuilder(context.applicationContext, DataBase::class.java, DataBaseName)
                        .addMigrations(versionUpdate)
                        .build()

        private class MigrationImpl(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //添加age字段
                database.execSQL("ALTER TABLE user ADD COLUMN age INTEGER NOT NULL DEFAULT 0")
            }
        }
    }


}