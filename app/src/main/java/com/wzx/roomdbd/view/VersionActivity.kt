package com.wzx.roomdbd.view

import android.os.Bundle
import com.wzx.roomdbd.R

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/26
 * 更新时间：
 * 更新内容：
 */
class VersionActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_about_version

    override fun initView(savedInstanceState: Bundle?) {
        supportActionBar?.title = getString(R.string.text_title_version)
    }

    override fun initData() {

    }
}