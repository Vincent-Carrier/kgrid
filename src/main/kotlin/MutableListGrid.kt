
open class MutableListGrid<E>(grid: MutableList<MutableList<E>>)
	: ListGrid<E>(grid), MutableGrid<E> {

	override fun set(x: Int, y: Int, value: E) {
		list[y][x] = value
	}

	final override var Cell.value: E
		get() = get(x, y)
		set(value) {
			set(x, y, value)
		}
}



fun <E> MutableList<MutableList<E>>.toMutableListGrid(): MutableListGrid<E> {
	return MutableListGrid(this)
}

inline fun <E> String.toMutableListGrid(toValue: (Char) -> E): MutableListGrid<E> {
	return trimMargin().lines()
		.map { string ->
			string.map { c ->
				toValue(c)
			}.toMutableList()
		}.toMutableList()
		.toMutableListGrid()
}

inline fun <E, O, R> Grid<E>.combineWith(other: Grid<O>, combine: (E, O) -> R): MutableListGrid<R> {
	require(other.width == width && other.height == height) {
		"Argument grid should have the same dimensions as the receiver grid"
	}

	return MutableList(height) { y ->
		MutableList(width) { x ->
			combine(this[x, y], other[x, y])
		}
	}.toMutableListGrid()
}