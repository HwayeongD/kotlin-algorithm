package com.example.kotlin_algorithm.arithmetic

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val a = nextInt()
    val b = nextInt().toString()

    if(b.length != 3) {
        println("Error")
    }

    println(a * b[2].digitToInt())
    println(a * b[1].digitToInt())
    println(a * b[0].digitToInt())
    println(a * b.toInt())
}