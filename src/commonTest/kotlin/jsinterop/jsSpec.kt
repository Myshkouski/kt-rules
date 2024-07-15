package jsinterop

fun jsSpec(block: SpecContext.() -> Unit) {
    DefaultSpecContext().block()
}