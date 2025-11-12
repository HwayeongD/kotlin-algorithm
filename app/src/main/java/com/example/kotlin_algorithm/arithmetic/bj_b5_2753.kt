package com.example.kotlin_algorithm.arithmetic

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val year = nextInt()

    val res = if((year % 4 == 0) && (year % 100 != 0 || year % 400 == 0)) 1 else 0

    println(res)
}