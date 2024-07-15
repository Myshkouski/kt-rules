package jsinterop

interface ItContext {
    fun <T> expect(value: T): Expect
}

class DefaultItContext : ItContext {
    override fun <T> expect(value: T): Expect {
        return DefaultExpect(value)
    }
}
