package rule

import Parameters
import Storage
import condition.Condition

interface Rule {
    fun evaluate(): Parameters
}

private class DefaultRule(
    private val conditions: Storage<Condition>,
) : Rule {
    override fun evaluate(): Parameters {
        TODO()
    }
}
