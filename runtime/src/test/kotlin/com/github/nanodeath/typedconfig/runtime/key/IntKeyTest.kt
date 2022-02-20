package com.github.nanodeath.typedconfig.runtime.key

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class IntKeyTest {
    @ParameterizedTest
    @MethodSource("parseExpectations")
    fun parseInteger(value: String, expected: Int) {
        IntKey.parse(value) shouldBe expected
    }


    @Test
    fun decimalString() {
        shouldThrow<NumberFormatException> {
            IntKey.parse("1.0")
        }
    }

    @Test
    fun emptyString() {
        shouldThrow<NumberFormatException> {
            IntKey.parse("")
        }
    }

    companion object {
        @JvmStatic
        fun parseExpectations(): Array<Array<Any>> =
            listOf(
                "1" to 1,
                "0" to 0,
                "-1" to -1,
                "1000000" to 1000000
            )
                .map { (value, expected) -> arrayOf<Any>(value, expected) }
                .toTypedArray()
    }
}
