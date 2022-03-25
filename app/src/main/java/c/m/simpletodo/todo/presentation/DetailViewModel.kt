package c.m.simpletodo.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import c.m.simpletodo.core.common.Resource
import c.m.simpletodo.todo.domain.model.TodoListItem
import c.m.simpletodo.todo.domain.use_case.get_todo_item_use_case.GetTodoItemUseCase
import c.m.simpletodo.todo.presentation.state.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getTodoItemUseCase: GetTodoItemUseCase) :
    ViewModel() {
    private val _detailState = MutableStateFlow(DetailState())
    val detailState: StateFlow<DetailState> = _detailState

    fun getTodoDetail(todoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getTodoItemUseCase(todoId = todoId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _detailState.update {
                            it.copy(
                                isLoading = false,
                                isError = true,
                                todoItem = TodoListItem()
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _detailState.update {
                            it.copy(
                                isLoading = true,
                                isError = false,
                                todoItem = TodoListItem()
                            )
                        }
                    }
                    is Resource.Success -> {
                        _detailState.update {
                            it.copy(
                                isLoading = false,
                                isError = false,
                                todoItem = result.data ?: TodoListItem()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }
}