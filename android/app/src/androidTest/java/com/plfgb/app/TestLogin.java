package com.plfgb.app;

import android.app.Activity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.Collection;

import androidx.lifecycle.Lifecycle;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.times;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

import static androidx.test.runner.lifecycle.Stage.RESUMED;

@RunWith(AndroidJUnit4.class)
public class TestLogin {




    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void goodLogin() throws Exception {

        onView(withId(R.id.entry)).perform(typeText("loginTest"));

        onView(withId(R.id.ok)).perform(click());

        intended(toPackage("com.plfgb.app"));
        intended(hasComponent(MenuActivity.class.getName()), times(1));

        MenuActivity nouvelleActivite = (MenuActivity) getActivityInstance();

        GlobalState gs = (GlobalState) nouvelleActivite.getApplicationContext();
        assertEquals("loginTest", gs.getClient().getLogin());




    }



    Activity currentActivity = null;

    public Activity getActivityInstance(){
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities =
                         ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (resumedActivities.iterator().hasNext()){
                    currentActivity = (Activity) resumedActivities.iterator().next();
                }
            }
        });

        return currentActivity;
    }




    @Test
    public void testbadlogin() throws Exception {

        onView(withId(R.id.entry)).perform(typeText(""));
        onView(withId(R.id.ok)).perform(click());
        GlobalState gs = (GlobalState) intentsTestRule.getActivity().getApplicationContext();
        assertEquals(true,gs.getClient()==null);

    }



}


