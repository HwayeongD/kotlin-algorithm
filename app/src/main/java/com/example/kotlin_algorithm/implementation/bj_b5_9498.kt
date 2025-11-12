package com.example.kotlin_algorithm.implementation

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val score = nextInt()

    println(
        when {
            score >= 90 -> "A"
            score >= 80 -> "B"
            score >= 70 -> "C"
            score >= 60 -> "D"
            else -> "F"
        }
    )
}