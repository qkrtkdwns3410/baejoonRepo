package 알고리즘.백준.덱

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 *packageName    : 알고리즘.백준.덱
 * fileName       : Main
 * author         : ipeac
 * date           : 2024-03-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-02        ipeac       최초 생성
 */
fun main() {
    BufferedReader(InputStreamReader(System.`in`)).use { br ->
        val n = br.readLine().toInt()
        val deque = ArrayDeque<Int>()
        val answer = StringBuilder()
        
        repeat(n) {
            when (val command = br.readLine()) {
                "pop_front" -> answer.appendLine(deque.removeFirstOrNull() ?: -1)
                "pop_back" -> answer.appendLine(deque.removeLastOrNull() ?: -1)
                "size" -> answer.appendLine(deque.size)
                "empty" -> answer.appendLine(if (deque.isEmpty()) 1 else 0)
                "front" -> answer.appendLine(deque.firstOrNull() ?: -1)
                "back" -> answer.appendLine(deque.lastOrNull() ?: -1)
                
                else -> {
                    val (p, x) = command.split(" ")
                    when (p) {
                        "push_front" -> deque.addFirst(x.toInt())
                        "push_back" -> deque.addLast(x.toInt())
                    }
                }
            }
        }
        
        println(answer)
    }
}