package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.operator.TypedOperator
import io.github.myshkouski.kotlin.rule.Rule
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
interface MutableEngineOperations {
    fun setRule(name: String, rule: Rule): EngineBuilder
    // fun <T> updateRule(rule: TypedRule<T>): EngineBuilder
    // fun <T> removeRule(rule: TypedRule<T>): EngineBuilder

    // fun <T> setCondition(name: String, condition: TypedCriterion<T>): EngineBuilder
    // fun removeCondition(name: String): EngineBuilder

    fun setOperator(name: String, operator: TypedOperator<Any?, Any?>): EngineBuilder
    // fun removeOperator(operator: TypedOperator<*, *>): EngineBuilder
}
