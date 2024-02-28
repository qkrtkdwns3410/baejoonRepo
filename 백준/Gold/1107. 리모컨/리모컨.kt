import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import kotlin.math.abs

fun main() {
    val reader = System.`in`.bufferedReader(StandardCharsets.UTF_8)
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    
    reader.use { r ->
        writer.use { w ->
            // 수빈이가 이동하려고하는 채널
            val N: Int = r.readLine().trimEnd().toInt()
            // 고장난 버튼의 개수
            val M: Int = r.readLine().trimEnd().toInt()
            // 고장난 버튼 리스트
            val brokenButtons: Set<Int> = if (M > 0) {
                r.readLine().trimEnd().split(" ")
                    .map { it.toInt() }
                    .toSet()
            } else emptySet()
            val avaliableChannels = (0..9).filter { it !in brokenButtons }
            
            //고장난 버튼을 제외하고 채널을 변경할 수 있는지 확인하는 함수.
            fun canPress(channel: Int): Boolean {
                //채널 0인 경우 고장난 버튼이 0을 가지고 있지 않으면 true 반환
                if (channel == 0) {
                    return 0 in avaliableChannels
                }
                
                val allDigitsClear = channel.toString().map { it.toString().toInt() }.all { it in avaliableChannels }
                
                return allDigitsClear
            }
            
            fun downChannel(channel: Int): Int {
                var current = channel
                // current 가 0 미만인 경우 종료해야함 -> 음수는 채널이 아님
                while (0 <= current) {
                    if (canPress(current)) {
                        return current
                    }
                    current--
                }
                
                return Int.MAX_VALUE
            }
            
            fun upChannel(channel: Int, limit: Int): Int {
                var current = channel
                while (true) {
                    if (abs(current - channel) > limit) {
                        return Int.MAX_VALUE
                    }
                    
                    if (canPress(current)) {
                        return current
                    }
                    current++
                }
            }
            
            fun getClosestChannel(target: Int): Int {
                if (avaliableChannels.isEmpty()) return Int.MAX_VALUE
                
                val down: Int = downChannel(target)
                
                val downDiff: Int = abs(target - down)
                
                val up: Int = upChannel(target + 1, downDiff)
                
                val upDiff: Int = abs(up - target)
                
                //단순 거리 + 채널의 길이 비교하여 작은 값을 반환
                val upDist = if (up == Int.MAX_VALUE) {
                    Int.MAX_VALUE
                } else {
                    up.toString().length + upDiff
                }
                
                val downDist = if (down == Int.MAX_VALUE) {
                    Int.MAX_VALUE
                } else {
                    down.toString().length + downDiff
                }
                
                return minOf(upDist, downDist)
            }
            
            //가장 가까운 채널을 탐색
            val from100 = abs(N - 100)
            val thereIsNoAvaliableButton = getClosestChannel(N) == Int.MAX_VALUE
            
            val closestChannel = if (thereIsNoAvaliableButton) {
                from100
            } else {
                getClosestChannel(N)
            }
            
            w.write(minOf(from100, closestChannel).toString());
            w.flush()
        }
    }
}