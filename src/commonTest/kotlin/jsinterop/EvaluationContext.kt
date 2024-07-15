package jsinterop

interface EvaluationContext {
    fun <T> expect(value: T): Expect
}
