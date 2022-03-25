package c.m.simpletodo.todo.domain.model

data class TodoListItem(
    val completed: Boolean? = false,
    val id: Int? = 0,
    val title: String? = "",
    val userId: Int? = 0
)
