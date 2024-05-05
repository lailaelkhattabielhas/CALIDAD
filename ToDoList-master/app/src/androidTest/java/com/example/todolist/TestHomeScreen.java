package com.example.todolist;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class TestHomeScreen {
    @Rule
    public ActivityScenarioRule<HomeScreen> activityRule = new ActivityScenarioRule<>(HomeScreen.class);
    @Test
    public void testFirstFragmentScreen() {
        onView(withId(R.id.firstFragment)).perform(click());
        onView(withId(R.id.textousuario)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonCancel() {
        onView(withId(R.id.firstFragment)).perform(click());
        onView(withId(R.id.cancelar)).perform(click());
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }

}
