package com.example.kotlin_algorithm.bfs

import java.util.Scanner

lateinit var maze: Array<Array<Int>> // 미로
lateinit var visited: Array<Array<Int>> // 방문 순차
lateinit var queue: ArrayDeque<Pair<Int, Int>> // 탐색용 queue
val directions = listOf(Pair(-1, 0), Pair(0, -1), Pair(1, 0), Pair(0, 1)) // 좌 상 우 하

fun main() = with(Scanner(System.`in`)) {
    val (n, m) = nextLine().split(' ').map { it.toInt() }

    // initialization
    maze = Array(n) { Array(m) { 0 } }
    visited = Array(n) { Array(m) { 0 } }
    queue = ArrayDeque()

    // input
    repeat(n) { cn ->
        val nl = nextLine()
        repeat(m) { cm ->
            maze[cn][cm] = nl[cm] - '0'
        }
    }

    // (0, 0)에서 탐색 시작
    visited[0][0] = 1
    queue.add(Pair(0, 0))

    while(queue.isNotEmpty()) {
        val curr = queue.removeFirst()

        for(i in 0 until 4) { // 상하좌우 탐색
            val nx = curr.first + directions[i].first
            val ny = curr.second + directions[i].second

            if(!isInBound(nx, ny)) continue // 미로 밖은 무시
            if(maze[nx][ny] == 0) continue // 이동 불가한 칸은 무시
            if(visited[nx][ny] != 0) continue // 이미 방문한 칸은 무시

            // 다음 후보 지점 추가
            queue.add(Pair(nx, ny))
            visited[nx][ny] = visited[curr.first][curr.second] + 1 // 방문 순차 갱신

            if(nx == n - 1 && ny == m - 1) { // (n-1, m-1) 칸 도달
                println(visited[n-1][m-1])
                queue.clear()
                break
            }
        }
    }
}

fun isInBound(x: Int, y: Int): Boolean {
    return when {
        x < 0 -> false
        y < 0 -> false
        x >= maze.size -> false
        y >= maze[0].size -> false
        else -> true
    }
}