package com.diegooliveira.rock_paper_scissors.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegooliveira.rock_paper_scissors.domain.repository.MedievalNameRepository
import kotlinx.coroutines.launch

class MedievalNameViewModel(
    private val repository: MedievalNameRepository
) : ViewModel() {

    val medievalNames: LiveData<List<String>> get() = _medievalNames
    private val _medievalNames = MutableLiveData<List<String>>()

    fun fetchMedievalNames() {
        viewModelScope.launch {
            try {
                val response = repository.getMedievalName()
                _medievalNames.value = response.results
            } catch (e: Exception) {
                Log.i("ERRO API", e.toString())
            }
        }
    }
}