package com.example.android.testing.BasicSample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void elementsOnView() {
        ViewInteraction emailInput = onView(
                allOf(withId(R.id.emailInput), isDisplayed()));
        emailInput.check(matches(isDisplayed()));

        ViewInteraction passwordInput = onView(
                allOf(withId(R.id.userPasswordInput), isDisplayed()));
        passwordInput.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.saveButton), withText(R.string.save), isDisplayed()));
        button.check(matches(isDisplayed()));
    }

    @Test
    public void tryToLoginWithoutAnyInput() {
        ViewInteraction button = onView(
                allOf(withId(R.id.saveButton), withText(R.string.save), isDisplayed()));
        button.perform(click());

        ViewInteraction emailInput = onView(
                allOf(withId(R.id.emailInput), isDisplayed()));
        emailInput.check(matches(hasErrorText(getResourceString(R.string.email_error))));
    }

    @Test
    public void tryToLoginWithoutPasswordInput() {
        ViewInteraction emailInput = onView(
                allOf(withId(R.id.emailInput), isDisplayed()));

        emailInput.perform(replaceText("valid@email.com"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.saveButton), withText(R.string.save), isDisplayed()));
        button.perform(click());

        ViewInteraction passwordInput = onView(
                allOf(withId(R.id.userPasswordInput), isDisplayed()));
        passwordInput.check(matches(hasErrorText(getResourceString(R.string.password_error))));
    }

    @Test
    public void tryToLoginWithoutEmailInput() {
        ViewInteraction passwordInput = onView(
                allOf(withId(R.id.userPasswordInput), isDisplayed()));
        passwordInput.perform(replaceText("EinPasswortDasMehrAls8ZeichenHat"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.saveButton), withText(R.string.save), isDisplayed()));
        button.perform(click());

        ViewInteraction emailInput = onView(
                allOf(withId(R.id.emailInput), isDisplayed()));
        emailInput.check(matches(hasErrorText(getResourceString(R.string.email_error))));
    }

    @Test
    public void successfullyLogin() {
        ViewInteraction emailInput = onView(
                allOf(withId(R.id.emailInput), isDisplayed()));
        emailInput.check(matches(isDisplayed()));
        emailInput.perform(replaceText("valid@email.com"), closeSoftKeyboard());

        ViewInteraction passwordInput = onView(
                allOf(withId(R.id.userPasswordInput), isDisplayed()));
        passwordInput.check(matches(isDisplayed()));
        passwordInput.perform(replaceText("EinPasswortDasMehrAls8ZeichenHat"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.saveButton), withText(R.string.save), isDisplayed()));
        button.perform(click());

        ViewInteraction welcome = onView(allOf(withText(R.string.welcome_title), isDisplayed()));
        welcome.check(matches(withText(R.string.welcome_title)));
    }

    private String getResourceString(int id) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        return targetContext.getResources().getString(id);
    }
}
