package test

import io.github.myshkouski.kotlin.criterion.DefaultTypedCriterion
import io.github.myshkouski.kotlin.engine.EngineBuilder
import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.operator.ContainsOperator
import io.github.myshkouski.kotlin.operator.EqualOperator
import io.github.myshkouski.kotlin.rule.TypedRule
import io.github.myshkouski.kotlin.storage.Storage
import io.github.myshkouski.kotlin.storage.set
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class EngineTests {
    @Test
    fun engineBuilder() = runTest {
        val engineBuilder = EngineBuilder()

        engineBuilder.addRule(
            TypedRule(
                fact = "user.id",
                criterion = DefaultTypedCriterion(
                    operator = EqualOperator<Number, Number>(),
                    value = 1,
                ),
            )
        ).addRule(
            TypedRule(
                fact = "user.role",
                criterion = DefaultTypedCriterion(
                    operator = ContainsOperator(),
                    value = "admin",
                ),
            )
        ).addRule(
            TypedRule(
                fact = "user.role",
                criterion = DefaultTypedCriterion(
                    operator = ContainsOperator(),
                    value = "admin",
                ),
            )
        )

        val engine = engineBuilder.build()
        assertNotNull(engine)

        val facts: Storage<Fact> = Storage()
        facts.set("user.id", 1)
        facts.set("user.role", arrayOf("admin"))

        engine.run(facts)
    }
}