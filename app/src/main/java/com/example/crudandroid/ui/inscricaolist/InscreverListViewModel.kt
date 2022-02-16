package com.example.crudandroid.ui.inscricaolist

import androidx.lifecycle.ViewModel
import com.example.crudandroid.repository.InscricaoRepository

class InscreverListViewModel(
    private val repositorio:InscricaoRepository) : ViewModel(){


        val todosInscritosEvent = repositorio.getAllSubscribers()

}