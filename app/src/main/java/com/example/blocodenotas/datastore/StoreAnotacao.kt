package com.example.blocodenotas.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreAnotacao(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("configuracoes")
        val ANOTACAO_KEY = stringPreferencesKey("anotacao")
    }

    fun getAnotacao() : Flow<String> {
        return context.dataStore.data
        .map { preferences ->
            preferences[ANOTACAO_KEY] ?: ""
        }
    }

    suspend fun setAnotacao(anotacao: String) {
        context.dataStore.edit {preferences ->
            preferences[ANOTACAO_KEY] = anotacao
        }
    }

}
