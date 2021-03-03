package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard =  InitialSquareBoard(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = InitialGameBoard(width)

open class InitialSquareBoard (override val width: Int):SquareBoard
{
    protected var celdas:List<Cell> = mutableListOf<Cell>()

    init {
        var fila:Int = 1
        while ( fila<=width)
        {
            var columna:Int = 1
            while ( columna<=width)
            {

                celdas +=Cell(fila,columna)

                columna+=1
            }
            fila+=1
        }
    }
    override fun getCellOrNull(i: Int, j: Int): Cell? {

        return if (i >width || j>width||i <1 || j<1)
            null
        else
            celdas[(i*width)-(width-j)-1]


    }

    override fun getCell(i: Int, j: Int): Cell {
        var a= getCellOrNull(i, j)
        if (a==null)
            throw IllegalArgumentException("Incorrect values")
        else
            return a
    }

    override fun getAllCells(): Collection<Cell> {
        return celdas
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        var first=jRange.first
        var last=jRange.last
        var ascendente= first<last
        if (first !in 1..width)
        {
            if (first>width)
                first=width
            if (first<1)
                first=1
        }
        if (last !in 1..width)
        {
            if (last>width)
            {
                last=width
            }
            if (last<1)
            {
                last=1
            }
        }
        var rango=IntProgression.fromClosedRange(first,last,1)
        if (!ascendente)
        {
            rango=IntProgression.fromClosedRange(last,first,1)
        }
        var fila:List<Cell> = mutableListOf<Cell>()
        for (n in rango)
        {
            fila+=getCell(i,n)
        }

        if (ascendente)
            return fila
        else
            return fila.asReversed()
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        var first=iRange.first
        var last=iRange.last
        var ascendente= first<last
        if (first !in 1..width)
        {
            if (first>width)
                first=width
            if (first<1)
                first=1
        }
        if (last !in 1..width)
        {
            if (last>width)
            {
                last=width
            }
            if (last<1)
            {
                last=1
            }
        }
        var rango=IntProgression.fromClosedRange(first,last,1)
        if (!ascendente)
        {
            rango=IntProgression.fromClosedRange(last,first,1)
        }
        var columna:List<Cell> = mutableListOf<Cell>()
        for (n in rango)
            columna+= getCell(n,j)
        if (ascendente)
            return columna
        else
            return columna.asReversed()
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        var fila=this.i
        var columna=this.j
        when(direction)
        {
            UP->fila-=1
            DOWN->fila+=1
            LEFT->columna-=1
            RIGHT->columna+=1
        }
        return getCellOrNull(fila,columna)
    }

}
class InitialGameBoard<T> (width: Int):GameBoard<T>,InitialSquareBoard(width)
{
    private var mapa = mutableMapOf<Cell,T?>()

    init {
        this.celdas.forEach { mapa[it] = null}
    }
    fun getMap():Map<Cell,T?>{
        return mapa
    }

    override fun get(cell: Cell): T? {
        return mapa[cell]
    }
    override fun set(cell: Cell, value: T?) {

        mapa.plusAssign((Pair(cell,value)))
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {

        return mapa.filterValues { predicate(it)}.keys
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return filter(predicate).firstOrNull()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return mapa.filterValues { predicate(it) }.isNotEmpty()||mapa.size<celdas.size
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return mapa.filterValues { predicate (it) }.size== celdas.size
    }

}