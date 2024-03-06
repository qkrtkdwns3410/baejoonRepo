package 알고리즘.백준.비밀번호찾기

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 *packageName    : 알고리즘.백준.비밀번호찾기
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
        val (n, m) = r.readLine().split(" ").map { it.toInt() }
        val answer = StringBuilder()
        val map = mutableMapOf<String, String>()
        
        repeat(n) {
            val (site, password) = r.readLine().split(" ")
            map[site] = password
        }
        
        repeat(m) {
            answer.appendLine(map[r.readLine()])
        }
        
        println(answer);
    }
}