package com.jdr.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jdr.rickandmorty.ui.theme.RickAndMortyTheme
import com.jdr.rickandmorty.ui.view.characters.CharacterScreen
import com.jdr.rickandmorty.ui.view.characters.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.falling_stars_background))

                    LottieAnimation(
                        modifier = Modifier.fillMaxSize(),
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        contentScale = ContentScale.FillBounds,
                        speed = 2f
                    )

                    val viewModel = viewModel<CharacterViewModel>()
                    CharacterScreen(viewModel = viewModel)
                }
            }
        }
    }
}