package 알고리즘.백준.IOIOI

import java.io.BufferedReader

/**
 *packageName    : 알고리즘.백준.IOIOI
 * fileName       : Main
 * author         : ipeac
 * date           : 2024-03-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-01        ipeac       최초 생성
 */
fun main() {
    BufferedReader(System.`in`.reader()).use { br ->
        val n = br.readLine().toInt()
        val m = br.readLine().toInt()
        val s = br.readLine()
        
        var answer = 0
        var patternCount = 0
        var index = 0
        
        while (index < m - 1) {
            if (s[index] == 'I' && s[index + 1] == 'O' && index + 2 < m && s[index + 2] == 'I') {
                patternCount++
                
                if (patternCount == n) {
                    answer++
                    //패턴이 겹칠 가능성이 있기에, 카운트를 하나 줄인다. ( 이전 카운트를 기억할 수 있음 (효율))
                    patternCount--
                }
                
                index += 2
            } else {
                patternCount = 0
                index++
                
                // I 를 만날때까지 index를 증가시킨다.
                while (index < m && s[index] != 'I') {
                    index++
                }
            }
        }
        
        println(answer);
    }
}