package c.m.simpletodo.todo.di

import c.m.simpletodo.todo.domain.repository.TodoRepository
import c.m.simpletodo.todo.domain.use_case.get_todo_item_use_case.GetTodoItemUseCase
import c.m.simpletodo.todo.domain.use_case.get_todos_use_case.GetTodosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetTodosUseCase(todoRepository: TodoRepository): GetTodosUseCase {
        return GetTodosUseCase(todoRepository)
    }

    @Provides
    @Singleton
    fun provideGetTodoItemUseCase(todoRepository: TodoRepository): GetTodoItemUseCase {
        return GetTodoItemUseCase(todoRepository)
    }
}