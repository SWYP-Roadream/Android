package com.yeogijeogi.data.database

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.yeogijeogi.domain.database.SessionStorage
import com.yeogijeogi.domain.model.data.AuthInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject

class DataStoreSessionStorage @Inject constructor(
    private val dataStore: DataStore<Preferences>
): SessionStorage {

    object PreferencesKey {
        val TOKEN = stringPreferencesKey("TOKEN")
    }

    override suspend fun getToken(): Flow<AuthInfo?> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKey.TOKEN]?.let { token ->
                Json.decodeFromString<AuthInfo>(token)
            }
        }
    }

    override suspend fun setToken(info: AuthInfo?) {
        dataStore.edit { prefs ->
            prefs[PreferencesKey.TOKEN] = Json.encodeToString(info)
        }
    }
}