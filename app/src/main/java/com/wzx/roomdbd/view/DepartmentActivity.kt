package com.wzx.roomdbd.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.WorkerThread
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import com.wzx.roomdbd.R
import com.wzx.roomdbd.db.DataBase
import com.wzx.roomdbd.db.Department
import com.wzx.roomdbd.db.dao.DepartmentDao
import com.wzx.roomdbd.view.adapter.DepartmentAdapter

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/25
 * 更新时间：
 * 更新内容：
 */
class DepartmentActivity : BaseActivity(), IOperate {

    @BindView(R.id.ed_name)
    lateinit var mNameET: EditText

    @BindView(R.id.recyclerView)
    lateinit var mRecyclerView: RecyclerView

    private lateinit var mDao: DepartmentDao

    private lateinit var mAdapter: DepartmentAdapter

    override fun getLayoutId() = R.layout.activity_department

    override fun initView(savedInstanceState: Bundle?) {

        supportActionBar?.title = getString(R.string.text_title_single)

        mDao = DataBase.getInstance(this).departmentDao()

        mAdapter = DepartmentAdapter(null)

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mRecyclerView.adapter = mAdapter
    }

    override fun initData() {
        query()
    }

    @OnClick(R.id.iv_add, R.id.iv_query, R.id.iv_update, R.id.iv_delete)
    fun onViewsClick(view: View) {
        when (view.id) {
            R.id.iv_add -> {
                insert()
            }
            R.id.iv_query -> {
                query()
            }
            R.id.iv_update -> {
                update()
            }
            R.id.iv_delete -> {
                delete()
            }
            else -> {

            }
        }
    }

    @SuppressLint("WrongThread")
    override fun insert() {
        if (mNameET.text.isEmpty()) {
            return
        }
        Thread {
            if (mDao.insert(Department(mNameET.text.toString())) > 0) {
                query()
                mNameET.setText("")
            }
        }.start()

    }

    override fun query() {
        Thread {
            val list = mDao.queryAll()
            runOnUiThread {
                mAdapter.newData(list)
            }
        }.start()
    }

    @SuppressLint("WrongThread")
    override fun update() {
        val department = mAdapter.selectedDepartment()
        if (department == null) {
            showSnakeBar(mNameET, "请选择部门")
            return
        }

        val newName = mNameET.text.toString()

        if (department.name == newName) {
            showSnakeBar(mNameET, "名称相同")
            return
        }

        department.name = newName

        Thread {
            if (mDao.update(department) > 0) {
                val list = mDao.queryAll()
                runOnUiThread {
                    mAdapter.newData(list)
                    mNameET.setText("")
                }
            }
        }.start()
    }

    /**
     * 因为user表中有department的索引，一旦department中某条数据删除后，user中相关数据也将删除
     */
    override fun delete() {
        val department = mAdapter.selectedDepartment()
        if (department == null) {
            showSnakeBar(mNameET, "请选择部门")
            return
        }

        Thread {
            mDao.delect(department)
            val list = mDao.queryAll()
            runOnUiThread {
                mAdapter.newData(list)
                mNameET.setText("")
            }
        }.start()
    }
}
