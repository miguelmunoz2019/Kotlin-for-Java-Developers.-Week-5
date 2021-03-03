package games.gameOfFifteen

import board.Cell
import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game
import games.game2048.Game2048Initializer

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
        GameOfFifteen(initializer )
class GameOfFifteen(private val initializer: GameOfFifteenInitializer) : Game {
    private val board = createGameBoard<Int?>(4)
    override fun initialize() {
var lista=initializer.initialPermutation
        var contador=0
        for (i in 1..4) {
            for (j in 1..4)
            {
                if (contador<15)
                {
                var celda=board.getCell(i,j)
                board[celda]=lista[contador]
                contador+=1
                }
                else
                {
                    var celda=board.getCell(i,j)
                    board[celda]=null
                }
            }
        }

    }

    override fun canMove(): Boolean =true

    override fun hasWon(): Boolean {
        var contador=0
        var mapa=board.getAllCells().toMutableList()
        while (contador<mapa.size-1)
        {
            if (board[mapa[contador]]!=null&&board[mapa[contador+1]]!=null) {
                if (board[mapa[contador]]!! > board[mapa[contador + 1]]!!)
                    return false

            }
            contador+=1
        }
        return true
    }

    override fun processMove(direction: Direction) {
       var vacia:Cell?= board.find { it==null }

        with(board)
        {
            when (direction) {
                Direction.DOWN -> {
                    var posicioni=vacia!!.i
                    var posicionj=vacia!!.j

                    var celdaActual=this.getCell(posicioni,posicionj)
                    var celdaCambio=this.getCell(posicioni-1,posicionj)
                    this[celdaActual]= this[celdaCambio]
                    this[celdaCambio]= null

                }
                Direction.RIGHT -> {
                    var posicioni=vacia!!.i
                    var posicionj=vacia!!.j

                        var celdaActual=this.getCell(posicioni,posicionj)
                        var celdaCambio=this.getCell(posicioni,posicionj-1)
                        this[celdaActual]= this[celdaCambio]
                        this[celdaCambio]= null

                }
                Direction.LEFT -> {
                    var posicioni=vacia!!.i
                    var posicionj=vacia!!.j

                    var celdaActual=this.getCell(posicioni,posicionj)
                    var celdaCambio=this.getCell(posicioni,posicionj+1)
                    this[celdaActual]= this[celdaCambio]
                    this[celdaCambio]= null

                }
                Direction.UP -> {
                    var posicioni=vacia!!.i
                    var posicionj=vacia!!.j

                    var celdaActual=this.getCell(posicioni,posicionj)
                    var celdaCambio=this.getCell(posicioni+1,posicionj)
                    this[celdaActual]= this[celdaCambio]
                    this[celdaCambio]= null

                }
            }
        }
    }

    override fun get(i: Int, j: Int): Int? {
        return board.get(board.getCell(i,j))
    }

}