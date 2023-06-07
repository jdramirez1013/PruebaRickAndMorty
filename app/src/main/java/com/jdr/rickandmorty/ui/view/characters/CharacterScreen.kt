package com.jdr.rickandmorty.ui.view.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdr.rickandmorty.R
import com.jdr.rickandmorty.ui.theme.RickAndMortyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
    viewModel: CharacterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val state = viewModel.state
    val searchText by viewModel.searchText.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = searchText ?: "",
            onValueChange = viewModel::onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = {
                Text(text = stringResource(id = R.string.search))
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.items.size) { i ->
                val item = state.items[i]
                if (i >= state.items.size - 1 && state.endReached.not() && state.isLoading.not() && state.error.isNullOrEmpty()) {
                    viewModel.loadNextItems()
                }
                CharacterItem(character = item)
            }
            item {
                if (state.isLoading) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                if (state.error.isNullOrEmpty().not()) {
                    Text(
                        text = state.error.toString(),
                        modifier = Modifier.fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}