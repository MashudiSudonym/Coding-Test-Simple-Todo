package c.m.simpletodo.todo.domain.use_case.get_todos_use_case

import c.m.simpletodo.core.common.Resource
import c.m.simpletodo.todo.domain.model.TodoListItem
import c.m.simpletodo.todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodosUseCase(private val todoRepository: TodoRepository) {
    suspend operator fun invoke(): Flow<Resource<List<TodoListItem>>> = todoRepository.getTodos()
}