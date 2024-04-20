package com.example.githubmvi.data.repository.impl

import com.example.githubmvi.data.model.response.RepositoryDto
import com.example.githubmvi.data.model.response.User
import com.example.githubmvi.data.model.response.UserDetailDto
import com.example.githubmvi.data.repository.UserRepository
import com.example.githubmvi.data.source.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): UserRepository {
    override suspend fun getUsers(): Result<List<User>> = makeApiCall(dispatcher) {
        userService.getUsers(since = 51234842)
    }

    override suspend fun getUser(username: String): Result<UserDetailDto?> = makeApiCall(dispatcher) {
        userService.getUserDetail(username)
    }

    override suspend fun getRepositories(
        username: String,
        sort: String,
        order: String
    ): Result<List<RepositoryDto>> = makeApiCall(dispatcher) {
        userService.getRepositories(username, sort, order)
    }
}

@OptIn(ExperimentalContracts::class)
suspend fun <T> makeApiCall(
    dispatcher: CoroutineDispatcher,
    test: String? = null,
    call: suspend () -> T
): Result<T> {
    contract { returns(true) implies (test != null) }
    return runCatching {
        withContext(dispatcher) {
            call.invoke()
        }
    }
}

/*fun foo(s: String?) {
    if(s != null) s.length
}*/

fun String?.isNotNull(): Boolean = this != null
fun foo(s: String?) {
    require(s is String)
    s.length
}

@OptIn(ExperimentalContracts::class)
fun require(s: String?) {
    // This is a syntax form which tells the compiler:
    // "if this function returns successfully, then the passed 'condition' is true"
    contract { returns(true) implies (s != null) }
    if (s == null) throw IllegalArgumentException("Condition not satisfied")
}