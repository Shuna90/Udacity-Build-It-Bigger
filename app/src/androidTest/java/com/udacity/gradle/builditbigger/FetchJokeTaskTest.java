package com.udacity.gradle.builditbigger;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by shuna on 12/31/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FetchJokeTaskTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void jokeReturnTest() {
        onView(withId(R.id.button)).perform(click());
        String joke = null;

        /*
        try {
            FetchJokeAsyncTask jokeTask = new FetchJokeAsyncTask() {
                @Override
                protected void onPostExecute(String result) {

                }
            };
            jokeTask.execute();
            joke = jokeTask.get(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            fail("Timed out");
        }

        assertNotNull("Result is null", joke); // the second parameter is the object you are checking for null value
        assertTrue("Result is empty", joke.length() > 0);  // the second parameter is the condition you are testing

        */
        onView(withId(R.id.text_joke))
                .check(matches(not(withText(""))));
    }
}
