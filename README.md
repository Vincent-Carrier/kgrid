<pre>
enum class PieceType(val char: Char) {
	KING('K'), QUEEN('Q'), ROOK('R'),
	BISHOP('B'), KNIGHT('N'), PAWN('P')
}

enum class PieceColor { WHITE, BLACK }

data class ChessPiece(val color: PieceColor, val type: PieceType)

fun Char.toChessPiece(): ChessPiece? = ...

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
  """<b>.toMutableArrayGrid { char -> char.toChessPiece() }</b>

class MutableArrayGridTest {

	@Test fun pawnToE4() {
		val e2 = <b>Cell(4, 6)</b>
		val e4 = Cell(4, 4)
		chessboard.<b>swap(e2, e4)</b>

		assertEquals(ChessPiece(WHITE, PAWN), <b>chessboard[e4]</b>)
	}
}
</pre>