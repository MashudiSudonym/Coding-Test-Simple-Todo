package c.m.simpletodo.todo.data.remote.dto

import c.m.simpletodo.todo.domain.model.TodoListItem
import com.google.gson.annotations.SerializedName

data class TodoListDTOItem(
    @SerializedName("completed")
    val completed: Boolean? = false,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("userId")
    val userId: Int? = 0
) {
    fun toTodoListItem(): TodoListItem {
        return TodoListItem(
            completed = completed,
            id = id,
            title = title,
            userId = userId
        )
    }
}