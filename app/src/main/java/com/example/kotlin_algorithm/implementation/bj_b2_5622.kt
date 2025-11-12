package com.example.kotlin_algorithm.implementation

import java.util.Scanner

fun alphaToInt(ch: Char) : Int {
    return when(ch) {
        in 'A' .. 'C' -> 2
        in 'D' .. 'F' -> 3
        in 'G' .. 'I' -> 4
        in 'J' .. 'L' -> 5
        in 'M' .. 'O' -> 6
        in 'P' .. 'S' -> 7
        in 'T' .. 'V' -> 8
        in 'W' .. 'Z' -> 9
        else -> 0
    }
}

fun main() = with(Scanner(System.`in`)) {
    val word = readln()
    var res = 0
    word.forEach {
        res += (alphaToInt(it) + 1)
    }

    println(res)
}