package org.mbds.unice.github.ui.users

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ItemListUserBinding

//TODO : Use viewBinding instead of findviewbyid
class ListUserViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(user: User, callback: UserListAdapter.Listener) {
        val imageurl : String = user.avatarUrl
        Glide.with(binding.root)
            .load(imageurl)
            .into(binding.itemListUserAvatar)

        binding.itemListUserUsername.text = user.login

        binding.itemListUserDeleteButton.setOnClickListener {
            callback.onClickDelete(user)
        }
    }

}