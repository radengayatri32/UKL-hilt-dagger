package com.example.appstarwars

import androidx.lifecycle.LiveData
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val characterDao: CharacterDao, private val apiService: ApiService) {

    suspend fun getAllCharacters(): LiveData<List<CharacterEntity>> {
        return characterDao.getAllCharacters()
    }

    suspend fun searchCharacters(searchQuery: String): LiveData<List<CharacterEntity>> {
        return characterDao.searchCharacters(searchQuery)
    }

    suspend fun insertCharacters(characters: List<CharacterEntity>) {
        characterDao.insertCharacter(characters)
    }

    fun ResultsItem.toCharacterEntity(): CharacterEntity{
        return CharacterEntity(
            name = this.name,
            birthYear = this.birthYear,
            eyeColor = this.eyeColor,
            gender = this.gender,
            skinColor = this.skinColor,
            height = this.height
        )
    }

    suspend fun fetchData(){
        val response: Response<Responses> = apiService.getResponse()
        if (response.isSuccessful){
            val data = response.body()?.results
            data?.let {
                this.saveDatatoRoom(it)
            }
        }
    }

    private suspend fun saveDatatoRoom(data: List<ResultsItem>) {
        characterDao.deleteAllCharacters()
        characterDao.insertCharacter(data.map { it.toCharacterEntity() })
    }
}

