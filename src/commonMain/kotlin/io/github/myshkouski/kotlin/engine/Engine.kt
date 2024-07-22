package io.github.myshkouski.kotlin.engine

import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.fact.ValueProvider
import io.github.myshkouski.kotlin.rule.Rule
import io.github.myshkouski.kotlin.rule.RuleContext
import io.github.myshkouski.kotlin.storage.Storage
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

interface Engine {
    suspend fun run(facts: Storage<out Fact>): Storage<Boolean>
}

internal class DefaultEngine(
    private val context: EngineProperties
) : Engine {
    override suspend fun run(facts: Storage<out Fact>): Storage<Boolean> {
        val results = Storage<Boolean>()

        val ruleContext = RuleContext(facts, context.operators, trace = null)

        for ((name, rule) in context.rules.entries()) {
            val result = try {
                rule.evaluate(ruleContext)
            } catch (e: Throwable) {
                false
            }

            results.set(name, result)

            if (!result) {
                break
            }
        }

        return results
    }
}

@OptIn(ExperimentalJsExport::class)
@JsExport.Ignore
@JsName("createEngine")
fun Engine(): Engine {
    return DefaultEngine(context = DefaultEngineProperties())
}

@DslMarker
annotation class RulesScopeDsl

@DslMarker
annotation class RuleScopeDsl

interface ExactRuleScope {
    // infix fun property(name: String)
    // infix fun operator(name: String)
    // infix fun value(value: Any?)
    infix fun String.should(operator: String): ConditionBuilder
    infix fun String.shouldNot(operator: String): ConditionBuilder
}

interface Condition {
    infix fun and(condition: () -> Condition): Condition
    infix fun and(condition: Condition): Condition
    // infix fun and(name: String): String
}

interface ConditionBuilder {
    infix fun to(value: Any?): Condition
    infix fun than(value: Any?): Condition
}

@RuleScopeDsl
interface RuleScope {
    fun all(init: RuleScope.() -> Unit)
    fun any(init: RuleScope.() -> Unit)
    // fun exact(init: ExactRuleScope.() -> Unit)
    infix fun String.shouldBe(operator: String): ConditionBuilder
    infix fun String.shouldNotBe(operator: String): ConditionBuilder
}



@RulesScopeDsl
interface RulesScope : RuleScope {

}

@RulesScopeDsl
interface EngineScope {
    fun rules(init: RulesScope.() -> Unit)

    fun operator(name: String)
    fun rule(name: String, rule: Rule)
    fun <T> fact(name: String, value: ValueProvider<T>)
}

fun runEngine(init: EngineScope.() -> Unit) {
    val scope: EngineScope = TODO()
    scope.init()

}

fun test() {
    runEngine {
        rules {
            any {
                all {
                    let {
                        "event.type" shouldBe "equal" to "temperature"
                    } and {
                        "event.payload.value" shouldNotBe "less" to 10
                    }
                }
                all {

                }
            }
        }
    }
}
