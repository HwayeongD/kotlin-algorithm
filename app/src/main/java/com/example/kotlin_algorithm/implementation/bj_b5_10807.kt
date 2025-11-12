package com.example.kotlin_algorithm.implementation

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val arr = ArrayList<Int>()

    repeat(n) { arr.add(nextInt()) }

    val v = nextInt()
    println(arr.count { it == v })
}