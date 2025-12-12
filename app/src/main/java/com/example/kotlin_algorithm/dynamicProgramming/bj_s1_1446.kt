package com.example.kotlin_algorithm.dynamicProgramming

import java.util.Scanner
import kotlin.math.min

lateinit var map: Array<Int> // 최소 거리
lateinit var highway: Array<ArrayDeque<Pair<Int, Int>>> // 지름길 정보

fun main() = with(Scanner(System.`in`)) {
    val (n, d) = nextLine().split(' ').map { it.toInt() }

    // 초기화
    map = Array(d + 1) { 0 }
    highway = Array(d + 1) { ArrayDeque() }

    // 입력
    repeat(n) {
        val (start, end, dist) = nextLine().split(' ').map { it.toInt() }
        if(end > d) return@repeat
        highway[end].add(Pair(start, dist))
    }

    map[0] = 0
    for (i in 1 .. d) {
        if(map[i] == 0) map[i] = i

        if(highway[i].size == 0) {
            map[i] = map[i - 1] + 1
        } else {
            highway[i].forEach { way ->
                map[i] = min(map[i], min(map[i - 1] + 1, map[way.first] + way.second))
            }
        }
    }

    println(map[d])
}