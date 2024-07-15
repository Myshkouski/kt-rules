package jsinterop

interface SpecContext {
    fun describe(name: String, block: DescribeContext.() -> Unit)
}

internal class DefaultSpecContext : SpecContext {
    override fun describe(name: String, block: DescribeContext.() -> Unit) {
        DefaultDescribeContext().block()
    }
}
