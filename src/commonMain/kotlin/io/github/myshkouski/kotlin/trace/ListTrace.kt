package io.github.myshkouski.kotlin.trace

class ListTrace : EvaluationTrace {
    private val mutableEventList: MutableList<Event> = mutableListOf()

    val event: List<Event>
        get() = mutableEventList.toList()

    override fun push(event: Event) {
        mutableEventList.add(event)
    }
}