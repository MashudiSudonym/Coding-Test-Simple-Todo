package c.m.simpletodo.todo.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import c.m.simpletodo.core.presentation.ui.theme.Red500
import c.m.simpletodo.core.presentation.ui.theme.White

@Composable
fun MainScreen(navController: NavController) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val todoListState by mainViewModel.todoListState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Red500, elevation = 0.dp) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Simple Todo", fontWeight = FontWeight.Bold, color = White)
            }
        }
    }) {
        Column(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                todoListState.isLoading -> CircularProgressIndicator(
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