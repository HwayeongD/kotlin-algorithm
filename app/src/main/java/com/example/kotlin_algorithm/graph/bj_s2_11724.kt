package com.example.kotlin_algorithm.graph

import java.util.Scanner

lateinit var groupInfo: Array<Int> // 각 node의 연결 요소 정보
lateinit var connected: Array<ArrayDeque<Int>> // node 연결 정보
lateinit var queue: ArrayDeque<Int> // 탐색용 queue

fun main() = with(Scanner(System.`in`)) {
    val (n, m) = nextLine().split(' ').map { it.toInt() } // N M

    // Initialization
    groupInfo = Array(n + 1) { 0 }
    connected = Array(n + 1) { ArrayDeque() }
    queue = ArrayDeque()

    // 간선 정보 입력
    repeat(m) {
        val (a, b) = nextLine().split(' ').map { it.toInt() }

        connected[a].add(b)
        connected[b].add(a)
    }

    var cc = 0 // 연결 요소

    for(idx in 1 .. n) { // 순차적 순회
        if(groupInfo[idx] != 0) continue // 기방문 노드는 무시

        // 새로 찾은 그룹
        cc += 1
        groupInfo[idx] = cc
        queue.add(idx)

        // 간선 연결된 노드들 탐색
        while(queue.isNotEmpty()) {
            val curr = queue.removeFirst()

            connected[curr].forEach { nNum ->
                if(groupInfo[nNum] != 0) return@forEach

                groupInfo[nNum] = cc
                queue.add(nNum)
            }
        }
    }

    println(cc)
}