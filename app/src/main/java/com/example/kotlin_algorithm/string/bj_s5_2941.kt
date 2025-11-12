package com.example.kotlin_algorithm.string

import java.util.Scanner

val alphas = listOf("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=")

fun main() = with(Scanner(System.`in`)) {
    val inp = nextLine()
    var str = inp

    alphas.forEach {
        str = str.replace(it, "a")
    }

    println(str.length)
}