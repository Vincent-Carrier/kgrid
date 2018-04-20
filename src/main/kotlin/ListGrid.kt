
open class ListGrid<E>(protected val grid: MutableList<MutableList<E>>) : BaseGrid<E>() {
	init {
		require(grid.all { row -> row.size == grid.first().size }) {
			"Grid must be a rectangle"
		}
	}

	final override val height = grid.size

	final override val width = grid.first().size

	override fun get(x: Int, y: Int) = grid[y][x]
}

fun <E> List<List<E>>.toListGrid(): ListGrid<E> {
	return ListGrid(map { it.toMutableList() }.toMutableList())
}

fun <E> String.toListGrid(toValue: (Char) -> E): ListGrid<E> {
	return toMutableListGrid { toValue(it) }
}
