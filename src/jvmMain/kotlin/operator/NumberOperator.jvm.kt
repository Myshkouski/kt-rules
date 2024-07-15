package operator

internal actual fun compare(value: Number, other: Number): Int {
    return when {
        value is Byte && other is Byte -> value.compareTo(other)
        value is Short && other is Short -> value.compareTo(other)
        value is Int && other is Int -> value.compareTo(other)
        value is Long && other is Long -> value.compareTo(other)
        value is Float && other is Float -> value.compareTo(other)
        else -> value.toDouble().compareTo(other.toDouble())
    }
}
