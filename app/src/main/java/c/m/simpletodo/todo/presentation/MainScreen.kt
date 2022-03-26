package c.m.simpletodo.todo.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
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
import c.m.simpletodo.todo.presentation.custom.AppBarDefault
import c.m.simpletodo.todo.presentation.custom.LoadingIndicator

@Composable
fun MainScreen(navController: NavController) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val todoListState by mainViewModel.todoListState.collectAsState()

    Scaffold(topBar = {
        AppBarDefault(title = stringResource(id = R.string.app_name))
    }) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                todoListState.isLoading -> LoadingIndicator(
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                )
                todoListState.isError -> Text(
                    text = "Error/No Data", modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                )
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        this.items(items = todoListState.todoListItem) { item ->
                            Spacer(modifier = Modifier.height(8.dp))
                            Column(modifier = Modifier
                                .clickable {
                                    navController.navigate("detail/${item.id}") {
                                        popUpTo(
                                            "main"
                                        )
                                    }
                                }
                                .fillMaxWidth()) {
                                Text(text = item.title ?: "")
                                Text(text = "Status : ${item.completed}")
                                Text(text = "User ID: ${item.userId}")
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    }
}