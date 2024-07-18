package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.operator.TypedOperator
import io.github.myshkouski.kotlin.operator.defaultOperators
import io.github.myshkouski.kotlin.rule.Rule
import io.github.myshkouski.kotlin.storage.Storage

interface EngineProperties {
    var rules: Storage<Rule>
    val operators: Storage<TypedOperator<Any?, Any?>>
    val facts: Storage<Fact>
}

internal class DefaultEngineProperties : EngineProperties {
    override val operators: Storage<TypedOperator<Any?, Any?>> = defaultOperators
    override var rules: Storage<Rule> = Storage()
    override val facts: Storage<Fact> = Storage()
}
