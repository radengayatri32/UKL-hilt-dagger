package com.example.appstarwars

import android.content.Context
import androidx.lifecycle.LiveData
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val characterDao: CharacterDao) {

    suspend fun getAllCharacters(): LiveData<List<CharacterEntity>> {
        return characterDao.getAllCharacters()
    }

    suspend fun searchCharacters(searchQuery: String): LiveData<List<CharacterEntity>> {
        return characterDao.searchCharacters(searchQuery)
    }

    suspend fun insertCharacters(characters: List<CharacterEntity>) {
        characterDao.insertCharacter(characters)
    }
}

