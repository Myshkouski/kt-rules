package engine

import criterion.TypedCriterion
import operator.TypedOperator
import rule.TypedRule

interface MutableEngine : Engine {
    fun <T> addRule(rule: TypedRule<T>): EngineBuilder
    // fun <T> updateRule(rule: TypedRule<T>): EngineBuilder
    // fun <T> removeRule(rule: TypedRule<T>): EngineBuilder

    fun <T> setCondition(name: String, condition: TypedCriterion<T>): EngineBuilder
    // fun removeCondition(name: String): EngineBuilder

    fun <T, U> addOperator(name: String, operator: TypedOperator<T, U>): EngineBuilder
    // fun removeOperator(operator: TypedOperator<*, *>): EngineBuilder
}
