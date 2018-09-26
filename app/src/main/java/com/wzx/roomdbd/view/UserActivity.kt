package com.wzx.roomdbd.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.WorkerThread
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import com.wzx.roomdbd.R
import com.wzx.roomdbd.db.DataBase
import com.wzx.roomdbd.db.User
import com.wzx.roomdbd.db.dao.DepartmentDao
import com.wzx.roomdbd.db.dao.UserDao
import com.wzx.roomdbd.view.adapter.DepartmentAdapter
import com.wzx.roomdbd.view.adapter.UserAdapter

/**
 * 描述：
 *
 * 创建人： Administrator
 * 创建时间： 2018/9/25
 * 更新时间：
 * 更新内容：
 */
class UserActivity : BaseActivity(), IOperate {


    @BindView(R.id.departmentRV)
    lateinit var mDepartmentRecyclerView: RecyclerView

    @BindView(R.id.recyclerView)
    lateinit var mUserRecyclerView: RecyclerView

    @BindView(R.id.ed_name)
    lateinit var mNameET: EditText

    private lateinit var mDepartmentAdapter: DepartmentAdapter
    private lateinit var mUserAdapter: UserAdapter

    private lateinit var mDepartmentDao: DepartmentDao
    private lateinit var mUserDao: UserDao

    override fun initView(savedInstanceState: Bundle?) {

        supportActionBar?.title = getString(R.string.text_title_mutil)

        mNameET.hint = "请输入用户名"

        mDepartmentAdapter = DepartmentAdapter(null)

        mDepartmentRecyclerView.layoutManager = LinearLayoutManager(this)
        mDepartmentRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mDepartmentRecyclerView.adapter = mDepartmentAdapter

        mUserAdapter = UserAdapter(null)

        mUserRecyclerView.layoutManager = LinearLayoutManager(this)
        mUserRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mUserRecyclerView.adapter = mUserAdapter

        mDepartmentDao = DataBase.getInstance(this).departmentDao()
        mUserDao = DataBase.getInstance(this).userDao()
    }

    override fun initData() {
        query()
    }

    override fun getLayoutId() = R.layout.activity_user

    @OnClick(R.id.iv_add, R.id.iv_query, R.id.iv_update, R.id.iv_delete)
    fun onViewsClick(view: View) {
        when (view.id) {
            R.id.iv_add -> {
                insert()
            }
            R.id.iv_query -> {
                queryUser()
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

        val department = mDepartmentAdapter.selectedDepartment()
        if (department == null) {
            showSnakeBar(mNameET, "请选择部门")
            return
        }

        if (mNameET.text.isEmpty()) {
            showSnakeBar(mNameET, "用户名不能为空")
            return
        }

        Thread {
            if (mUserDao.insert(User(mNameET.text.toString(), department.id!!)) > 0) {
                queryUser()
                mNameET.setText("")
            }
        }.start()

    }

    override fun query() {
        Thread {
            val departmentList = mDepartmentDao.queryAll()
            val udList = mUserDao.queryAll()
            runOnUiThread {
                mDepartmentAdapter.newData(departmentList)
                mUserAdapter.newData(udList)
            }
        }.start()
    }

    @WorkerThread
    fun queryUser() {
        Thread {
            val udList = mUserDao.queryAll()
            runOnUiThread {
                mUserAdapter.newData(udList)
            }
        }.start()
    }

    @SuppressLint("WrongThread")
    override fun update() {

        val userD = mUserAdapter.selectUser()

        if (userD == null) {
            showSnakeBar(mNameET, "请选择用户")
            return
        }

        val department = mDepartmentAdapter.selectedDepartment()

        if (department != null) {
            userD.dId = department.id!!
            userD.department = department.name
        }

        if (!mNameET.text.isEmpty()) {
            userD.userName = mNameET.text.toString()
        }

        val user = User(userD.userName, userD.dId)

        user.id = userD.id

        Thread {
            if (mUserDao.update(user) > 0) {
                queryUser()
                mNameET.setText("")
            }
        }.start()
    }

    override fun delete() {
        val userD = mUserAdapter.selectUser()

        if (userD == null) {
            showSnakeBar(mNameET, "请选择用户")
            return
        }

        val user = User(userD.userName, userD.dId)

        user.id = userD.id

        Thread {
            if (mUserDao.delete(user) > 0) {
                queryUser()
            }
        }.start()
    }
}