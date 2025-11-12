package com.example.kotlin_algorithm.dfs

import java.util.Scanner

lateinit var connected: Array<ArrayList<Int>>
lateinit var visited: Array<Int>
val sb = StringBuilder()
var cnt = 1

fun main() = with(Scanner(System.`in`)) {
    val (v, e, r) = nextLine().split(' ').map { it.toInt() }

    connected = Array(v + 1) { ArrayList() }
    visited = Array(v + 1) { 0 }

    repeat(e) {
        val (a, b) = nextLine().split(' ').map { it.toInt() }

        connected[a].add(b)
        connected[b].add(a)
    }

connected.forEach { it.sortDescending() }

    dfs(r)

    for(i in 1..v) {
        sb.append(visited[i]).append("\n")
    }

    println(sb)
}

fun dfs(idx: Int) {
    if(visited[idx] != 0) return

    visited[idx] = cnt++

    connected[idx].forEach { next ->
        dfs(next)
    }
}