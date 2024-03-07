package 알고리즘.백준.카드구매하기

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 *packageName    : 알고리즘.백준.카드구매하기
 * fileName       : Main
 * author         : ipeac
 * date           : 2024-03-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-07        ipeac       최초 생성
 */
fun main() {
    BufferedReader(InputStreamReader(System.`in`)).use { r ->
        val n = r.readLine()!!.toInt()
        val p = r.readLine()!!.split(" ").map { it.toInt() }
        
        val dp = IntArray(n + 1)
        
        dp[1] = p[0]
        
        for (i in 2..n) {
            for (j in 1..i) {
                dp[i] = maxOf(dp[i], dp[i - j] + p[j - 1])
            }
        }
        
        println(dp[n])
    }
}