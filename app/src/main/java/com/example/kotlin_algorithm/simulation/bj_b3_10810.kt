package com.example.kotlin_algorithm.simulation

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val m = nextInt()

    val baskets = ArrayList<Int>(n)
    repeat(n) {
        baskets.add(0)
    }

    repeat(m) {
        val i = nextInt() - 1
        val j = nextInt() - 1
        val k = nextInt()

        for(idx in i .. j) {
            baskets[idx] = k
        }
    }

    baskets.forEach { print("$it ") }
}