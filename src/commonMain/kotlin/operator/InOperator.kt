package operator

class InOperator<T, U: T> : TypedOperator<T, Array<out U>> {
    override fun match(value: T, operatorValue: Array<out U>): Boolean {
        return operatorValue.contains(value)
    }
}
