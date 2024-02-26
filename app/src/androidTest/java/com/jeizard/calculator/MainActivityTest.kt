package com.jeizard.calculator

import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule

import org.junit.Assert.*
import org.junit.Rule

private const val OPERATOR_ADD = "+"
private const val OPERATOR_SUBTRACT = "-"
private const val OPERATOR_MULTIPLY = "×"
private const val OPERATOR_DIVIDE = "÷"
private const val OPERATOR_POWER = "^"
private const val OPERATOR_DOT = "."
private const val OPERATOR_OPEN_BRACKET = "("
private const val OPERATOR_CLOSE_BRACKET = ")"
private const val OPERATOR_PERCENT = "%"
private const val OPERATOR_RECIPROCAL = "^(-1)"
private const val OPERATOR_ROOT = "√"

private const val DIVISION_BY_ZERO_ERROR = "На 0 делить нельзя!"
private const val ROOT_ERROR = "Нельзя брать корень из отрицательного числа!"

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickAllNumbersOnEmptyField_DisplayCorrectNumber() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("2")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_3)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("3")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_4)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("4")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_5)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("5")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_6)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("6")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_7)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("7")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_8)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("8")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_9)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("9")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_0)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("0")))
    }

    @Test
    fun clickAllOperatorsOnEmptyField_DisplayCorrectInput() {
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_subtract)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText(OPERATOR_SUBTRACT)))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_multiply)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_division)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_percent)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_root)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText(OPERATOR_ROOT + OPERATOR_OPEN_BRACKET)))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_reciprocal)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_power)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("")))
    }

    @Test
    fun clickDot_DisplayCorrectInput() {
        onView(withId(R.id.btn_dot)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("0" + OPERATOR_DOT)))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_dot)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_DOT)))
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_DOT + "1")))
        onView(withId(R.id.btn_dot)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_DOT + "1")))
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_DOT + "11")))
    }

    @Test
    fun clickChangeSign_DisplayCorrectInput() {
        onView(withId(R.id.btn_sign)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText(OPERATOR_OPEN_BRACKET + OPERATOR_SUBTRACT)))
        onView(withId(R.id.btn_sign)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("")))

        onView(withId(R.id.btn_sign)).perform(click())
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.result_field)).check(matches(withText(OPERATOR_SUBTRACT + "1")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1")))

        onView(withId(R.id.btn_sign)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText(OPERATOR_OPEN_BRACKET + OPERATOR_SUBTRACT + "1")))

        onView(withId(R.id.btn_sign)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1")))

        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_ADD + "1")))
        onView(withId(R.id.result_field)).check(matches(withText("2")))

        onView(withId(R.id.btn_sign)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_ADD + OPERATOR_OPEN_BRACKET + OPERATOR_SUBTRACT + "1")))
        onView(withId(R.id.result_field)).check(matches(withText("0")))
    }

    @Test
    fun clickOnButtons_DisplayCorrectInput() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1")))

        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_ADD)))

        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_ADD + "2")))
    }

    @Test
    fun calculateResult_DisplayCorrectResult() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_ADD + "2")))
        onView(withId(R.id.result_field)).check(matches(withText("3")))

        onView(withId(R.id.btn_result)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("3")))
        onView(withId(R.id.result_field)).check(matches(withText("")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_subtract)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_SUBTRACT + "2")))
        onView(withId(R.id.result_field)).check(matches(withText("-1")))

        onView(withId(R.id.btn_result)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("-1")))
        onView(withId(R.id.result_field)).check(matches(withText("")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_multiply)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_MULTIPLY + "2")))
        onView(withId(R.id.result_field)).check(matches(withText("2")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_division)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_DIVIDE+ "2")))
        onView(withId(R.id.result_field)).check(matches(withText("0.5")))

        onView(withId(R.id.btn_result)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("0.5")))
        onView(withId(R.id.result_field)).check(matches(withText("")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_power)).perform(click())
        onView(withId(R.id.btn_3)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("2" + OPERATOR_POWER + "3")))
        onView(withId(R.id.result_field)).check(matches(withText("8")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_reciprocal)).perform(click())
        onView(withId(R.id.btn_3)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("2" + OPERATOR_RECIPROCAL)))
        onView(withId(R.id.result_field)).check(matches(withText("0.5")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_percent)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_PERCENT)))
        onView(withId(R.id.result_field)).check(matches(withText("0.01")))

        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_PERCENT + "2")))
        onView(withId(R.id.result_field)).check(matches(withText("0.02")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_root)).perform(click())
        onView(withId(R.id.btn_4)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText(OPERATOR_ROOT + OPERATOR_OPEN_BRACKET + "4")))
        onView(withId(R.id.result_field)).check(matches(withText("2")))
    }

    @Test
    fun clickOnOperatorAfterOtherOperator_ChangeDisplayedOperator() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_ADD)))

        onView(withId(R.id.btn_subtract)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_SUBTRACT)))

        onView(withId(R.id.btn_multiply)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_MULTIPLY)))

        onView(withId(R.id.btn_division)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_DIVIDE)))

        onView(withId(R.id.btn_power)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_POWER)))

        onView(withId(R.id.btn_percent)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_PERCENT)))

        onView(withId(R.id.btn_reciprocal)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_RECIPROCAL)))
    }

    @Test
    fun clickOnButtons_correctProcedure() {
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.result_field)).check(matches(withText("4")))

        onView(withId(R.id.btn_multiply)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("2" + OPERATOR_ADD + "2" + OPERATOR_MULTIPLY + "2")))
        onView(withId(R.id.result_field)).check(matches(withText("6")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_bracket)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_bracket)).perform(click())
        onView(withId(R.id.result_field)).check(matches(withText("4")))

        onView(withId(R.id.btn_multiply)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText(OPERATOR_OPEN_BRACKET + "2" + OPERATOR_ADD + "2" + OPERATOR_CLOSE_BRACKET + OPERATOR_MULTIPLY + "2")))
        onView(withId(R.id.result_field)).check(matches(withText("8")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_root)).perform(click())
        onView(withId(R.id.btn_bracket)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_bracket)).perform(click())
        onView(withId(R.id.result_field)).check(matches(withText("2")))

        onView(withId(R.id.btn_multiply)).perform(click())
        onView(withId(R.id.btn_4)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText(OPERATOR_ROOT + OPERATOR_OPEN_BRACKET + OPERATOR_OPEN_BRACKET + "2" + OPERATOR_ADD + "2" + OPERATOR_CLOSE_BRACKET + OPERATOR_MULTIPLY + "4")))
        onView(withId(R.id.result_field)).check(matches(withText("4")))
    }


    @Test
    fun doubleClickReciprocal_RemoveReciprocal() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_reciprocal)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_RECIPROCAL)))

        onView(withId(R.id.btn_reciprocal)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1")))
    }

    @Test
    fun clickSubtractBeforeNumber_RemoveReciprocal() {
        onView(withId(R.id.btn_subtract)).perform(click())
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText(OPERATOR_SUBTRACT + "12")))
    }

    @Test
    fun divisionByZero_DisplayError() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_division)).perform(click())
        onView(withId(R.id.btn_0)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_DIVIDE + "0")))
        onView(withId(R.id.result_field)).check(matches(withText(DIVISION_BY_ZERO_ERROR)))

        onView(withId(R.id.btn_result)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_DIVIDE + "0")))
        onView(withId(R.id.result_field)).check(matches(withText(DIVISION_BY_ZERO_ERROR)))
    }

    @Test
    fun rootNegativeNumber_DisplayError() {
        onView(withId(R.id.btn_root)).perform(click())
        onView(withId(R.id.btn_subtract)).perform(click())
        onView(withId(R.id.btn_4)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText(OPERATOR_ROOT + OPERATOR_OPEN_BRACKET + OPERATOR_SUBTRACT + "4")))
        onView(withId(R.id.result_field)).check(matches(withText(ROOT_ERROR)))

        onView(withId(R.id.btn_result)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText(OPERATOR_ROOT + OPERATOR_OPEN_BRACKET + OPERATOR_SUBTRACT + "4")))
        onView(withId(R.id.result_field)).check(matches(withText(ROOT_ERROR)))
    }

    @Test
    fun clickingBack_RemovesLastEntry() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_ADD + "2")))
        onView(withId(R.id.result_field)).check(matches(withText("3")))

        onView(withId(R.id.btn_back)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_ADD)))
        onView(withId(R.id.result_field)).check(matches(withText("")))
    }

    @Test
    fun clickingClear_ClearsEntryField() {
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("1" + OPERATOR_ADD + "2")))
        onView(withId(R.id.result_field)).check(matches(withText("3")))

        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("")))
        onView(withId(R.id.result_field)).check(matches(withText("")))
    }

    @Test
    fun useMemoryFunctions_CorrectInputAndProcedure(){
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_2)).perform(click())
        onView(withId(R.id.btn_memory_store)).perform(click())
        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_memory_recall)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("3")))

        onView(withId(R.id.btn_subtract)).perform(click())
        onView(withId(R.id.btn_1)).perform(click())
        onView(withId(R.id.btn_memory_add)).perform(click())
        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_memory_recall)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("5")))

        onView(withId(R.id.btn_add)).perform(click())
        onView(withId(R.id.btn_4)).perform(click())
        onView(withId(R.id.btn_memory_subtract)).perform(click())
        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_memory_recall)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("(-4)")))

        onView(withId(R.id.btn_memory_clear)).perform(click())
        onView(withId(R.id.btn_clear)).perform(click())
        onView(withId(R.id.btn_memory_recall)).perform(click())
        onView(withId(R.id.entry_field)).check(matches(withText("0")))
    }
}