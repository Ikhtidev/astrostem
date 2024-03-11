package uz.astrostem.astrostem.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.astrostem.astrostem.utils.DataStoreManager

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = DataStoreManager()

    val getTheoreticalLike = dataStore.getTheoreticalLike().asLiveData(Dispatchers.IO)

    fun setTheoreticalLike(isLike: Boolean) {
        viewModelScope.launch {
            dataStore.setTheoreticalLike(isLike)
        }
    }

    val getPracticalLike = dataStore.getPracticalLike().asLiveData(Dispatchers.IO)

    fun setPracticalLike(isLike: Boolean) {
        viewModelScope.launch {
            dataStore.setPracticalLike(isLike)
        }
    }

    val getLaboratoryLike = dataStore.getLaboratoryLike().asLiveData(Dispatchers.IO)

    fun setLaboratoryLike(isLike: Boolean) {
        viewModelScope.launch {
            dataStore.setLaboratoryLike(isLike)
        }
    }

    val getTvEmptySavedCourses = dataStore.getTvEmptySavedCourses().asLiveData(Dispatchers.IO)
}