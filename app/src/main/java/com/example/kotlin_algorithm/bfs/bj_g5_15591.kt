package com.example.kotlin_algorithm.bfs

import java.util.Scanner

lateinit var connected: Array<ArrayDeque<Pair<Int, Int>>> // 연결 정보
lateinit var visited: Array<Boolean>
lateinit var queue: ArrayDeque<Int>

fun main() = with(Scanner(System.`in`)) {
    val (n, quest) = nextLine().split(' ').map { it.toInt() }

    // initialization
    connected = Array(n + 1) { ArrayDeque() }
    visited = Array(n + 1) { false }
    queue = ArrayDeque()

    // input
    repeat(n - 1) {
        val (p, q, r) = nextLine().split(' ').map { it.toInt() }
        // 양방향 연결 정보 저장
        connected[p].add(Pair(q, r))
        connected[q].add(Pair(p, r))
    }

    repeat(quest) {
        val (k, v) = nextLine().split(' ').map { it.toInt() }

        init(n) // 부분 초기화
        println(bfs(k, v)) // BFS 실행
    }
}

fun init(n: Int) {
    repeat(n + 1) {
        visited[it] = false
    }
}

/** BFS **/
fun bfs(k: Int, v: Int) : Int {
    var ret = 0 // 추천 가능 영상의 개수

    // 시작점
    visited[v] = true
    queue.add(v)

    while(queue.isNotEmpty()) {
        val curr = queue.removeFirst()

        connected[curr].forEach {
            if(visited[it.first]) return@forEach // 기방문 지점 무시
            if(it.second < k) return@forEach // 유사도 작은 경로 무시

            // 다음 탐색 지점
            visited[it.first] = true
            queue.add(it.first)
            ret++
        }
    }

    return ret
}