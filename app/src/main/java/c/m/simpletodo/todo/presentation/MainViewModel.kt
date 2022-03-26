package c.m.simpletodo.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import c.m.simpletodo.core.common.Resource
import c.m.simpletodo.todo.domain.use_case.get_todos_use_case.GetTodosUseCase
import c.m.simpletodo.todo.presentation.state.TodoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {
    private val _todoListState = MutableStateFlow(TodoListState())
    val todoListState: StateFlow<TodoListState> = _todoListState.asStateFlow()

    init {
        getTodosListData()
    }

    private fun getTodosListData() {
        viewModelScope.launch {
            getTodosUseCase().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _todoListState.update {
                            it.copy(
                                isLoading = false,
                                isError = true,
                                todoListItem = emptyList()
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _todoListState.update {
                            it.copy(
                                isLoading = true,
                                isError = false,
                                todoListItem = emptyList()
                            )
                        }
                    }
                    is Resource.Success -> {
                        _todoListState.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                todoListItem = result.data ?: emptyList()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }
}