package c.m.simpletodo.todo.domain.use_case.get_todo_item_use_case

import c.m.simpletodo.core.common.Resource
import c.m.simpletodo.todo.domain.model.TodoListItem
import c.m.simpletodo.todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class GetTodoItemUseCase(private val todoRepository: TodoRepository) {
    suspend operator fun invoke(todoId: Int): Flow<Resource<TodoListItem>> = todoRepository.getTodoItem(todoId)
}