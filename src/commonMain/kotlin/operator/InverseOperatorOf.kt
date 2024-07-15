package operator

open class InverseOperatorOf<T, U>(
    private val operator: TypedOperator<T, U>
) : TypedOperator<T, U> {
    override fun match(value: T, operatorValue: U): Boolean {
        return operator.match(value, operatorValue).not()
    }
}

internal fun <T, U> TypedOperator<T, U>.inverse(): TypedOperator<T, U> {
    return InverseOperatorOf(this)
}
