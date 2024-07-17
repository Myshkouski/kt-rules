package test

import io.github.myshkouski.kotlin.condition.MatchAllRules
import io.github.myshkouski.kotlin.criterion.Criterion
import io.github.myshkouski.kotlin.engine.EngineBuilder
import io.github.myshkouski.kotlin.fact.Fact
import io.github.myshkouski.kotlin.operator.ContainsOperator
import io.github.myshkouski.kotlin.operator.EqualsOperator
import io.github.myshkouski.kotlin.operator.InOperator
import io.github.myshkouski.kotlin.rule.Rule
import io.github.myshkouski.kotlin.storage.Storage
import io.github.myshkouski.kotlin.storage.set
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class EngineTests {
    @Test
    fun engineBuilder() = runTest {
        val engineBuilder = EngineBuilder()

        engineBuilder.addOperator(
            name = "equals",
            operator = EqualsOperator(),
        ).addOperator(
            name = "in",
            operator = InOperator(),
        ).addOperator(
            name = "contains",
            operator = ContainsOperator(),
        ).addRule(
            MatchAllRules(
                Rule(
                    fact = "user.id",
                    criterion = Criterion(
                        operator = "equals",
                        value = 1,
                    ),
                )
            )
        ).addRule(
            Rule(
                fact = "user.group",
                criterion = Criterion(
                    operator = "in",
                    value = arrayOf("security", "admin"),
                ),
            )
        ).addRule(
            Rule(
                fact = "user.role",
                criterion = Criterion(
                    operator = "contains",
                    value = "api-management",
                ),
            )
        )

        val engine = engineBuilder.build()
        assertNotNull(engine)

        val facts: Storage<Fact> = Storage()
        facts.set("user.id", 1)
        facts.set("user.group", "admin")
        facts.set("user.role", arrayOf("api-management"))

        engine.run(facts)
    }
}