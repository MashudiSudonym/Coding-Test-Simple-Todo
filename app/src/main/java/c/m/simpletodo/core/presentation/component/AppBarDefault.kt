package c.m.simpletodo.core.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import c.m.simpletodo.R
import c.m.simpletodo.core.presentation.ui.theme.Red500

@Composable
fun AppBarDefault(title: String?) {
    TopAppBar(
        elevation = 6.dp,
        backgroundColor = Red500,
        modifier = Modifier.height(64.dp)
    ) {
        Row {
            Spacer(modifier = Modifier.width(16.dp))

            AppBarTitleText(
                title = title ?: stringResource(id = R.string.app_name),
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}