package com.tirth.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tirth.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                ArtAppScreen()
            }
        }
    }
}

// ArtAppScreen composable
@Composable
fun ArtAppScreen(
    modifier: Modifier = Modifier,
) {
    val firstImage = R.drawable.tirth
    val secondImage = R.drawable.tirth_2
    val thirdImage = R.drawable.ab1_inversions

    var currentImage by remember {
        mutableStateOf(firstImage)
    }

    var title by remember {
        mutableStateOf(R.string.tirth)
    }

    var description by remember {
        mutableStateOf(R.string.tirth_description)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(0.dp, 60.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtImage(currentImage = currentImage)
        Spacer(modifier = Modifier.padding(16.dp))
        ArtTitle(title = title)
        ArtDescription(description = description)
        Spacer(modifier = Modifier.padding(8.dp))
        Row {
            Button(
                onClick = {
                    when (currentImage) {
                        firstImage -> {
                            currentImage = thirdImage
                            title = R.string.tirth_3
                            description = R.string.tirth_3_description
                        }

                        secondImage -> {
                            currentImage = firstImage
                            title = R.string.tirth
                            description = R.string.tirth_description
                        }

                        thirdImage -> {
                            currentImage = secondImage
                            title = R.string.tirth_2
                            description = R.string.tirth_2_description
                        }
                    }
                }
            ) {
                Text(text = "Previous")
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Button(
                onClick = {
                    when (currentImage) {
                        firstImage -> {
                            currentImage = secondImage
                            title = R.string.tirth_2
                            description = R.string.tirth_2_description
                        }

                        secondImage -> {
                            currentImage = thirdImage
                            title = R.string.tirth_3
                            description = R.string.tirth_3_description
                        }

                        thirdImage -> {
                            currentImage = firstImage
                            title = R.string.tirth
                            description = R.string.tirth_description
                        }
                    }
                }
            ) {
                Text(text = "Next")
            }
        }
    }
}

// Preview for ArtAppScreen
@Preview(showBackground = true)
@Composable
fun ArtAppScreenPreview() {
    ArtAppScreen()
}

// ArtImage composable
@Composable
fun ArtImage(
    modifier: Modifier = Modifier,
    @DrawableRes currentImage: Int
) {
    Card(
        modifier = modifier
            .padding(30.dp)
            .shadow(10.dp)
    ) {
        Image(
            painter = painterResource(id = currentImage),
            contentDescription = null,
            modifier = modifier
                .padding(40.dp)
        )
    }
}

@Composable
fun ArtTitle(
    modifier: Modifier = Modifier
        .padding(16.dp),
    @StringRes title: Int,
) {
    Text(
        text = stringResource(id = title),
        style = MaterialTheme.typography.titleMedium,
    )
}


// ArtDescription composable
@Composable
fun ArtDescription(
    modifier: Modifier = Modifier,
    @StringRes description: Int,
) {
    Text(
        text = stringResource(id = description),
    )
}
