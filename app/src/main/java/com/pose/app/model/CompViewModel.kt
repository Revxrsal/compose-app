package com.pose.app.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pose.app.db.ComposeRepository
import com.pose.app.db.entity.CompEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompViewModel @Inject constructor(
    private val repository: ComposeRepository
) : ViewModel() {

    private val _entities = MutableStateFlow<List<CompEntity>>(emptyList())

    val entities = _entities.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getEntities().distinctUntilChanged().collect {
                _entities.value = it
            }
        }
    }

    fun addEntity(entity: CompEntity): Job {
        return viewModelScope.launch { repository.addEntity(entity) }
    }

    fun removeEntity(entity: CompEntity): Job {
        return viewModelScope.launch { repository.deleteEntity(entity) }
    }

    fun updateEntity(entity: CompEntity): Job {
        return viewModelScope.launch { repository.updateEntity(entity) }
    }

    fun deleteAllEntities(): Job {
        return viewModelScope.launch { repository.deleteAllEntities() }
    }
}
