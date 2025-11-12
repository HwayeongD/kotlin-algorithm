package com.example.kotlin_algorithm.arithmetic

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val a = nextInt()
    val b = nextInt()
    println(a + b)
    println(a - b)
    println(a * b)
    println(a / b)
    println(a % b)
}