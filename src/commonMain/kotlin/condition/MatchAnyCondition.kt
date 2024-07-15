package condition

class MatchAnyCondition(
    private val conditions: Array<out Condition>
): Condition {
    override suspend fun evaluate(): Boolean {
        return conditions.find { it.evaluate() } !== null
    }
}

fun MatchAnyCondition(condition: Condition, vararg other: Condition): MatchAnyCondition {
    return MatchAnyCondition(conditions = arrayOf(condition) + other)
}
