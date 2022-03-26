package c.m.simpletodo.todo.presentation.component

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoadingIndicator(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
    )
}