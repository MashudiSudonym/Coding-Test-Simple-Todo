package c.m.simpletodo.todo.presentation.state

import c.m.simpletodo.todo.domain.model.TodoListItem

data class DetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val todoItem: TodoListItem = TodoListItem()
)
