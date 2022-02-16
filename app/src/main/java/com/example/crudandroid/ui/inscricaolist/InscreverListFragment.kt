package com.example.crudandroid.ui.inscricaolist


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.crudandroid.R
import com.example.crudandroid.data.db.AppDatabase
import com.example.crudandroid.data.db.dao.InscricaoDAO
import com.example.crudandroid.data.db.entity.InscricaoEntity
import com.example.crudandroid.repository.DatabaseDataSource
import com.example.crudandroid.repository.InscricaoRepository
import com.example.crudandroid.ui.inscricao.InscricaoViewModel
import kotlinx.android.synthetic.main.inscrever_list_fragment.*
import com.example.crudandroid.extension.navigateWithAnimations


class InscreverListFragment : Fragment(R.layout.inscrever_list_fragment) {

    private val viewModel: InscreverListViewModel by viewModels {
        object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val inscricaoDAO: InscricaoDAO =
                    AppDatabase.getInstance(requireContext()).inscricaoDAO

                val repository: InscricaoRepository = DatabaseDataSource(inscricaoDAO)
                return InscreverListViewModel(repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelEvents()
        configurarViewListeners()
    }

        private fun observeViewModelEvents() {
            viewModel.todosInscritosEvent.observe(viewLifecycleOwner){todosInscritos ->
                val inscreverListAdapter = InscreverListAdapter(todosInscritos)
            recycler_subscribers.run {
                setHasFixedSize(true)
                adapter = inscreverListAdapter
                }
            }
    }

    private fun configurarViewListeners(){
        fabAddSubscriber.setOnClickListener{
            findNavController().navigateWithAnimations(R.id.inscreverListFragment)

        }

    }



}


