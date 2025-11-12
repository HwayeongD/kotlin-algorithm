package com.example.kotlin_algorithm.graph

import java.util.Scanner

lateinit var connected: Array<ArrayList<Int>>
lateinit var visited: Array<Int>
lateinit var infected: ArrayDeque<Int>
var viruses = 0

fun main() = with(Scanner(System.`in`)) {
    val computers = nextInt()
    val connection = nextInt()

    connected = Array(computers + 1) { ArrayList() }
    visited = Array(computers + 1) { 0 }
    infected = ArrayDeque()

    repeat(connection) {
        val a = nextInt()
        val b = nextInt()
//        val (a, b) = nextLine().split(' ').map { it.toInt() }

        connected[a].add(b)
        connected[b].add(a)
    }

    visited[1] = 1
    infected.add(1)

    while(infected.isNotEmpty()) {
        val curr = infected.removeFirst()

        connected[curr].forEach {
            if(visited[it] != 0) return@forEach

            infected.add(it)
            visited[it] = 1
            viruses++
        }
    }

    println(viruses)
}