package day04

import util.AocDay
import util.loadInputFromServer

fun main() {
    Day4(inputLines = loadInputFromServer("2022", "4")).printTheAnswers()
}

class Day4(
    inputLines: List<String>,
    private val assignments: List<Assignments> = inputLines.map { Assignments.fromString(it) }
) : AocDay {
    override fun part1() = assignments.count { it.fullyOverlaps() }

    override fun part2()= assignments.count { it.partiallyOverlaps() }
}


data class Assignments(
    val group1: IntRange,
    val group2: IntRange,
) {
    companion object {
        fun fromString(string: String) = string.split(',').let {
            Assignments(
                group1 = assignmentFromString(it[0]),
                group2 = assignmentFromString(it[1])
            )
        }
        private fun assignmentFromString(string: String) = string.split('-').let {
            it[0].toInt() .. it[1].toInt()
        }
    }
    fun fullyOverlaps() = group1.all { it in group2 } || group2.all { it in group1 }

    fun partiallyOverlaps() = group1.any { it in group2 }
}