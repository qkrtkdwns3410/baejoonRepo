import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    
    fun calculateDistance(mbti1: String, mbti2: String): Int {
        return mbti1.indices.count { mbti1[it] != mbti2[it] }
    }
    
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    val answer = StringBuilder()
    
    repeat(t) { _ ->
        val n = br.readLine().toInt()
        val mbtis = br.readLine().split(" ")
        
        if (3 <= mbtis.size) {
            val eachCount = mbtis.groupingBy { it }.eachCount()
            
            val maxCount = eachCount.maxByOrNull { it.value }!!.value
            
            if (3 <= maxCount) {
                answer.append("0").appendLine()
                return@repeat
            }
        }
        
        var minDistance = Int.MAX_VALUE
        
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                for (k in j + 1 until n) {
                    val distance = calculateDistance(mbtis[i], mbtis[j]) +
                            calculateDistance(mbtis[j], mbtis[k]) +
                            calculateDistance(mbtis[i], mbtis[k])
                    
                    minDistance = minOf(minDistance, distance)
                }
            }
        }
        
        answer.append("$minDistance").appendLine()
    }
    
    print(answer)
}