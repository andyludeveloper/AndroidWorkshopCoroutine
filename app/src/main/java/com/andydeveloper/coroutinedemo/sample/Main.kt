package com.andydeveloper.coroutinedemo.sample

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

fun main() {
    //1. Light thread
    val threadExecuteTime = measureTimeMillis {
        repeat(100_000) {
            thread {
                // Do nothing
            }.run()
        }
    }
    println("Completed, Thread take: $threadExecuteTime ms")

    val coroutinesExecuteTime = measureTimeMillis {
        runBlocking {
            repeat(100_000) { // launch a lot of coroutines
                launch {
                    //Do nothing
                }
            }
        }
    }
    println("Completed, Coroutine take: $coroutinesExecuteTime ms")

    //2. Structured Concurrency
    //3. built-in cancel()
    runBlocking {
        val job1 = launch {
            delay(100)
            println("Job1 done")
        }

        val job2 = launch {
            delay(1000)
            println("Job2 done")
        }
        println("Outer coroutine done")
        delay(300)
        job2.cancel()
    }

    // Suspend function
    runBlocking {
        //Coroutine1
        launch {
            run1()
        }
        //Coroutine2
        launch {
            run2()
        }
        println("Start")
    }

}

suspend fun run1() {
    delay(1000)
    println("done1")
}

suspend fun run2() {
    delay(500)
    println("done2")
}