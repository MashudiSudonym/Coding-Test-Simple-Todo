package c.m.simpletodo.todo.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun DetailScreen(navController: NavController, todoId: Int) {
    val detailViewModel: DetailViewModel = hiltViewModel()
    val detailState by detailViewModel.detailState.collectAsState()

    // Load Data
    detailViewModel.getTodoDetail(todoId)

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Red500, elevation = 0.dp) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Detail", fontWeight = FontWeight.Bold, color = White)
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
                detailState.isLoading -> CircularProgressIndicator(
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