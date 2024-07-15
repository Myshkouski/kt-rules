package engine

import Storage
import fact.Fact
import rule.Rule

interface EngineContext {
    val rules: Storage<Rule>
    val facts: Storage<Fact>
}
