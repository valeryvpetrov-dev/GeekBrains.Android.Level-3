package ru.geekbrains.android.level3.valeryvpetrov.presentation.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.Retrofit
import ru.geekbrains.android.level3.valeryvpetrov.data.local.datasource.UserLocalDataSource
import ru.geekbrains.android.level3.valeryvpetrov.data.local.realm.datasource.UserRealmDataSource
import ru.geekbrains.android.level3.valeryvpetrov.data.local.room.datasource.UserRoomDataSource
import ru.geekbrains.android.level3.valeryvpetrov.data.local.room.db.GitHubDatabase
import ru.geekbrains.android.level3.valeryvpetrov.data.remote.datasource.UserRemoteDataSource
import ru.geekbrains.android.level3.valeryvpetrov.data.repository.UserRepository
import ru.geekbrains.android.level3.valeryvpetrov.domain.usecase.DeleteUserUseCase
import ru.geekbrains.android.level3.valeryvpetrov.domain.usecase.GetUserLocalUseCase
import ru.geekbrains.android.level3.valeryvpetrov.domain.usecase.GetUserRemoteUseCase
import ru.geekbrains.android.level3.valeryvpetrov.domain.usecase.SaveUserUseCase
import ru.geekbrains.android.level3.valeryvpetrov.util.AppExecutors
import ru.geekbrains.android.level3.valeryvpetrov.util.ConnectivityManager

class ViewModelFactory(
    private val connectivityManager: ConnectivityManager,
    private val getUserRemoteUseCase: GetUserRemoteUseCase,
    private val getUserLocalUseCase: GetUserLocalUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModelProvider.NewInstanceFactory() {

    companion object {

        private var instance: ViewModelFactory? = null

        fun getInstance(
            connectivityManager: ConnectivityManager,
            appExecutors: AppExecutors,
            retrofitGithub: Retrofit,
            roomGitHubDatabase: GitHubDatabase
        ): ViewModelFactory {
            val userRemoteRepository =
                UserRemoteDataSource(
                    retrofitGithub
                )
            val userLocalRetrofit = UserLocalDataSource(
                UserRealmDataSource(),
                UserRoomDataSource(roomGitHubDatabase.userDao(), roomGitHubDatabase.repoDao())
            )
            val userRepository = UserRepository.getInstance(userRemoteRepository, userLocalRetrofit)
            val networkExecutionScheduler = appExecutors.networkIo
            val diskExecutionScheduler = appExecutors.diskIo
            val mainThreadExecutionScheduler = appExecutors.mainThread

            if (instance == null) {
                instance = ViewModelFactory(
                    connectivityManager,
                    GetUserRemoteUseCase(
                        networkExecutionScheduler,
                        mainThreadExecutionScheduler,
                        userRepository
                    ),
                    GetUserLocalUseCase(
                        networkExecutionScheduler,
                        mainThreadExecutionScheduler,
                        userRepository
                    ),
                    SaveUserUseCase(
                        diskExecutionScheduler,
                        mainThreadExecutionScheduler,
                        userRepository
                    ),
                    DeleteUserUseCase(
                        diskExecutionScheduler,
                        mainThreadExecutionScheduler,
                        userRepository
                    )
                )
            }
            return instance as ViewModelFactory
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(
                        connectivityManager,
                        getUserRemoteUseCase,
                        getUserLocalUseCase,
                        saveUserUseCase,
                        deleteUserUseCase
                    )
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class $modelClass")
            }
        } as T
}