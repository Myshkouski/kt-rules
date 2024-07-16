package io.github.myshkouski.kotlin

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
        private set(value: T) {
            synchronized(this) {
                isPresent = true
                optionalField = value
            }
        }
}

fun <T> Optional.Companion.of(value: T): Optional<out T> {
    return DefaultOptional(true, value)
}

fun <T> Optional.Companion.ofNullable(value: T?): Optional<out T?> {
    return DefaultOptional(true, value)
}

fun <T> Optional.Companion.empty(): Optional<out T> {
    return DefaultOptional(false, null)
}
