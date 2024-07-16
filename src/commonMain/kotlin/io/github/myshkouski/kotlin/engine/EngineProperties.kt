package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.Storage
import io.github.myshkouski.kotlin.createStorage
import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.rule.Rule

interface EngineProperties {
    var rules: Array<Rule>
    val facts: Storage<Fact>
}

internal class DefaultEngineProperties(
) : EngineProperties {
    override var rules: Array<Rule> = arrayOf()
    override val facts: Storage<Fact> = createStorage()
}
