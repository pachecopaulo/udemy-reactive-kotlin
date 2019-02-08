package com.study.reactive.data

import com.study.reactive.exception.UserNotFoundException
import com.study.reactive.model.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class InMemoryData {
    companion object {
        fun getAllUser(): Flowable<User> =
            Flowable.fromIterable(userMap.values)

        fun getUserById(userId: Int): Maybe<User> =
            getAllUser()
                .filter { it.id == userId }
                .firstElement()

        fun getPointsForUserId(userId: Int): Single<Int> =
            when (userPointsMap.containsKey(userId)) {
                true -> Single.just(userPointsMap[userId])
                else -> Single.error { UserNotFoundException("Id $userId is not found") }
            }

        fun addUser(user: User): Completable =
            Completable.fromAction { userMap[user.id] = user }

            private val userMap = mutableMapOf(
                1 to User(1, "Paulo"),
                2 to User(2, "Eduardo"),
                3 to User(3, "Pacheco")
            )
            private val userPointsMap = mapOf(
                1 to 40,
                2 to 23,
                3 to 0,
                4 to 5
            )
        }
}