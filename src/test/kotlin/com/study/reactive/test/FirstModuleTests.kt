package com.study.reactive.test
import com.study.reactive.data.InMemoryData.Companion.getUserById
import org.junit.jupiter.api.Test

class FirstModuleTests {

    @Test
    fun printGetUserById() {
        getUserById(1)
            .subscribe({ println(it) })
    }
}