package com.example.kotlin_algorithm.arithmetic

import java.util.Scanner

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val scores = ArrayList<Int>()
    var maxScore = 0
    var sumFakeScores = 0f

    repeat(n) {
        val score = nextInt()
        if(score > maxScore) maxScore = score

        scores.add(score)
    }

    repeat(n) { i ->
        sumFakeScores += ((scores[i] / maxScore.toFloat()) * 100)
    }

    println(sumFakeScores / n)
}