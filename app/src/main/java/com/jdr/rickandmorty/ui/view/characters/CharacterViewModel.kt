package com.jdr.rickandmorty.ui.view.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdr.rickandmorty.data.paginator.DefaultPaginator
import com.jdr.rickandmorty.data.repository.CharacterRepository
import com.jdr.rickandmorty.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow<String?>(null)
    val searchText = _searchText.asStateFlow()

    private var searchJob: Job? = null

    var state by mutableStateOf(ScreenState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextPage ->
            repository.getCharacters(pages = nextPage, searchText.value)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty(),
                error = null
            )
        }
    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            state = state.copy(page = 1, items = mutableListOf())
            paginator.reset()
            loadNextItems()
        }

    }

}

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<CharacterModel> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)