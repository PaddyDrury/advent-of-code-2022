package day01

import util.AocDay
import util.chunkWhen
import util.loadInputFromServer

fun main() {
    Day1(inputLines = loadInputFromServer("2022", "1"))
        .printTheAnswers()
}

class Day1(
    inputLines: List<String>,
    private val input: List<String> = inputLines,
) : AocDay {
    override fun part1() = input.chunkWhen {
        it.isBlank()
    }.maxOf { group ->
        group.sumOf { it.toLong() }
    }

    override fun part2() = input.chunkWhen {
        it.isBlank()
    }.map { group ->
        group.sumOf { it.toLong() }
    }.sortedDescending()
        .take(3)
        .sum()
}