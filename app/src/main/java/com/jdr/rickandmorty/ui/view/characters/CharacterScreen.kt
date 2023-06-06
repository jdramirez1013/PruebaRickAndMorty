package com.jdr.rickandmorty.ui.view.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdr.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun CharacterScreen(viewModel: CharacterViewModel){
    val state = viewModel.state
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(state.items.size){ i ->
            val item = state.items[i]
            if(i >= state.items.size - 1 && state.endReached.not() && state.isLoading.not()){
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
        }
    }
}

@Preview
@Composable
fun previewCharacterScreen() {
    RickAndMortyTheme {
        //CharacterScreen(viewModel = )
    }
}