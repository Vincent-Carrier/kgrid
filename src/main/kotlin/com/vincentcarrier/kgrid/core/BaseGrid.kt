package com.vincentcarrier.kgrid.core


abstract class BaseGrid<out E> : Grid<E> {
	val rows by lazy { 0 until height }

	val columns by lazy { 0 until width }

	open val Cell.value: E
		get() = get(x, y)

	fun Cell.isWithinGrid() = x in columns && y in rows

	inline fun forEach(func: (x: Int, y: Int, E) -> Unit) {
		for (y in rows) {
			for (x in columns) {
				func(x, y, get(x, y))
			}
		}
	}

	inline fun toString(toChar: (E) -> Char?): String {
		return with(StringBuilder()) {
			for (y in rows) {
				if (y != 0) append('\n')
				for (x in columns) {
					append(toChar(get(x, y)) ?: '_')
				}
			}
			toString()
		}
	}

	/** By default, this will return a grid made of each element's toString()'s
	 * first character or an underscore if the element is null. You can override this
	 * behavior passing a lambda to convert each [E] to a [Char] */
	override fun toString(): String {
		return toString { it.toString().first() }
	}
}