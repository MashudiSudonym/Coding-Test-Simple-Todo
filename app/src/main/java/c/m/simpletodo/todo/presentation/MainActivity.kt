package c.m.simpletodo.todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import c.m.simpletodo.core.presentation.ui.theme.TodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            TodoTheme {
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(navController)
                    }
                    composable(
                        "detail/{todo_id}",
                        listOf(navArgument("todo_id") {
                            type = NavType.IntType
                        })
                    ) { navBackStackEntry ->
                        DetailScreen(
                            navController,
                            navBackStackEntry.arguments?.getInt("todo_id") ?: 0
                        )
                    }
                }
            }
        }
    }
}