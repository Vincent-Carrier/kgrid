import PieceColor.BLACK
import PieceColor.WHITE
import PieceType.PAWN
import com.vincentcarrier.kgrid.core.Cell
import com.vincentcarrier.kgrid.core.get
import com.vincentcarrier.kgrid.core.swap
import com.vincentcarrier.kgrid.implementations.toMutableArrayGrid
import org.junit.Assert.assertEquals
import org.junit.Test

enum class PieceType(val char: Char) {
	KING('K'), QUEEN('Q'), ROOK('R'),
	BISHOP('B'), KNIGHT('N'), PAWN('P')
}

enum class PieceColor { WHITE, BLACK }

data class ChessPiece(val color: PieceColor, val type: PieceType)

fun Char.toChessPiece(): ChessPiece? {
	val type = PieceType.values().find { toUpperCase() == it.char }
	val color = when {
		isUpperCase() -> WHITE
		isLowerCase() -> BLACK
		else -> null
	}

	return if (type != null && color != null) { ChessPiece(color, type) } else null
}

fun ChessPiece?.toChar(): Char {
	return this?.type?.char.also { if (this?.color == BLACK) it?.toLowerCase() } ?: '_'
}

class MutableArrayGridTest {

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
  """.toMutableArrayGrid { char -> char.toChessPiece() }

	@Test fun pawnToE4() {
		val e2 = Cell(4, 6)
		val e4 = Cell(4, 4)
		chessboard.swap(e2, e4)

		println(chessboard.toString { piece -> piece.toChar() })
		assertEquals(ChessPiece(WHITE, PAWN), chessboard[e4])
	}
}