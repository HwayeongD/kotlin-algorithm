package com.example.kotlin_algorithm.bfs

import java.util.Scanner

lateinit var connected: Array<ArrayList<Int>>
lateinit var visited: Array<Int>
lateinit var queue: ArrayDeque<Int>
val sb = StringBuilder()
var cnt = 1

fun main() = with(Scanner(System.`in`)) {
    val (v, e, r) = nextLine().split(' ').map { it.toInt() }

    connected = Array(v + 1) { ArrayList() }
    visited = Array(v + 1) { 0 }
    queue = ArrayDeque()

    repeat(e) {
        val (a, b) = nextLine().split(' ').map { it.toInt() }

        connected[a].add(b)
        connected[b].add(a)
    }

    connected.forEach { it.sort() }

    queue.add(r)
    visited[r] = cnt++
    bfs()

    for(i in 1..v) {
        sb.append(visited[i]).append("\n")
    }

    println(sb)
}

fun bfs() {
    while (!queue.isEmpty()) {
        val curr = queue.removeFirst()

        connected[curr].forEach { next ->
            if(visited[next] != 0) return@forEach

            queue.add(next)
            visited[next] = cnt++
        }
    }
}