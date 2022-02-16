package com.example.crudandroid.ui.inscricao

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crudandroid.R
import com.example.crudandroid.data.db.AppDatabase
import com.example.crudandroid.data.db.dao.InscricaoDAO
import com.example.crudandroid.extension.esconderTeclado
import com.example.crudandroid.repository.DatabaseDataSource
import com.example.crudandroid.repository.InscricaoRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.inscricao_fragment.*


class InscricaoFragment : Fragment(R.layout.inscricao_fragment) {


    private val viewModel: InscricaoViewModel by viewModels {
            object : ViewModelProvider.Factory {

                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val inscricaoDAO: InscricaoDAO =
                        AppDatabase.getInstance(requireContext()).inscricaoDAO

                      val repository: InscricaoRepository = DatabaseDataSource(inscricaoDAO)
                      return InscricaoViewModel(repository) as T
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvents()
        setListeners()
    }


    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner){
            subscriberState->
            when (subscriberState) {
                is InscricaoViewModel.SubscriberState.Inserted ->{
                    limparCampos()
                    esconderTeclado()
                    requireView().requestFocus()
                }

            }
        }

        viewModel.messageEventData.observe(viewLifecycleOwner){stringResId ->
            Snackbar.make(requireView(), stringResId, Snackbar.LENGTH_LONG).show()
        }
    }


    private fun limparCampos() {


        input_name.text?.clear()
        input_email.text?.clear()

    }

    private fun esconderTeclado(){

        val parentActivity = requireActivity()
        if(parentActivity is AppCompatActivity){
            parentActivity.esconderTeclado()
        }

    }

    private fun setListeners() {
        botao_inscrever.setOnClickListener{
            val nome =  input_name.text.toString()
            val email =  input_email.text.toString()
            viewModel.addInscricao(nome, email)

        }
    }


}