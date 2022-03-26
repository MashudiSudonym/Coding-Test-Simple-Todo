package c.m.simpletodo.todo.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import c.m.simpletodo.R
import c.m.simpletodo.todo.presentation.component.AppBarDefault
import c.m.simpletodo.todo.presentation.component.LoadingIndicator

@Composable
fun MainScreen(navController: NavController) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val todoListState by mainViewModel.todoListState.collectAsState()

    Scaffold(topBar = {
        AppBarDefault(title = stringResource(id = R.string.app_name))
    }) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
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
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .clickable {
                                        navController.navigate("detail/${item.id}") {
                                            popUpTo(
                                                "main"
                                            )
                                        }
                                    },
                                shape = MaterialTheme.shapes.medium,
                                elevation = 2.dp
                            ) {
                                Column(modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()) {
                                    Text(
                                        text = item.title ?: "",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(text = "Status : ${item.completed}")
                                    Text(text = "User ID: ${item.userId}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}