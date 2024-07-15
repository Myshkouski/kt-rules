package jsinterop

import kotlin.test.assertEquals
import kotlin.test.assertNotNull

interface Expect {
    val to: To

    interface To {
        fun <T> equal(value: T)
        val have: Have

        interface Have {
            fun property(name: String)
        }
    }
}

internal class DefaultExpect(
    value: Any?
) : Expect {
    override val to: Expect.To = DefaultTo(value)

    private class DefaultTo(
        private val value: Any?
    ) : Expect.To {
        override fun <T> equal(value: T) {
            assertEquals(value, this.value)
        }

        override val have: Expect.To.Have = DefaultHave(value)

        class DefaultHave(
            private val value: Any?
        ) : Expect.To.Have {
            override fun property(name: String) {
                assertNotNull(value)

                val member = value::class.members.find { member ->
                    member.name == name
                }

                assertNotNull(member)
            }
        }
    }
}
