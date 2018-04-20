
abstract class BaseGrid<out E> : Grid<E> {
	/** By default, this will return a grid made of each element's toString()'s
	 * first character or an underscore if the element is null. You can override this
	 * behavior passing a lambda to convert each [E] to a [Char] */
	override fun toString(): String {
		return toString { it.toString().first() }
	}
}