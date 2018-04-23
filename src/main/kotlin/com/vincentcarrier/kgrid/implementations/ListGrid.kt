package com.vincentcarrier.kgrid.implementations

import com.vincentcarrier.kgrid.core.BaseGrid

open class ListGrid<E>(protected val list: MutableList<MutableList<E>>) : BaseGrid<E>() {
	init {
		require(list.all { row -> row.size == list.first().size }) {
			"Grid must be a rectangle but rows were of differing sizes"
		}
	}

	final override val height = list.size

	final override val width = list.first().size

	override fun get(x: Int, y: Int) = list[y][x]
}

fun <E> List<List<E>>.toListGrid(): ListGrid<E> {
	return ListGrid(map { it.toMutableList() }.toMutableList())
}

inline fun <E> String.toListGrid(toValue: (Char) -> E): ListGrid<E> {
	return toMutableListGrid { toValue(it) }
}
