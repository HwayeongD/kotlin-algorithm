package com.example.kotlin_algorithm.implementation

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()

    // 3안 - Kotlin 답게
    repeat(n) { i ->
        println("*".repeat(i + 1))
    }

    // 2안
//    for (i in 1..n) {
//        println("*".repeat(i))
//    }

    // 1안
//    for(cnt in 1..n) {
//        for (tmp in 1..cnt) {
//            print("*")
//        }
//        println()
//    }
}