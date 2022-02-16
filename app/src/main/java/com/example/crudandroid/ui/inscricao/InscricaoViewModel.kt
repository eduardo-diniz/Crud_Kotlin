package com.example.crudandroid.ui.inscricao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crudandroid.R
import com.example.crudandroid.repository.InscricaoRepository
import kotlinx.coroutines.launch

class InscricaoViewModel(private val repository: InscricaoRepository) : ViewModel() {

    private val _subscriberStateEventData = MutableLiveData<SubscriberState>()
    val subscriberStateEventData: LiveData<SubscriberState>
        get() = _subscriberStateEventData

    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>
        get() = _messageEventData

    fun addInscricao(name: String, email:String) = viewModelScope.launch {

        try {
            val id = repository.insertSubscriber(name, email)
            if(id>0) {
                _subscriberStateEventData.value = SubscriberState.Inserted
                _messageEventData.value = R.string.subscriber_inserted_successfully
            }


        } catch (ex: Exception){
            _messageEventData.value = R.string.subscriber_error_to_insert
            Log.e("", ex.toString())
        }

    }

    //Forma de escutar as notificações dos eventos para verificar os estados do CRUD na view
    sealed class SubscriberState {
        object Inserted : SubscriberState()
    }

    companion object {
        private val TAG = InscricaoViewModel::class.java.simpleName
    }


}