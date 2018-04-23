package com.vincentcarrier.kgrid.core


abstract class BaseGrid<out E> : Grid<E> {
	final override val rows by lazy { 0 until height }

	final override val columns by lazy { 0 until width }

	/** By default, this will return a grid made of each element's toString()'s
	 * first character or an underscore if the element is null. You can override this
	 * behavior passing a lambda to convert each [E] to a [Char] */
	override fun toString(): String {
		return toString { it.toString().first() }
	}
}