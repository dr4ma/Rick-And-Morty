package com.example.rickandmortycharacters.presentation.fragments.allCharacters

import android.util.Log
import androidx.lifecycle.*
import com.example.rickandmortycharacters.domain.models.room.CacheModel
import com.example.rickandmortycharacters.domain.models.retrofit.ResultsItem
import com.example.rickandmortycharacters.domain.usecase.CacheDataInDatabaseUseCase
import com.example.rickandmortycharacters.domain.usecase.GetAllCharactersUseCase
import com.example.rickandmortycharacters.utilits.APP_ACTIVITY
import com.example.rickandmortycharacters.utilits.TAG
import com.example.rickandmortycharacters.utilits.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllCharactersViewModel @Inject constructor(
    private val repo: GetAllCharactersUseCase,
    private val cacheDataInDatabaseUseCase: CacheDataInDatabaseUseCase
) : ViewModel() {

    private val sumCharacters = mutableListOf<ResultsItem>()
    private val allCharactersList: MutableLiveData<MutableList<ResultsItem>> = MutableLiveData()
    val resultCharactersList : LiveData<MutableList<ResultsItem>> = allCharactersList
    private var page = 0
    private val cacheList = arrayListOf<CacheModel>()
    private val getCacheList = cacheDataInDatabaseUseCase.getCacheList
    val resultCacheList = getCacheList

    fun getAllCharacters(onSuccess: () -> Unit) {
        page++
        viewModelScope.launch {
            // for eliminate the error java.net.sockettimeoutexception: timeout
            try {
                repo.getAllCharacters(page = page)?.let {
                    sumCharacters.addAll(it)
                }
                allCharactersList.value = sumCharacters
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
            }
        }
        onSuccess()
    }

    fun insertCharacters(characters: List<ResultsItem>) {
        deleteCharacters()
        viewModelScope.launch {
            characters.forEach {
                val cacheModel = CacheModel(
                    name = it.name,
                    species = it.species,
                    gender = it.gender,
                    locationName = it.location.name,
                    status = it.status,
                    episodes = it.episode?.size!!
                )
                cacheList.add(cacheModel)
            }
            // for eliminate the error java.util.concurrentmodificationexception
            val newList: List<CacheModel> = ArrayList<CacheModel>(cacheList)

            cacheDataInDatabaseUseCase.insertData(newList) {
                Log.i(TAG, "insert was successful")
            }
            cacheList.clear()
        }
    }

    private fun deleteCharacters() {
        viewModelScope.launch {
            cacheDataInDatabaseUseCase.deleteData() {
                Log.i(TAG, "delete was successful")
            }
        }
    }
}