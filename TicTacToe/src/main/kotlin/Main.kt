package tictactoe

fun finishTTT(ticTacToe: Array<IntArray>): Boolean {
    var countX = 0
    var countO = 0
    var countXWin = 0
    var countOWin = 0
    var countEmpty = 0
    var winX: Boolean
    var winO: Boolean
    for (row in ticTacToe.indices) {
        winX = true
        winO = true
        for (column in ticTacToe[row].indices) {
            when (ticTacToe[row][column]) {
                'X'.code -> {
                    countX += 1
                    winO = false
                }
                'O'.code -> {
                    countO += 1
                    winX = false
                }
                else -> {
                    winO = false
                    winX = false
                    countEmpty += 1
                }
            }
        }
        if (winX) countXWin += 1
        if (winO) countOWin += 1
    }
    val diffXOCounts = kotlin.math.abs(countX -countO)

    for (column in ticTacToe[0].indices) {
        winX = true
        winO = true
        for (row in ticTacToe.indices) {
            when (ticTacToe[row][column]) {
                'X'.code -> winO = false
                'O'.code -> winX = false
                else -> {
                    winO = false
                    winX = false
                }
            }
        }
        if (winX) countXWin += 1
        if (winO) countOWin += 1
    }

    winX = true
    winO = true
    for (row in ticTacToe.indices) {
        when (ticTacToe[row][row]) {
            'X'.code -> winO = false
            'O'.code -> winX = false
            else -> {
                winO = false
                winX = false
            }
        }
    }
    if (winX) countXWin += 1
    if (winO) countOWin += 1

    winX = true
    winO = true
    for (row in ticTacToe.indices) {
        when (ticTacToe[row][ticTacToe.lastIndex -row]) {
            'X'.code -> winO = false
            'O'.code -> winX = false
            else -> {
                winO = false
                winX = false
            }
        }
    }
    if (winX) countXWin += 1
    if (winO) countOWin += 1

    println(when {
        countXWin == countOWin && countXWin == 0 && diffXOCounts < 2 && countEmpty > 0 -> return false//"Game not finished"
        countXWin == countOWin && countXWin == 0 && diffXOCounts < 2 -> "Draw"
        countXWin > countOWin -> "X wins"
        countOWin > countXWin -> "O wins"
        else -> "Impossible"
    })
    return true
}

fun printTTT(ticTacToe: Array<IntArray>) {
    println("---------")
    for (row in ticTacToe.indices) {
        print("|")
        for (column in ticTacToe[row].indices) {
            print(" "+ticTacToe[row][column].toChar())
        }
        println(" |")
    }
    println("---------")
}

fun readTTT(ticTacToe: Array<IntArray>) {
    val rds = readln()
    for (row in ticTacToe.indices) {
        for (column in ticTacToe[row].indices) {
            ticTacToe[row][column] = rds[row * ticTacToe[row].size + column].code
        }
    }
}

fun main() {
    val ticTacToe: Array<IntArray> = Array(3) { IntArray(3) {'_'.code} }
    //readTTT(ticTacToe)
    printTTT(ticTacToe)
    var chXOTurn = 'X'
    while (true) {
        val rds = readln()
        if (!Regex("""\b\d \d\b""").matches(rds)) {
            println("You should enter numbers!")
            continue
        }
        val row = rds[0].digitToInt()
        val col = rds[2].digitToInt()
        if (!(row in 1..3 && col in 1..3)) {
            println("Coordinates should be from 1 to 3!")
            continue
        }
        if (ticTacToe[row-1][col-1] != '_'.code) {
            println("This cell is occupied! Choose another one!")
            continue
        }
        ticTacToe[row-1][col-1] = chXOTurn.code
        printTTT(ticTacToe)
        chXOTurn = if (chXOTurn == 'X') 'O' else 'X'
        if (finishTTT(ticTacToe)) break
    }
    //
}