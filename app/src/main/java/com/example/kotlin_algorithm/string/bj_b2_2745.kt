package com.example.kotlin_algorithm.string

import java.util.Scanner
//import kotlin.math.pow

fun main() = with(Scanner(System.`in`)) {
    // 2안
    // 시간은 똑같네..
    val (n, b) = nextLine().split(' ')
    val nb = b.toInt()
    var res = 0
    var powed = 1
    n.reversed().forEach {
        val num = when {
            it.isDigit() -> { it.digitToInt() }
            it.isLetter() -> { it - 'A' + 10 }
            else -> 0
        }

        res += powed * num
        powed *= nb
    }

    println(res)

    // 1안
// 너무 느리다.. 다시 풀자.
// pow 말고 뒤에서부터 하나씩 곱해가는 식으로 풀자.
//    val (n, b) = nextLine().split(' ')
//    val nb = b.toInt()
//    var res = 0
//
//    n.forEachIndexed { pos, ch ->
//        val num = when {
//            ch.isDigit() -> {
//                ch.digitToInt()
//            }
//            ch.isLetter() -> {
//                ch - 'A' + 10
//            }
//            else -> 0
//        }
//
//        res += (nb.toDouble().pow(n.length - pos - 1).toInt() * num)
//    }
//
//    println(res)
}