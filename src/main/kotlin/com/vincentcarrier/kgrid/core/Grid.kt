package com.vincentcarrier.kgrid.core


interface Grid<out E> {
	val width: Int

	val height: Int

	operator fun get(x: Int, y: Int): E
}

operator fun <E> Grid<E>.component1() = width

operator fun <E> Grid<E>.component2() = height

fun <E> Grid<E>.orthogonallyAdjacent(x: Int, y: Int): List<Cell> {
	val above = if (y > 0) Cell(x, y - 1) else null
	val below = if (y < height - 1) Cell(x, y + 1) else null
	val left = if (x > 0) Cell(x - 1, y) else null
	val right = if (x < width - 1) Cell(x + 1, y) else null

	return listOfNotNull(above, below, left, right)
}

fun <E> Grid<E>.adjacent(x: Int, y: Int): List<Cell> {
	val topLeft = if (y > 0 && x > 0) Cell(x - 1, y - 1) else null
	val topRight = if (y < height - 1) Cell(x, y + 1) else null
	val bottomLeft = if (x > 0 && y < height - 1) Cell(x - 1, y) else null
	val bottomRight = if (x < width - 1 && y < height - 1) Cell(
		x + 1,
		y
	) else null

	val diagonallyAdjacent = listOfNotNull(topLeft, topRight, bottomLeft, bottomRight)

	return orthogonallyAdjacent(x, y) + diagonallyAdjacent
}



