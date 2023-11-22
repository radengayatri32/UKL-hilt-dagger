package com.example.appstarwars

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class YourViewModel @Inject constructor(private val characterRepository: CharacterRepository) : ViewModel() {

    private val _filteredData = MutableLiveData<List<CharacterEntity>>()
    val filteredData: LiveData<List<CharacterEntity>> get() = _filteredData

    fun fetchData() {
        viewModelScope.launch {
            characterRepository.fetchData()
            // Use observe to observe changes in LiveData
            characterRepository.getAllCharacters().observeForever { data ->
                _filteredData.postValue(data)
            }
        }
    }

    fun filterData(searchText: String) {
        viewModelScope.launch {
            characterRepository.searchCharacters("%$searchText%").observeForever { data ->
                _filteredData.postValue(data)
            }
        }
    }
}

