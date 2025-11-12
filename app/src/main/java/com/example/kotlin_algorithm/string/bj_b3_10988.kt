package com.example.kotlin_algorithm.string

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val inp = nextLine()

    val res = inp == inp.reversed()
    println(if(res) 1 else 0)
}