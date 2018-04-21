

fun <E> Grid<E>.column(x: Int): Iterable<Cell> {
	return Iterable {
		object : Iterator<Cell> {
			var i = 0

			override fun hasNext(): Boolean {
				return i < height
			}

			override fun next(): Cell {
				return Cell(x, i)
			}
		}
	}
}

fun <E> Grid<E>.row(y: Int): Iterable<Cell> {
	return Iterable {
		object : Iterator<Cell> {
			var i = 0

			override fun hasNext(): Boolean {
				return i < width
			}

			override fun next(): Cell {
				return Cell(y, i)
			}
		}
	}
}

