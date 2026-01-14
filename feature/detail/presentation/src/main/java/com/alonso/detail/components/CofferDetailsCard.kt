package com.alonso.detail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.navigation.CoffeeItem
import com.alonso.ui_components.components.CoffeeRating

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CofferDetailsCard(
    drink: CoffeeItem?,
    modifier: Modifier = Modifier
) {

    AnimatedContent(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(
                color = Color(0xFFF9F6F3),
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .padding(16.dp),
        targetState = drink?.id,
        transitionSpec = { slideInVertically { -it }.togetherWith(slideOutVertically { it }) },
        label = "coffee_details_animation"
    ) { currentScrollDirection ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 35.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                TitleDetailCard(
                    name = drink?.name.orEmpty(),
                    volume = drink?.volume.toString(),
                    price = drink?.price ?: 0.0,
                    category = drink?.category.orEmpty(),
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = drink?.description.orEmpty(),
                    style = CoffeeGoTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Start,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth()

                )

            }
            CoffeeRating(
                modifier = Modifier.align(Alignment.BottomEnd),
                value = drink?.qualification.toString()
            )
        }
    }


}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true, name = "Full Screen")
@Composable
private fun CofferDetailsCardPreview() {
    CofferDetailsCard(
        CoffeeItem(
            id = "j4785489fd-dvnkd83vkd-89374jdf",
            name = "Espresso",
            price = 2.50,
            description = "Un shot concentrado de café con un sabor intenso y una crema rica.",
            image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
            qualification = 5.0,
            category = "Cafe",
            volume = "32lm",
            favorite = false
        ),
    )


}
