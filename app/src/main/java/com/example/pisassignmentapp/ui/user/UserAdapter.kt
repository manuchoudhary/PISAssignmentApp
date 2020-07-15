package com.example.pisassignmentapp.ui.user

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pisassignmentapp.R
import com.example.pisassignmentapp.data.local.UserEntity
import kotlinx.android.synthetic.main.item_view.view.*

class UserAdapter (private var context : Context?, private var users : List<UserEntity>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    lateinit var mCallback : OnUserAcceptance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(v)

    }

    fun setOnUserAcceptance(mCallback: OnUserAcceptance) {
        this.mCallback = mCallback
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.mName?.text = users[position].name
        context?.let { Glide.with(it).asBitmap().load(users[position].avatar).into(holder?.mAvatar) }
        if (!users[position].accept && !users[position].rejected) {
            holder?.mReject.visibility = View.VISIBLE
            holder?.mAccept.visibility = View.VISIBLE
            holder?.mMessage.visibility = View.GONE
        }
        if (users[position].rejected) {
            holder?.mReject.visibility = View.GONE
            holder?.mAccept.visibility = View.GONE
            holder.mMessage.visibility = View.VISIBLE
            holder?.mMessage.text = "REJECTED"
        }
        if (users[position].accept) {
            holder?.mReject.visibility = View.GONE
            holder?.mAccept.visibility = View.GONE
            holder.mMessage.visibility = View.VISIBLE
            holder?.mMessage.text = "ACCEPTED"
        }
        holder?.mAccept.setOnClickListener {
            holder?.mAccept.visibility = View.GONE
            holder?.mReject.visibility = View.GONE
            holder?.mMessage.visibility = View.VISIBLE
            holder?.mMessage.text = "ACCEPTED"
            mCallback.onAccept(users[position].id)
        }

        holder?.mReject.setOnClickListener {
            holder?.mAccept.visibility = View.GONE
            holder?.mReject.visibility = View.GONE
            holder?.mMessage.visibility = View.VISIBLE
            holder?.mMessage.text = "REJECTED"
            mCallback.onReject(users[position].id)
        }
    }

    override fun getItemCount(): Int = users?.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mName = itemView.name
        val mAvatar = itemView.avatar
        val mAccept = itemView.btnAccept
        val mReject = itemView.btnReject
        val mMessage = itemView.txtMessage
    }

    fun setItems(users : List<UserEntity>) {

        this.users = users
        notifyDataSetChanged()
    }

    interface OnUserAcceptance {
        fun onAccept(id: Long)
        fun onReject(id: Long)
    }
}