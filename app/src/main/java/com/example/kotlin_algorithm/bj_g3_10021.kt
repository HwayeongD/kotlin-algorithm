package com.example.kotlin_algorithm

import java.util.Scanner
import kotlin.math.pow

lateinit var fields: ArrayDeque<Pair<Int, Int>> // 땅 정보
lateinit var costs: ArrayDeque<Triple<Long, Int, Int>> // 후보 파이프 정보
lateinit var root: Array<Int> // 각 땅의 root 정보

fun main() = with(Scanner(System.`in`)) {
    val (n, c) = nextLine().split(' ').map { it.toInt() }

    // initialization
    fields = ArrayDeque()
    costs = ArrayDeque()
    root = Array(n) { 0 }

    // input
    repeat(n) {
        val (x, y) = nextLine().split(' ').map { it.toInt() }
        fields.add(Pair(x, y))
    }

    for(i in 0 until n) {
        root[i] = i // root는 자신으로 초기화
        for(j in i + 1 until n) {
            val cost = computeCost(i, j) // 각 파이브 cost 계산
            if(cost >= c) costs.add(Triple(cost, i, j)) // c 이상만 저장
        }
    }

    costs.sortBy { it.first } // cost 기준 오름차순 정렬

    // process & output
    println(kruskal(n))
}

/** Kruskal **/
fun kruskal(n: Int) : Long {
    var amount = 0L // 총 파이프 cost
    var pipe = 0 // 파이프 수

    while(costs.isNotEmpty()) {
        val cost = costs.removeFirst()
        if(union(cost.second, cost.third)) continue // 싸이클 존재 시 파이브 무시

        // 파이프 추가
        amount += cost.first
        pipe++

        if(pipe == n - 1) return amount // MST 완성
    }

    return -1 // MST 연결 불가
}

fun union(x: Int, y: Int) : Boolean {
    val px = find(x)
    val py = find(y)

    if(px == py) return true

    // root 합치기
    if(px < py) root[py] = px
    else root[px] = py

    return false
}

/** Root 찾기 **/
fun find(x: Int) : Int {
    if(root[x] == x) return x // 자기 자신이면 바로 반환
    else { // 아니면 root 재귀 탐색
        root[x] = find(root[x])
        return root[x]
    }
}

fun computeCost(i: Int, j: Int) : Long {
    return ((fields[i].first - fields[j].first).toDouble().pow(2) + (fields[i].second - fields[j].second).toDouble().pow(2)).toLong()
}