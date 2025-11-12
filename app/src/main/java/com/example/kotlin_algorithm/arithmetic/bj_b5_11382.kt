package com.example.kotlin_algorithm.arithmetic

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    println(nextLong() + nextLong() + nextLong())
    /**
     * nextInt() 로 했더니 런타임 에러(InputMismatch) 발생
     * 조건에 맞지 않는 타입으로 에러 발생한 것으로 예상
     */
}