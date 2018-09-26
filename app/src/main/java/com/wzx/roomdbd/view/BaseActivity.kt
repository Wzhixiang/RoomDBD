package com.wzx.roomdbd.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import butterknife.ButterKnife

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/25
 * 更新时间：
 * 更新内容：
 */
abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun initView(@Nullable savedInstanceState: Bundle?)

    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)

        initView(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        initData()
    }

    protected fun showSnakeBar(@NonNull view: View, @NonNull msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()
    }
}