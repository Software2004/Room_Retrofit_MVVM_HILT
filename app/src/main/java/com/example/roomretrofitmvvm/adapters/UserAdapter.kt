package com.example.roomretrofitmvvm.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomretrofitmvvm.models.User
import com.example.roomretrofitmvvm.databinding.ItemUserBinding

class UserAdapter : ListAdapter<User, UserAdapter.ViewHolder>(ListComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemUserBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
            binding.btnCall.setOnClickListener{
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + user.phone)
                context.startActivity(intent)
            }
        }
    }

    class ListComparator : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}
