package 알고리즘.백준.네트워크1922

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    BufferedReader(InputStreamReader(System.`in`)).use { r ->
        val n = r.readLine().toInt()
        val m = r.readLine().toInt()

        val edges = mutableListOf<Edge>()

        repeat(m) {
            val (a, b, c) = r.readLine().split(" ").map { it.toInt() }
            edges.add(Edge(a, b, c))
        }

        //가중치 낮은 순으로 정렬이 필요하다
        edges.sort()

        val parent = IntArray(n + 1) { 0 }
        for (i in 1..n) {
            parent[i] = i
        }

        var result = 0

        for (edge in edges) {
            val (a, b, c) = edge
            if (findParent(parent, a) != findParent(parent, b)) {
                union(parent, a, b)
                result += c
            }
        }

        println(result)
    }
}

fun findParent(parent: IntArray, x: Int): Int {
    return if (parent[x] == x) x else findParent(parent, parent[x])
}

fun union(parent: IntArray, a: Int, b: Int) {
    val (pa, pb) = findParent(parent, a) to findParent(parent, b)
    if (pa < pb) parent[pb] = pa else parent[pa] = pb
}

data class Edge(val from: Int, val to: Int, val weight: Int) : Comparable<Edge> {

    override fun compareTo(other: Edge): Int {
        return weight - other.weight
    }

    override fun toString(): String {
        return "Edge(from=$from, to=$to, weight=$weight)"
    }
}