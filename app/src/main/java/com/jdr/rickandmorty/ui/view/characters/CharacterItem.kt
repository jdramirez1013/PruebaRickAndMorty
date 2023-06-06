package com.jdr.rickandmorty.ui.view.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jdr.rickandmorty.R
import com.jdr.rickandmorty.model.CharacterModel
import com.jdr.rickandmorty.model.LocationModel
import com.jdr.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun CharacterItem(
    character: CharacterModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row( modifier = Modifier
            .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.Center) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .height(IntrinsicSize.Max)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AsyncImage(
                    model = character.image,
                    contentDescription = character.name,
                    placeholder = painterResource(R.drawable.portal),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${character.species} - ${character.status}",
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun CharacterPreview() {
    RickAndMortyTheme {
        CharacterItem(character = CharacterModel(
            id = 1,
            name = "Rick",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = LocationModel(
                id = 1,
                name = "Earth",
                url = ""
            ),
            location = LocationModel(
                id = 1,
                name = "Earth",
                url = ""
            ),
            image = "https://rickandmortyapi.com/api/character/avatar/2.jpeg"
        ))
    }
}