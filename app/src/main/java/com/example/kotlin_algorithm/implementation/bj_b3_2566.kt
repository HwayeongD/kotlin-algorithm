package com.example.kotlin_algorithm.implementation

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val row = 9
    val column = 9
    var max = -1
    var (mRow, mCol) = Pair(-1, -1)

    repeat(row) { r ->
        repeat(column) { c ->
            val tmp = nextInt()
            if(tmp > max) {
                max = tmp
                mRow = r
                mCol = c
            }
        }
    }

    println("$max\n${mRow + 1} ${mCol + 1}")
}