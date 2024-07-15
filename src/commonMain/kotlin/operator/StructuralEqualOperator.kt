package operator

class StructuralEqualOperator<T, U> : TypedOperator<T, U> {
    override fun match(value: T, operatorValue: U): Boolean {
        return value == operatorValue
    }
}