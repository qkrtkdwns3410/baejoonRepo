package 알고리즘.백준.NX2타일링2

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 *packageName    : 알고리즘.백준.NX2타일링2
 * fileName       : Main
 * author         : ipeac
 * date           : 2024-03-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-04        ipeac       최초 생성
 */
fun main() {
    BufferedReader(InputStreamReader(System.`in`)).use { r ->
        val n = r.readLine()!!.toInt()
        
        val dp = IntArray(1001)
        
        dp[1] = 1
        dp[2] = 3
        
        if (n <= 2) {
            println(dp[n])
            return
        }
        
        for (i in 3..n) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007
        }
        
        println(dp[n])
    }
}