package org.mbds.unice.github.ui.users

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.mbds.unice.github.R
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

    override fun onClickToggleStatus(user: User) {

        viewModel.updateUserStatus(user)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_list_user, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView

        searchView?.queryHint = "Rechercher un utilisateur"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    filterUsersByName(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    filterUsersByName(it)
                }
                return true
            }
        })
        return true
    }

    private fun filterUsersByName(query: String) {
        val filteredList = viewModel.users.value?.filter { user ->
            user.login.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredList ?: emptyList())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_alpha_asc -> {
                sortUsersByName(ascending = true)
                true
            }
            R.id.action_sort_alpha_desc -> {
                sortUsersByName(ascending = false)
                true
            }
            R.id.action_sort_date_asc -> {
                sortUsersByDate(ascending = true)
                true
            }
            R.id.action_sort_date_desc -> {
                sortUsersByDate(ascending = false)
                true
            }
            R.id.action_sort_status -> {
                sortUsersByStatus()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Trier les utilisateurs par nom (A-Z ou Z-A)
    private fun sortUsersByName(ascending: Boolean) {
        val sortedList = if (ascending) {
            viewModel.users.value?.sortedBy { it.login }
        } else {
            viewModel.users.value?.sortedByDescending { it.login }
        }
        adapter.updateList(sortedList ?: emptyList())
    }

    // Trier les utilisateurs par date de création
    private fun sortUsersByDate(ascending: Boolean) {
        val sortedList = if (ascending) {
            viewModel.users.value?.sortedBy { it.creationDate }
        } else {
            viewModel.users.value?.sortedByDescending { it.creationDate }
        }
        adapter.updateList(sortedList ?: emptyList())
    }

    // Trier les utilisateurs par statut (activé/désactivé)
    private fun sortUsersByStatus() {
        val sortedList = viewModel.users.value?.sortedByDescending { it.isActive }
        adapter.updateList(sortedList ?: emptyList())
    }



}