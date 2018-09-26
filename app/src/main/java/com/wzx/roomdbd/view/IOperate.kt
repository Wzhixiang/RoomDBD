package com.wzx.roomdbd.view

import android.support.annotation.WorkerThread

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/26
 * 更新时间：
 * 更新内容：
 */
interface IOperate {

    @WorkerThread
    fun query()

    @WorkerThread
    fun insert()

    @WorkerThread
    fun update()

    @WorkerThread
    fun delete()
}