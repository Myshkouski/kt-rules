package criterion

sealed interface TypedCriterion<in T> {
    fun evaluate(value: T): Boolean
}
