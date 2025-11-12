package com.example.kotlin_algorithm.arithmetic

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    println(nextInt() / nextInt().toDouble())
    /**
     * nextInt() 만 쓰면 Int형이라 정수형으로 출력
     * .toFloat()으로 캐스팅하면 10^(-6)까지만 출력되어서 조건에 맞지 않아 오답
     * .toDouble()로 캐스팅해야 소수점 ~째자리까지 출력하여 정답
     */
}