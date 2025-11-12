package com.example.kotlin_algorithm.implementation

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val res = nextInt() - nextInt()

    println(
        when {
            res > 0 -> ">"
            res < 0 -> "<"
            else -> "=="
        }
    )

//    val a = nextInt()
//    val b = nextInt()
//
//    println(
//        when {
//            a > b -> ">"
//            a < b -> "<"
//            else -> "=="
//        }
//    )
}