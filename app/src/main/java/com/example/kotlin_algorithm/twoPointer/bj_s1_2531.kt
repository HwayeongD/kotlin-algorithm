package com.example.kotlin_algorithm.twoPointer

import java.util.Scanner

lateinit var sushiRail: Array<Int> // 레일 상태
lateinit var grouped: Array<Int> // 그룹화
var ans = 0

fun main() = with(Scanner(System.`in`)) {
    // n:레일 수 d:총 가짓수 k:연속 수 c:쿠폰 번호
    val (n, d, k, c) = nextLine().split(' ').map { it.toInt() }

    // initialization
    sushiRail = Array(n + k) { 0 }
    grouped = Array(d + 1) { 0 }

    var variety = 0 // 매 턴의 가짓수

    // input & setting
    repeat(n) { idx ->
        val num = nextInt()

        sushiRail[idx] = num
        if(idx < k) { // 첫 번째 그룹
            sushiRail[idx + n] = num // 반복

            grouped[num]++ // 포함 여부 갱신
            if(grouped[num] == 1) variety++ // 가짓수 체크
        }
    }

    ans = if(grouped[c] == 0) variety + 1 else variety // 쿠폰 적용 결과로 답안 갱신

    // 순회 탐색 - 0부터 (n -2)까지 cHead 제거 & nTail 추가
    for(idx in 0 until n - 1) {
        val cHead = sushiRail[idx] // 현재 head
        val nTail = sushiRail[idx + k] // 신규 tail

        // 현재 head 제거
        grouped[cHead]--
        if(grouped[cHead] == 0) {
            variety--
        }

        // 신규 tail 추가
        grouped[nTail]++
        if(grouped[nTail] == 1) {
            variety++
        }

        // 쿠폰 적용
        val tmp = if(grouped[c] == 0) variety + 1 else variety

        // 답안 갱신
        if(tmp > ans) ans = tmp
    }

    // 출력
    println(ans)
}