package c.m.simpletodo.todo.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import c.m.simpletodo.R
import c.m.simpletodo.todo.presentation.component.AppBarWithBackButton
import c.m.simpletodo.todo.presentation.component.LoadingIndicator

@Composable
fun DetailScreen(navController: NavController, todoId: Int) {
    val detailViewModel: DetailViewModel = hiltViewModel()

    // load data
    detailViewModel.getTodoDetail(todoId)

    Scaffold(topBar = {
        AppBarWithBackButton(
            title = stringResource(id = R.string.app_name),
            navController = navController
        )
    }) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            val detailState by detailViewModel.detailState.collectAsState()

            when {
                detailState.isLoading -> LoadingIndicator(
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                )
                detailState.isError -> Text(
                    text = "Error/No Data", modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                )
                else -> {
                    Text(text = detailState.todoItem.title ?: "")
                    Text(text = "Status : ${detailState.todoItem.completed}")
                    Text(text = "User ID: ${detailState.todoItem.userId}")
                }
            }
        }
    }
}