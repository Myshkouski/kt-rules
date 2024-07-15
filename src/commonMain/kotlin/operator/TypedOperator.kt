package operator

fun interface TypedOperator<in T, in U> {
    fun match(value: T, operatorValue: U): Boolean
}

typealias Operator = TypedOperator<*, *>
