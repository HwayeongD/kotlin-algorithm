package com.example.kotlin_algorithm.bfs

import java.util.Scanner

lateinit var safeDistance: Array<Array<Int>> // 안전거리
lateinit var queue: ArrayDeque<Triple<Int, Int, Int>>
val move = listOf(
    Pair(0, -1), Pair(1, -1), Pair(1, 0), Pair(1, 1),
    Pair(0, 1), Pair(-1, 1), Pair(-1, 0), Pair(-1, -1)
) // 이동 8방향

fun main() = with(Scanner(System.`in`)) {
    val (n, m) = nextLine().split(' ').map { it.toInt() }

    // initialization
    safeDistance = Array(n) { Array(m) { 0 } }
    queue = ArrayDeque()

    // input
    repeat(n) { nn ->
        repeat(m) { nm ->
            safeDistance[nn][nm] = Int.MAX_VALUE // 안전거리 초기화
            val num = nextInt()
            if(num == 1) {
                queue.add(Triple(0, nn, nm)) // 시작점 추가
                safeDistance[nn][nm] = 0 // 상어 위치는 안전거리 0
            }
        }
    }

    // process
    bfs(n, m)

    // output
    println(findMaxDistance(n, m))
}

/** BFS **/
fun bfs(n: Int, m: Int) {
    while(queue.isNotEmpty()) {
        val curr = queue.removeFirst()

        move.forEach { dir ->
            val nn = curr.second + dir.first
            val nm = curr.third + dir.second

            if(!isInBound(nn, nm, n, m)) return@forEach // 좌표 외부는 무시
            if(safeDistance[nn][nm] <= curr.first + 1) return@forEach // 이미 더 적다면 무시

            // 다음 탐색 후보로 추가
            queue.add(Triple(curr.first + 1, nn, nm))
            safeDistance[nn][nm] = curr.first + 1
        }
    }
}

/** 안전거리 최댓값 탐색 **/
fun findMaxDistance(n: Int, m: Int) : Int {
    var ans = 0

    for(i in 0 until n) {
        for(j in 0 until m) {
            if(safeDistance[i][j] > ans) ans = safeDistance[i][j]
        }
    }

    return ans
}

/** 좌표 범위 검증 **/
fun isInBound(x: Int, y: Int, n: Int, m: Int) : Boolean {
    return when {
        x < 0 -> false
        y < 0 -> false
        x >= n -> false
        y >= m -> false
        else -> true
    }
}