package org.mbds.unice.github.ui.users

import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ItemListUserBinding


class ListUserViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root)  {

    fun bind(user: User, callback: UserListAdapter.Listener) {
        val imageurl : String = user.avatarUrl
        Glide.with(binding.root)
            .load(imageurl)
            .into(binding.itemListUserAvatar)

        binding.itemListUserUsername.text = user.login

        if (user.isActive) {
            binding.root.setBackgroundColor(Color.WHITE)  // Fond blanc si activé
            binding.itemListUserDeleteButton.setImageResource(R.drawable.ic_delete_black_24dp)  // Icône "poubelle"
        } else {
            binding.root.setBackgroundColor(Color.RED)  // Fond rouge si désactivé
            binding.itemListUserDeleteButton.setImageResource(R.drawable.icc_restore_24)  // Icône "restore"
        }

        binding.itemListUserDeleteButton.setOnClickListener {
            // Inverser le statut actif/désactivé
            user.isActive = !user.isActive
            // Mettre à jour l'affichage visuel en fonction du nouveau statut
            bind(user, callback)
            // Notifier le callback de la modification
            callback.onClickToggleStatus(user)
        }
    }

}