package com.example.kotlin_algorithm.string

fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val num = readln()
    val sum = num.sumOf { it.digitToInt() }
    println(sum)
}