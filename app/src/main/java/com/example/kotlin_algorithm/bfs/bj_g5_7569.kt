package com.example.kotlin_algorithm.bfs

import java.util.Scanner

lateinit var box: Array<Array<Array<Int>>> // 토마토 박스
lateinit var visited: Array<Array<Array<Int>>> // 방문 여부
lateinit var queue: ArrayDeque<Triple<Int, Int, Int>>
val directions = listOf(
    Triple(-1, 0, 0), Triple(0, -1, 0),
    Triple(1, 0, 0), Triple(0, 1, 0),
    Triple(0, 0, -1), Triple(0, 0, 1)
) // 6방향 (상하좌우 및 위아래)
var tomatoes = 0 // 전체 토마토 (익, 안익)
var riped = 0 // 익은 토마토
var day = -1 // 소요 일수

fun main() = with(Scanner(System.`in`)) {
    val (m, n, h) = nextLine().split(' ').map { it.toInt() }

    // initialization
    box = Array(h) { Array(n) { Array(m) { 0 } } }
    visited = Array(h) { Array(n) { Array(m) { 0 } } }
    queue = ArrayDeque()

    // input
    repeat(h) { floor ->
        repeat(n) { cn ->
            repeat(m) { cm ->
                val info = nextInt()
                box[floor][cn][cm] = info

                when(info) {
                    1 -> { // 익은 토마토
                        riped++
                        tomatoes++
                        queue.add(Triple(floor, cn, cm))
                    }
                    0 -> { // 안 익은 토마토
                        tomatoes++
                    }
                    -1 -> {

                    }
                }
            }
        }
    }

    // 익은 토마토 주위 탐색
    while(queue.isNotEmpty()) {
        day++ // 일수 카운팅

        val sz = queue.size
        for(i in 0 until sz) {
            val curr = queue.removeFirst() // 금번에 탐색할 익은 토마토
            directions.forEach { // 6방향으로 탐색
                val nH = curr.first + it.first
                val nN = curr.second + it.second
                val nM = curr.third + it.third

                if(!isInBoxArea(nH, nN, nM)) return@forEach // 박스 외부는 무시
                if(visited[nH][nN][nM] == 1) return@forEach // 방문한 곳은 무시
                if(box[nH][nN][nM] == 1 || box[nH][nN][nM] == -1) return@forEach // 이미 익은 토마토 및 빈 칸은 무시

                // 다음 탐색 대상
                queue.add(Triple(nH, nN, nM))
                visited[nH][nN][nM] = 1
                box[nH][nN][nM] = 1
                riped++
            }
        }
    }

    // output
    println(if(riped == tomatoes) day else -1)
}

/** 3차원 박스 내부 여부 확인 **/
fun isInBoxArea(h: Int, n: Int, m: Int) : Boolean {
    val hh = box.size
    val nn = box[0].size
    val mm = box[0][0].size

    return when {
        h < 0 -> false
        h >= hh -> false
        n < 0 -> false
        n >= nn -> false
        m < 0 -> false
        m >= mm -> false
        else -> true
    }
}