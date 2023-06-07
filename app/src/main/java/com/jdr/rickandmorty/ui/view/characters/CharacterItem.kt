package com.jdr.rickandmorty.ui.view.characters

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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

    var showDetail by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .clickable { showDetail = !showDetail },
        horizontalArrangement = Arrangement.Center,

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(IntrinsicSize.Max),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                placeholder = painterResource(R.drawable.portal),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .zIndex(1f)
                    .width(150.dp)
                    .height(150.dp)
                    .clip(CircleShape),
            )
            Card(
                modifier = modifier
                    .zIndex(0f)
                    .absoluteOffset(y = (-16).dp),
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Canvas(modifier = Modifier.padding( end = 4.dp, top = 6.dp).size(10.dp) ){
                        drawCircle(
                            color = when (character.status) {
                                "Alive" -> {
                                    Color(85, 204, 68)
                                }
                                "Dead" -> {
                                    Color.Red
                                }
                                else -> {
                                    Color.Gray
                                }
                            }
                        )
                    }
                    Text(
                        text = "${character.status} - ${character.species}",
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
                AnimatedVisibility(visible = showDetail) {
                    Text(
                        text = "${stringResource(id = R.string.type)} ${character.type}",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                    )
                }
                AnimatedVisibility(visible = showDetail) {
                    Text(
                        text = "${stringResource(id = R.string.gender)} ${character.gender}",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                    )
                }
                AnimatedVisibility(visible = showDetail) {
                    Text(
                        text = "${stringResource(id = R.string.origin)} ${character.origin.name}",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                    )
                }
                AnimatedVisibility(visible = showDetail) {
                    Text(
                        text = "${stringResource(id = R.string.location)} ${character.location.name}",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp),
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun CharacterPreview() {
    RickAndMortyTheme {
        CharacterItem(
            character = CharacterModel(
                id = 1,
                name = "Rick",
                status = "Alive",
                species = "Human",
                type = "Humanoide",
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
            )
        )
    }
}