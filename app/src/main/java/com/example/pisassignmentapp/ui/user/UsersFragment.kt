package com.example.pisassignmentapp.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pisassignmentapp.R
import com.example.pisassignmentapp.data.local.UserEntity
import kotlinx.android.synthetic.main.fragment_users.*

class UsersFragment : Fragment(), UserAdapter.OnUserAcceptance {

    private val TAG = UsersFragment::class.java.simpleName

    private var mUserViewModel : UserViewModel ?= null
    private var mAdapter : UserAdapter ?= null

    companion object {

        fun newInstance() : UsersFragment = UsersFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        populate()
    }


    private fun initViews (){

        mAdapter = UserAdapter(activity, arrayListOf<UserEntity>())
        mAdapter!!.setOnUserAcceptance(this)
        val mLayoutManager = LinearLayoutManager(activity)
        recyclerview.layoutManager = mLayoutManager
        recyclerview.itemAnimator = DefaultItemAnimator()
        recyclerview.adapter = mAdapter

    }

    /**
     * Initialize [UserViewModel]
     */
    private fun initViewModel(){

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        mUserViewModel?.let { lifecycle.addObserver(it) }

    }

    /**
     * Show users
     */
    private fun populate() {

        mUserViewModel?.getUsers()?.observe(this,
            Observer {
                if (it != null) {
                    mAdapter?.setItems(it)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onAccept(id: Long) {
        mUserViewModel?.onAccept(id)
    }

    override fun onReject(id: Long) {
        mUserViewModel?.onReject(id)
    }

}
