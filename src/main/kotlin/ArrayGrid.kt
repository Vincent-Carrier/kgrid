
open class ArrayGrid<E>(protected val grid: Array<E>, final override val width: Int)
	: BaseGrid<E>() {

	override fun get(x: Int, y: Int) = grid[y * width + x]

	final override val height = grid.size / width
}

open class MutableArrayGrid<E>(grid: Array<E>, width: Int)
	: ArrayGrid<E>(grid, width), MutableGrid<E> {
	override fun set(x: Int, y: Int, value: E) {
		grid[y * width + x] = value
	}
}

inline fun <reified E> String.toMutableArrayGrid(toValue: (Char) -> E)
		: MutableArrayGrid<E> {

	val s = this.trimMargin().filter { it != '\n' }
	val array = Array(s.length) { i ->
		toValue(s[i])
	}

	return MutableArrayGrid(array, trimMargin().lines().first().length)
}

inline fun <reified E> String.toArrayGrid(toValue: (Char) -> E): ArrayGrid<E> {
	return toMutableArrayGrid { toValue(it) }
}
