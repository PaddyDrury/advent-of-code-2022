package day03


import util.AocDay
import util.loadInputFromServer

fun main() {
    Day3(inputLines = loadInputFromServer("2022", "3")).printTheAnswers()
}

class Day3(
    inputLines: List<String>,
    private val rucksacks: List<Rucksack> = inputLines.map { Rucksack.fromString(it) }
) : AocDay {
    override fun part1() = rucksacks.sumOf { it.duplicateScore() }
    override fun part2() = rucksacks.chunked(3).sumOf { it.groupBadge().sumOf { it.score() } }
}

data class Rucksack(
    val compartment1: Set<Char>,
    val compartment2: Set<Char>,
) {
    companion object {
        fun fromString(str: String) = str.chunked(str.length / 2).let {
            Rucksack(
                compartment1 = it[0].toSet(),
                compartment2 = it[1].toSet(),
            )
        }
    }

    fun duplicateScore() = compartment1.intersect(compartment2).sumOf {
        it.score()
    }

    fun badges() = compartment1.union(compartment2)
}

fun Char.score() = when(isLowerCase()) {
    true -> this - 'a' + 1
    else -> this - 'A' + 27
}

fun List<Rucksack>.groupBadge() = this.map { it.badges() }.reduce { acc, badges ->
    acc.intersect(badges)
}