package c.m.simpletodo.core.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import c.m.simpletodo.core.presentation.ui.theme.Red500
import c.m.simpletodo.core.presentation.ui.theme.White

@Composable
fun AppBarWithBackButton(title: String, navController: NavController) {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = Red500,
        modifier = Modifier.height(64.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.width(16.dp))

            Image(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    White
                ),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.width(16.dp))

            AppBarTitleText(
                title = title,
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}