import com.vincentcarrier.kgrid.core.component1
import com.vincentcarrier.kgrid.core.component2
import com.vincentcarrier.kgrid.core.swap
import com.vincentcarrier.kgrid.implementations.toMutableListGrid
import org.junit.Assert.assertEquals
import org.junit.Test

class MutableListGridTest {

	val grid =
		"""
    |abc
    |def
    """.toMutableListGrid { it }

	@Test	fun get() {
		assertEquals('c', grid[2, 0])
	}

	@Test fun set() {
		grid[2, 0] = 'z'
		assertEquals('z', grid[2, 0])
	}

	@Test fun components() {
		val (width, height) = grid
		assertEquals(3, width)
		assertEquals(2, height)
	}

	@Test fun toString_() {
		assertEquals(
			"""
      |abc
      |def
      """.trimMargin(),
			grid.toString()
		)
	}

	@Test fun swap() {
		grid.swap(0, 0, 1, 0)
		assertEquals('b', grid[0, 0])
		assertEquals('a', grid[1, 0])
	}
}