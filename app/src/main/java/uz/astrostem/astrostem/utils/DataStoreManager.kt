package uz.astrostem.astrostem.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import uz.astrostem.astrostem.MyApp
import uz.astrostem.astrostem.R
import java.io.IOException

class DataStoreManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = MyApp.getContext().getString(R.string.trainings)
    )

    private val dataStore = MyApp.getContext().dataStore

    companion object {
        val isTheoreticalLiked =
            booleanPreferencesKey(MyApp.getContext().getString(R.string.about_theoretical))
        val isPracticalLiked =
            booleanPreferencesKey(MyApp.getContext().getString(R.string.about_practical))
        val isLaboratoryLiked =
            booleanPreferencesKey(MyApp.getContext().getString(R.string.about_laboratory))
    }

    suspend fun setTheoreticalLike(isLiked: Boolean) {
        dataStore.edit { pref ->
            pref[isTheoreticalLiked] = isLiked
        }
    }

    fun getTheoreticalLike(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { pref ->
                val isLiked = pref[isTheoreticalLiked] ?: false
                isLiked
            }
    }

    suspend fun setPracticalLike(isLiked: Boolean) {
        dataStore.edit { pref ->
            pref[isPracticalLiked] = isLiked
        }
    }

    fun getPracticalLike(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { pref ->
                val isLiked = pref[isPracticalLiked] ?: false
                isLiked
            }
    }

    suspend fun setLaboratoryLike(isLiked: Boolean) {
        dataStore.edit { pref ->
            pref[isLaboratoryLiked] = isLiked
        }
    }

    fun getLaboratoryLike(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { pref ->
                val isLiked = pref[isLaboratoryLiked] ?: false
                isLiked
            }
    }
}