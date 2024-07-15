@JvmInline
value class StringValue(
    override val value: String
) : JsonTypedValue<String>