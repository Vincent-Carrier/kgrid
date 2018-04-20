
/** Defines a 2D location within a grid */
data class Cell(val x: Int, val y: Int)

operator fun <E> Grid<E>.get(c: Cell): E = get(c.x, c.y)

fun <E> Grid<E>.adjacent(c: Cell): List<Cell> {
	return adjacent(c.x, c.y)
}

operator fun <E> MutableGrid<E>.set(c: Cell, value: E) {
	set(c.x, c.y, value)
}

fun <E> MutableGrid<E>.swap(x1: Int, y1: Int, c2: Cell) {
	swap(x1, y1, c2.x, c2.y)
}

fun <E> MutableGrid<E>.swap(c1: Cell, c2: Cell) {
	swap(c1.x, c1.y, c2)
}