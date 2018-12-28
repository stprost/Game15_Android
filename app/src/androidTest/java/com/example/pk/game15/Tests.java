package com.example.pk.game15;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Tests {


    @Rule
    public final ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        mainTest();
        chooseLevelTest();
        gameTest();
    }

    private void chooseLevelTest() {
        onView(ViewMatchers.withId(R.id.easy))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.normal))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.hard))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.easy))
                .perform(click());
    }

    private void gameTest() {
        onView(ViewMatchers.withId(R.id.menu_button))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.pause_button))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.menu_button))
                .perform(click());

    }

    private void mainTest() {
        onView(ViewMatchers.withId(R.id.play))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.rules))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.play))
                .perform(click());


    }
}