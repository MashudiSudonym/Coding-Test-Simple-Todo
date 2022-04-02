package c.m.simpletodo.todo.data.remote

import c.m.simpletodo.todo.data.remote.dto.TodoListDTOItem
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApi {
    @GET("/todos")
    suspend fun todos(): List<TodoListDTOItem>

    @GET("/todos/{todo_id}")
    suspend fun todoDetail(
        @Path("todo_id") todo_id: Int
    ): TodoListDTOItem
}