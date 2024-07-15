interface Optional<T> {
    val isPresent: Boolean
    val value: T

    companion object
}

private class DefaultOptional<T>(
    isInitialValuePresent: Boolean,
    initialValue: T?,
): Optional<T> {
    override var isPresent: Boolean = isInitialValuePresent
        private set

    private var optionalField: T? = initialValue

    override var value: T
        get() {
            if (!isPresent) {
                throw NullPointerException("Optional does not contain value.")
            }
            return optionalField as T
        }
        set(value: T) {
            synchronized(this) {
                isPresent = true
                optionalField = value
            }
        }
}

fun <T: Any> Optional.Companion.of(value: T): Optional<T> {
    return DefaultOptional(true, value)
}

fun <T> Optional.Companion.ofNullable(value: T?): Optional<T?> {
    return DefaultOptional(true, value)
}

fun <T: Any> Optional.Companion.empty(): Optional<T> {
    return DefaultOptional(false, null)
}
