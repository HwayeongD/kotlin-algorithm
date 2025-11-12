package com.example.kotlin_algorithm.graph

import java.util.Scanner

lateinit var map: Array<Array<Int>> // 배추밭
lateinit var visited: Array<Array<Int>> // 방문 여부
lateinit var queue: ArrayDeque<Pair<Int, Int>> // queue
val directions = listOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1)) // 상 좌 우 하
var worms = 0 // 지렁이 수

fun main() = with(Scanner(System.`in`)) {
    val tc = nextLine().toInt()

    repeat(tc) {
        val (m, n, k) = nextLine().split(' ').map { it.toInt() }

        // initialization
        map = Array(m) { Array(n) { 0 } }
        visited = Array(m) { Array(n) { 0 } }
        queue = ArrayDeque()
        worms = 0

        // 배추 위치 입력
        repeat(k) {
            val (x, y) = nextLine().split(' ').map { it.toInt() }
            map[x][y] = 1
        }

        // 1) 직사각형 지도 탐색
        for (i in 0 until m) {
            for (j in 0 until n) {
                if(map[i][j] == 1 && visited[i][j] == 0) {
                    // 2) 탐색 시작할 칸 찾기
                    worms++ // 2) 지렁이 추가
                    checkTerritory(i, j) // 3) BFS 탐색 시작
                }
            }
        }

        println(worms)
    }
}

/** BFS **/
fun checkTerritory(i: Int, j: Int) {
    // 시작점 initialization
    queue.add(Pair(i, j))
    visited[i][j] = 1

    // 4) 방문할 인접칸 없을 때까지 탐색
    while(queue.isNotEmpty()) {
        val curr = queue.removeFirst()

        directions.forEach { // 4방향의 인접칸 탐색
            val ni = curr.first + it.first
            val nj = curr.second + it.second

            if(!isInside(ni, nj)) return@forEach // 인덱스 체크
            if(map[ni][nj] == 0) return@forEach // 배추 여부 체크
            if(visited[ni][nj] == 1) return@forEach // 방문 여부 체크

            queue.add(Pair(ni, nj)) // 다음 탐색 후보로 추가
            visited[ni][nj] = 1
        }
    }
}

/** map 내부 인덱스 여부 체크 **/
fun isInside(i: Int, j: Int) : Boolean {
    return when {
        i < 0 -> false
        j < 0 -> false
        i >= map.size -> false
        j >= map[0].size -> false
        else -> true
    }
}