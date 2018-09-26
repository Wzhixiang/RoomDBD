package com.wzx.roomdbd.view

import android.content.Intent
import android.os.Bundle
import android.support.annotation.NonNull
import android.view.View
import butterknife.OnClick
import com.wzx.roomdbd.R

/**
 * https://blog.csdn.net/u012149399/article/details/79391369
 */
class MainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        supportActionBar!!.title = "关于Room数据库"
    }

    override fun initData() {

    }

    @OnClick(R.id.tv_table, R.id.tv_table_mutil, R.id.tv_table_update)
    fun onViewsClick(view: View) {
        when (view.id) {
            R.id.tv_table -> {
                toActivity(DepartmentActivity::class.java)
            }
            R.id.tv_table_mutil -> {
                toActivity(UserActivity::class.java)
            }
            R.id.tv_table_update -> {
                toActivity(VersionActivity::class.java)
            }
            else -> {

            }
        }
    }

    /**
     * 跳转{@link AppCompatActivity}
     *
     * @param cls 需启动的Activity类
     */
    fun toActivity(@NonNull cls: Class<*>) {
        startActivity(Intent(this, cls))
    }
}
