package com.example.pk.game15;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Random;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class Tests {

    private int[] id = new int[]{R.id.c0, R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8, R.id.c9, R.id.c10, R.id.c11, R.id.c12, R.id.c13, R.id.c14, R.id.c15, R.id.c16, R.id.c17, R.id.c18, R.id.c19, R.id.c20, R.id.c21, R.id.c22, R.id.c23, R.id.c24, R.id.c25, R.id.c26, R.id.c27, R.id.c28, R.id.c29, R.id.c30, R.id.c31, R.id.c32, R.id.c33, R.id.c34, R.id.c35};
    @Rule
    public final ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        mainTest();
        chooseLevelTest();
        gameTest();
    }

    private void mainTest() {
        onView(ViewMatchers.withId(R.id.play))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.rules))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.play))
                .perform(click());


    }

    private void chooseLevelTest() {
        onView(ViewMatchers.withId(R.id.easy))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.normal))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.hard))
                .check(matches(isDisplayed()));
    }


    private void gameTest() {
        onView(ViewMatchers.withId(R.id.easy))
                .perform(click());

        onView(ViewMatchers.withId(R.id.menu_button))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.pause_button))
                .check(matches(isDisplayed()));

        check(4);

        onView(ViewMatchers.withId(R.id.normal))
                .perform(click());

        onView(ViewMatchers.withId(R.id.menu_button))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.pause_button))
                .check(matches(isDisplayed()));

        check(5);

        onView(ViewMatchers.withId(R.id.hard))
                .perform(click());

        onView(ViewMatchers.withId(R.id.menu_button))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.pause_button))
                .check(matches(isDisplayed()));

        check(6);

    }

    private void check(int side) {
        int moves = 20;
        int blank = side * side - 1;
        ArrayList<Integer> neigh = new ArrayList<Integer>();
        for (int i = 0; i < moves; i++) {
            if (blank % side < (side - 1)) neigh.add(blank + 1);
            if (blank % side > 0) neigh.add(blank - 1);
            if (blank / side < (side - 1)) neigh.add(blank + side);
            if (blank / side > 0) neigh.add(blank - side);
            int random = new Random().nextInt(neigh.size());
            onView(withId(id[neigh.get(random)]))
                    .perform(click());
            checkDisplayed(side);
            blank = neigh.get(random);
            neigh.clear();
        }

        pressBack();
    }

    private void checkDisplayed(int side) {
        for (int i = 0; i < side; i++) {
            onView(ViewMatchers.withId(id[i]))
                    .check(matches(isDisplayed()));
        }
    }

}