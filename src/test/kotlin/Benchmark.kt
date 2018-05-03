
import com.vincentcarrier.kgrid.core.BaseGrid
import com.vincentcarrier.kgrid.core.MutableGrid
import com.vincentcarrier.kgrid.core.swap
import com.vincentcarrier.kgrid.implementations.toMutableArrayGrid
import com.vincentcarrier.kgrid.implementations.toMutableListGrid
import org.junit.Test
import kotlin.system.measureNanoTime

val genericArray =
	"""
		|abc
		|def
		|ghi
		""".toMutableArrayGrid { it }

val listGrid =
	"""
		|abc
		|def
		|ghi
		""".toMutableListGrid { it }

class CharGrid(val charArray: CharArray, override val width: Int) : BaseGrid<Char>(),
	MutableGrid<Char> {

	override val height = charArray.size / width

	override fun get(x: Int, y: Int) = charArray[y * width + x]

	override fun set(x: Int, y: Int, value: Char) {
		charArray[y * width + x] = value
	}
}

val primitiveArray = CharGrid(charArrayOf('a','b','c','d','e','f','g','h','i'), 3)

fun swapBenchmark(grid: MutableGrid<Char>): Long {
	return measureNanoTime {
		for (i in 0..100000) {
			for ((x, y, _) in grid) {
				grid.swap(0, 0, x, y)
			}
		}
	}
}

class Benchmark {
	@Test fun benchmark() {
		var primSum = 0L
		var genSum = 0L
		var listSum = 0L
		for (i in 0..10000) {
			genSum += swapBenchmark(genericArray)
			primSum += swapBenchmark(primitiveArray)
			listSum += swapBenchmark(listGrid)
		}
		println("List:      ${listSum}ns")
		println("Primitive: ${primSum}ns")
		println("Generic:   ${genSum}ns")
	}
}

