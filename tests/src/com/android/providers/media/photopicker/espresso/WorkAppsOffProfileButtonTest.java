/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.providers.media.photopicker.espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.android.providers.media.R;
import com.android.providers.media.library.RunOnlyOnPostsubmit;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunOnlyOnPostsubmit
@RunWith(AndroidJUnit4ClassRunner.class)
public class WorkAppsOffProfileButtonTest extends PhotoPickerBaseTest {
    @BeforeClass
    public static void setupClass() throws Exception {
        PhotoPickerBaseTest.setupClass();
        PhotoPickerBaseTest.setUpWorkAppsOffProfileButton();
    }

    @Rule
    public ActivityScenarioRule<PhotoPickerTestActivity> mRule =
            new ActivityScenarioRule<>(PhotoPickerBaseTest.getSingleSelectionIntent());

    @Test
    public void testProfileButton_dialog() {
        final int profileButtonId = R.id.profile_button;

        // Verify profile button is displayed
        onView(withId(profileButtonId)).check(matches(isDisplayed()));
        // Check the text on the button. It should be "Switch to work"
        onView(withText(R.string.picker_work_profile)).check(matches(isDisplayed()));

        // Verify onClick shows a dialog
        onView(withId(profileButtonId)).check(matches(isDisplayed())).perform(click());
        onView(withText(R.string.picker_profile_work_paused_title)).check(
                matches(isDisplayed()));
        onView(withText(R.string.picker_profile_work_paused_msg)).check(matches(isDisplayed()));
        onView(withText(android.R.string.ok)).check(matches(isDisplayed())).perform(click());
    }
}
