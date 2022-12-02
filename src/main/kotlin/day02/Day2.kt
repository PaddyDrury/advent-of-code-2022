package day02

import day02.Result.*
import util.AocDay
import util.loadInputFromServer

fun main() {
    Day2(inputLines = loadInputFromServer("2022", "2")).printTheAnswers()
}

class Day2(private val inputLines: List<String>) : AocDay {

    val rock = Fist(
        score = 1,
        name = "ROCK",
    )
    val paper = Fist(
        score = 2,
        name = "PAPER",
    )

    val scissors = Fist(
        score = 3,
        name = "SCISSORS",
    )

    init {
        rock.winsAgainst = scissors
        rock.losesAgainst = paper
        scissors.winsAgainst = paper
        scissors.losesAgainst = rock
        paper.winsAgainst = rock
        paper.losesAgainst = scissors
    }

    override fun part1() = inputLines.sumOf { scorePart1(it) }

    override fun part2() = inputLines.sumOf { scorePart2(it) }
    private fun scorePart1(line: String) = when (line.first()) {
        'A' -> rock
        'B' -> paper
        'C' -> scissors
        else -> error("invalid ${line.first()}")
    }.let { opponent ->
        when (line.last()) {
            'X' -> rock
            'Y' -> paper
            'Z' -> scissors
            else -> error("invalid")
        }.let { i ->
            when (i.play(opponent)) {
                WIN -> 6 + i.score
                LOSE -> i.score
                DRAW -> 3 + i.score
            }
        }
    }

    private fun scorePart2(line: String) = when (line.first()) {
        'A' -> rock
        'B' -> paper
        'C' -> scissors
        else -> error("invalid ${line.first()}")
    }.let { opponent ->
        when (line.last()) {
            'X' -> opponent.winsAgainst.score
            'Y' -> opponent.score + 3
            'Z' -> opponent.losesAgainst.score + 6
            else -> error("invalid")
        }
    }
}

enum class Result {
    WIN, LOSE, DRAW,
}

data class Fist(
    val score: Int,
    val name: String,
) {
    lateinit var winsAgainst: Fist
    lateinit var losesAgainst: Fist
    fun play(other: Fist) = when (other) {
        this -> DRAW
        winsAgainst -> WIN
        losesAgainst -> LOSE
        else -> error("invalid")
    }
}