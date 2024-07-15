package condition

class MatchAllCondition(
    private val conditions: Array<out Condition>
): Condition {
    override suspend fun evaluate(): Boolean {
        return conditions.all { it.evaluate() }
    }
}

fun MatchAllCondition(condition: Condition, vararg other: Condition): MatchAllCondition {
    return MatchAllCondition(conditions = arrayOf(condition) + other)
}
