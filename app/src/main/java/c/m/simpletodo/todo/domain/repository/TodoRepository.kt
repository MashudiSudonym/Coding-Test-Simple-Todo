package c.m.simpletodo.todo.domain.repository

import c.m.simpletodo.core.common.Resource
import c.m.simpletodo.todo.domain.model.TodoListItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getTodos(): Flow<Resource<List<TodoListItem>>>
    suspend fun getTodoItem(todoId: Int): Flow<Resource<TodoListItem>>
}