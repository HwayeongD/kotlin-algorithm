package com.example.kotlin_algorithm.bruteForce

import java.util.Scanner

var n: Int = 0 // 복도 사이즈
lateinit var map: Array<Array<Char>> // 복도 정보
lateinit var teachers: ArrayDeque<Pair<Int, Int>> // 선생님의 위치
lateinit var spaces: ArrayDeque<Pair<Int, Int>> // 빈 공간의 위치 (장애물 후보)
val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

fun main() = with(Scanner(System.`in`)) {
    n = nextLine().toInt()

    // initialization
    map = Array(n) { Array(n) { ' ' } }
    teachers = ArrayDeque()
    spaces = ArrayDeque()

    // input
    repeat(n) { i ->
        val inp = nextLine().split(' ')
        repeat(n) { j ->
            map[i][j] = inp[j].firstOrNull() ?: 'X'

            when(map[i][j]) {
                'X' -> spaces.add(Pair(i, j))
                'T' -> teachers.add(Pair(i, j))
            }
        }
    }

    // (조합) 장애물 시뮬레이션
    val spaceSz = spaces.size
    for(i in 0 until spaceSz) {
        for(j in i + 1 until spaceSz) {
            for(k in j + 1 until spaceSz) {
                val res = simulation(i, j, k)
                if(!res) { // 학생 못찾은 경우
                    println("YES") // 케이스 발견
                    return@with
                }
            }
        }
    }

    // 학생 못찾는 케이스가 없는 경우
    println("NO")
}

/** i, j, k 장애물 설치 시 시뮬레이션 **/
fun simulation(i: Int, j: Int, k: Int) : Boolean {
    // 장애물 설치
    map[spaces[i].first][spaces[i].second] = 'O'
    map[spaces[j].first][spaces[j].second] = 'O'
    map[spaces[k].first][spaces[k].second] = 'O'

    teachers.forEach { teacher -> // 모든 선생님 순차적으로
        directions.forEach { direction -> // 4방향으로 탐색
            val res = searchStudent(teacher, direction)
            if(res) { // 학생 찾았다면 탐색 중지
                // 장애물 원복
                map[spaces[i].first][spaces[i].second] = 'X'
                map[spaces[j].first][spaces[j].second] = 'X'
                map[spaces[k].first][spaces[k].second] = 'X'

                return true
            }
        }
    }

    // 장애물 원복
    map[spaces[i].first][spaces[i].second] = 'X'
    map[spaces[j].first][spaces[j].second] = 'X'
    map[spaces[k].first][spaces[k].second] = 'X'

    // 학생 못찾은 경우 발견
    return false
}

/** 학생 찾기 **/
fun searchStudent(teacher: Pair<Int, Int>, direction: Pair<Int, Int>) : Boolean {
    // teacher 위치 기준에서 direction 방향으로 탐색
    for(i in 1 until 6) {
        val nx = teacher.first + (direction.first * i)
        val ny = teacher.second + (direction.second * i)

        if(!isInBound(nx, ny)) break // 복도 외부 도달 시 탐색 중지

        when(map[nx][ny]) {
            'O' -> return false // 장애물 발견 시 탐색 중지
            'S' -> return true // 학생 발견
        }
    }

    return false // 학생 못찾음
}

/** 복도 내부 여부 **/
fun isInBound(nx: Int, ny: Int) : Boolean {
    return when {
        nx < 0 -> false
        ny < 0 -> false
        nx >= n -> false
        ny >= n -> false
        else -> true
    }
}