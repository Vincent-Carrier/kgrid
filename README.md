# kgrid

Since Kotlin doesn't have collection literals (i.e. `[1, 2, 3]`), I made this small library to make it easier to work with
and test grids. Very useful for games like chess or checkers 😁

### Download
`implementation 'com.vincentcarrier:kgrid:1.0.1'`

### Example

<pre>
data class ChessPiece(val color: Color, val type: Type) {
    enum class Color { WHITE, BLACK }

    enum class Type(val char: Char) {
        KING('K'), QUEEN('Q'), ROOK('R'),
        BISHOP('B'), KNIGHT('N'), PAWN('P')
    }
}

fun Char.toChessPiece(): ChessPiece? = ...


class ChessboardTest {

    // Triple quotes and pipes are important here to trim out white space
    val chessboard =
      """
      |rnbqkbnr
      |pppppppp
      |________
      |________
      |________
      |________
      |PPPPPPPP
      |RNBQKBNR
      """<b>.toMutableGrid { char -> char.toChessPiece() }</b>

    @Test fun `pawn to e4`() {
        val e2 = <b>Cell(4, 6)</b> // A Cell is a simple, immutable data class that holds XY coordinates
        val e4 = Cell(4, 4)
        chessboard.<b>swap(e2, e4)</b>

        assertEquals(ChessPiece(WHITE, PAWN), <b>chessboard[e4]</b>)
    }
}
</pre>

### How it works

The library is built around the `Grid<E>` interface
```
interface Grid<out E> {
    val width: Int

    val height: Int

    operator fun get(x: Int, y: Int): E
}
```
and its mutable version,  `MutableGrid<E>`
```
interface MutableGrid<E> : Grid<E> {
    operator fun set(x: Int, y: Int, value: E)
}
```

The library provides the following implementations:
* `ArrayGrid<E>` and `MutableArrayGrid<E>`, backed by a single array
* `ListGrid<E>` and `MutableListGrid<E>`, backed by a list of lists
