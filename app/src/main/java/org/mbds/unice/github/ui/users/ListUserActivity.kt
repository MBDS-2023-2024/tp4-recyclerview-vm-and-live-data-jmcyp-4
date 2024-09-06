package org.mbds.unice.github.ui.users

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ActivityListUserBinding

class ListUserActivity : AppCompatActivity(), UserListAdapter.Listener {
    private lateinit var binding: ActivityListUserBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    // By lazy permet de faire du chargement parresseux,
    // L'adapteur sera crée au premier appel
    private val adapter: UserListAdapter by lazy {
        UserListAdapter(this)
    }

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureFab()
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.users.observe(this) {
            adapter.updateList(it)
        }
    }

    private fun configureRecyclerView() {
        recyclerView = binding.activityListUserRv
        recyclerView.adapter = adapter
    }

    private fun configureFab() {
        fab = binding.activityListUserFab
        fab.setOnClickListener {
            viewModel.generateRandomUser()

        }
    }

    override fun onClickDelete(user: User) {
        Log.d("UserAction", "Suppression demandée pour l'utilisateur : ${user.login}")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Voulez-vous vraiment supprimer ${user.login} ?")

        builder.setPositiveButton("Supprimer") { _, _ ->
            Log.d("UserAction", "Suppression confirmée pour l'utilisateur : ${user.login}")
            deleteUser(user)
        }

        builder.setNegativeButton("Annuler") { dialog, _ ->
            Log.d("UserAction", "Suppression annulée pour l'utilisateur : ${user.login}")
            dialog.dismiss()
        }

        builder.show()
    }


    private fun deleteUser(user: User) {
        viewModel.deleteUser(user)
    }
}