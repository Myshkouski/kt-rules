package operator

class NotEqualOperator<T, U> : TypedOperator<T, U> by EqualOperator<T, U>().inverse()
