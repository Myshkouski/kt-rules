package operator

class ContainsOperator<T: U, U> : TypedOperator<Array<out T>, U> {
    override fun match(value: Array<out T>, operatorValue: U): Boolean {
        return value.contains(operatorValue)
    }
}