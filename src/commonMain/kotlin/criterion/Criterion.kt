package criterion

import operator.TypedOperator

class Criterion<T, U>(
    private val operator: TypedOperator<T, U>,
    private val value: U,
): TypedCriterion<T> {
    override fun evaluate(value: T): Boolean {
        return operator.match(value, this.value)
    }
}
