package jsinterop

interface DescribeContext : SpecContext, EvaluationContext {
    fun it(name: String, block: ItContext.() -> Unit)
}

internal class DefaultDescribeContext : DescribeContext {
    override fun it(name: String, block: ItContext.() -> Unit) {
        DefaultItContext().block()
    }

    override fun <T> expect(value: T): Expect {
        return DefaultExpect(value)
    }

    override fun describe(name: String, block: DescribeContext.() -> Unit) {
        DefaultDescribeContext().block()
    }
}
