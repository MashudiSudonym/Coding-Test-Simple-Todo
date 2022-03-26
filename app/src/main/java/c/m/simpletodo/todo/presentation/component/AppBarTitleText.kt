package c.m.simpletodo.todo.presentation.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import c.m.simpletodo.R
import c.m.simpletodo.core.presentation.ui.theme.White

@Composable
fun AppBarTitleText(title: String?, modifier: Modifier) {
    Text(
        modifier = modifier,
        text = title ?: stringResource(id = R.string.app_name),
        color = White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}