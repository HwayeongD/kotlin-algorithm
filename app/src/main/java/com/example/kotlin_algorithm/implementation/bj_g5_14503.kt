package com.example.kotlin_algorithm.implementation

import java.util.Scanner

lateinit var room: Array<Array<Int>> // 방 정보 - 0:청소X, 1:벽, 2:청소O
val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1)) // 북동남서
var cleaned = 0 // 청소한 칸의 수

fun main() = with(Scanner(System.`in`)) {
    val (n, m) = nextLine().split(' ').map { it.toInt() }
    var (rX, rY, rD) = nextLine().split(' ').map { it.toInt() }

    // 초기화
    room = Array(n) { Array(m) { 0 } }

    repeat(n) { cn ->
        val nl = nextLine().split(' ').map { it.toInt() }
        repeat(m) { cm ->
            room[cn][cm] = nl[cm]
        }
    }

    while(true) {
        // 1. 현재 칸이 청소되지 않은 경우, 청소한다.
        if(room[rX][rY] != 2) {
            room[rX][rY] = 2
            cleaned++
        }

        if(!allCleaned(rX, rY)) { // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            val backDir = directions[(rD + 2) % 4]
            if(room[rX + backDir.first][rY + backDir.second] != 1) { // 후진이 가능하다면
                // 후진
                rX += backDir.first
                rY += backDir.second
            } else {
                // 작동 멈춤
                break
            }
        } else { // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            rD = (rD + 3) % 4 // 반시계 회전
            if(room[rX + directions[rD].first][rY + directions[rD].second] == 0) { // 청소X인 경우
                // 전진
                rX += directions[rD].first
                rY += directions[rD].second
            }
        }
    }

    // 출력
    println(cleaned)
}

// 주변 4칸의 청소 여부 체크
fun allCleaned(x: Int, y: Int) : Boolean {
    return when {
        room[x - 1][y] == 0 -> true
        room[x + 1][y] == 0 -> true
        room[x][y - 1] == 0 -> true
        room[x][y + 1] == 0 -> true
        else -> false
    }
}