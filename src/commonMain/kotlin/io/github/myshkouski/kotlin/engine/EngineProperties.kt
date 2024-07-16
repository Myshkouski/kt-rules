package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.operator.TypedOperator
import io.github.myshkouski.kotlin.operator.defaultOperators
import io.github.myshkouski.kotlin.rule.Rule
import io.github.myshkouski.kotlin.storage.Storage

interface EngineProperties {
    var rules: Array<Rule>
    val operators: Storage<TypedOperator<Any?, Any?>>
    val facts: Storage<Fact>
}

internal class DefaultEngineProperties : EngineProperties {
    override val operators: Storage<TypedOperator<Any?, Any?>> = defaultOperators
    override var rules: Array<Rule> = arrayOf()
    override val facts: Storage<Fact> = Storage()
}
