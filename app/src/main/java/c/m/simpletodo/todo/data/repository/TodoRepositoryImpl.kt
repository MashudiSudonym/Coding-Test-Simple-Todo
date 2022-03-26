package c.m.simpletodo.todo.data.repository

import c.m.simpletodo.core.common.Resource
import c.m.simpletodo.core.data.remote.TodoApi
import c.m.simpletodo.todo.domain.model.TodoListItem
import c.m.simpletodo.todo.domain.repository.TodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class TodoRepositoryImpl(private val todoApi: TodoApi) : TodoRepository {
    override suspend fun getTodos(): Flow<Resource<List<TodoListItem>>> = flow {
        emit(Resource.Loading())

        try {
            val todoListData = todoApi.todos().map { it.toTodoListItem() }
            emit(Resource.Success(todoListData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Http Exception Error"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "IO Exception Error"))
        } catch (e: UnknownHostException) {
            emit(Resource.Error(message = "Unknown Host Exception Error"))
        }
    }

    override suspend fun getTodoItem(todoId: Int): Flow<Resource<TodoListItem>> = flow {
        emit(Resource.Loading())

        try {
            val todoListItemData = todoApi.todoDetail(todoId).toTodoListItem()
            emit(Resource.Success(todoListItemData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "Http Exception Error"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "IO Exception Error"))
        } catch (e: UnknownHostException) {
            emit(Resource.Error(message = "Unknown Host Exception Error"))
        }

    }
}