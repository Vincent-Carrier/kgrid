package com.vincentcarrier.kgrid.core

abstract class BaseGrid<out E> : Grid<E> {
	final override val rows by lazy { 0 until height }

	final override val columns by lazy { 0 until width }

	private val iterator by lazy {
		object : AbstractIterator<Triple<Int, Int, E>>() {
			var x = 0
			var y = 0
			val xMax = columns.last
			val yMax = rows.last

			override fun computeNext() {
				if (y > yMax) done() else {
					setNext(Triple(x, y, get(x, y)))
					if (x < xMax) x += 1
					else {
						y += 1
						x = 0
					}
				}
			}
		}
	}

	override fun iterator(): Iterator<Triple<Int, Int, E>> = iterator

	/** By default, this will return a grid made of each element's toString()'s
	 * first character or an underscore if the element is null. You can override this
	 * behavior passing a lambda to convert each [E] to a [Char] */
	override fun toString(): String {
		return toString { it.toString().first() }
	}
}