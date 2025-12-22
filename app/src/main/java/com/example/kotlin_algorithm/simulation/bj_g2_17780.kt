package com.example.kotlin_algorithm.simulation

import java.util.Scanner

lateinit var map: Array<Array<Int>> // 지도
lateinit var horses: Array<Triple<Int, Int, Int>> // 각 말의 위치
lateinit var positions: Array<Array<ArrayDeque<Int>>> // 지도 위의 말 배치
val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0)) // 방향(우하좌상)

fun main() = with(Scanner(System.`in`)) {
    val (n, k) = nextLine().split(' ').map { it.toInt() }

    // initialization
    map = Array(n + 2) { Array(n + 2) { 2 } }
    horses = Array(k + 1) { Triple(0, 0, 0) }
    positions = Array(n + 2) { Array(n + 2) { ArrayDeque() } }

    // input
    repeat(n) { i ->
        val row = nextLine().split(' ').map { it.toInt() }
        repeat(n) { j ->
            map[i + 1][j + 1] = row[j] // 지도
        }
    }

    repeat(k) { i ->
        // 말 초기 정보
        val (r, c, d) = nextLine().split(' ').map { it.toInt() }
        val dir = if(d == 1) 0 else if(d == 4) 1 else d // 방향은 소스에 맞게 재설정
        horses[i + 1] = Triple(r, c, dir)
        positions[r][c].add(i + 1)
    }

    // simulation
    var turn = 0
    while(turn <= 1000) {
        turn++
        if(playTurn(k)) break
    }

    // output
    println(if(turn > 1000) -1 else turn)
}

/**
 * 각 turn play
 * @return 종료 조건 충족
 * **/
fun playTurn(k: Int) : Boolean {
    for(num in 1 .. k) { // 모든 말 순회
        val cr = horses[num].first
        val cc = horses[num].second
        val cd = horses[num].third

        if(positions[cr][cc].first() != num) continue // 맨 밑이 아니면 이동 불가

        // 이동할 칸의 위치
        var nr = cr + directions[cd].first
        var nc = cc + directions[cd].second

        if(map[nr][nc] == 2) { // 파란칸인 경우 - 방향 반대로 변경
            val nd = (cd + 2) % 4
            horses[num] = Triple(cr, cc, nd)

            // 방향 변경 후 신규 위치 재산정
            nr = cr + directions[nd].first
            nc = cc + directions[nd].second
        }

        // 0 흰칸 - 그대로 이동
        // 1 빨간칸 - 반대로 이동
        // 2 파란칸 - 이동 X
        val orders = if(map[nr][nc] == 0) positions[cr][cc] else if(map[nr][nc] == 1) positions[cr][cc].reversed() else continue

        // (cr, cc) 위치의 말들 순차적 이동
        orders.forEach { nHorse ->
            positions[nr][nc].add(nHorse)
            horses[nHorse] = Triple(nr, nc, horses[nHorse].third)
        }

        positions[cr][cc].clear() // 이동 후 기존 위치는 삭제

        if(positions[nr][nc].size >= 4) return true // 말 4개 이상 쌓였다면 true 반환 (게임 종료 조건)
    }

    return false
}