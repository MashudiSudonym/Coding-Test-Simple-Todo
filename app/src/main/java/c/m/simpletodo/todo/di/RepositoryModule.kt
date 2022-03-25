package c.m.simpletodo.todo.di

import c.m.simpletodo.core.data.remote.TodoApi
import c.m.simpletodo.todo.data.repository.TodoRepositoryImpl
import c.m.simpletodo.todo.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideTodoRepository(todoApi: TodoApi): TodoRepository = TodoRepositoryImpl(todoApi)
}