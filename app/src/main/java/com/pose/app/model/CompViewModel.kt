package com.pose.app.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pose.app.db.CompDao
import com.pose.app.db.entity.CompEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompViewModel @Inject constructor(
    private val dao: CompDao
) : ViewModel() {

    private val _entities = MutableStateFlow<List<CompEntity>>(emptyList())

    val entities = _entities.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dao.getEntities()
                .flowOn(Dispatchers.IO)
                .conflate()
                .distinctUntilChanged().collect {
                    _entities.value = it
                }
        }
    }

    fun addEntity(entity: CompEntity): Job {
        return viewModelScope.launch { dao.addEntity(entity) }
    }

    fun removeEntity(entity: CompEntity): Job {
        return viewModelScope.launch { dao.deleteEntity(entity) }
    }

    fun updateEntity(entity: CompEntity): Job {
        return viewModelScope.launch { dao.updateEntity(entity) }
    }

    fun deleteAllEntities(): Job {
        return viewModelScope.launch { dao.deleteAllEntities() }
    }
}
