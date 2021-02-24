package com.example.myapplication

import android.os.SystemClock
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class RecyclerTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test() =
        run {
            step("Open Main Screen") {
                testLogger.i("I am testLogger")
                activityTestRule.launchActivity(null)
                MainScreen {
                    bottomNavigationBarView.isVisible()
                    testLogger.i("container and  bottomNavigationBar isVisible")
                    bottomNavigationBarView {
                        setSelectedItem(R.id.navigation_home)
                        SystemClock.sleep(1500);
                        setSelectedItem(R.id.navigation_dashboard)
                        SystemClock.sleep(1500);
                        setSelectedItem(R.id.navigation_notifications)
                        testLogger.i("click tab  NavigationBar")
                    }
                }
            }
            step("NotificationsFragmentScreen Screen") {
                NotificationsFragmentScreen {
                    recycler.isVisible()
//                    https://www.raywenderlich.com/1505688-ui-testing-with-kakao-tutorial-for-android-getting-started
                    recycler {
                        firstChild<Item> {
                            isVisible()
                            textViewSmall { hasText("кот") }
                            click()
                        }
                        SystemClock.sleep(1500);
                        childAt<Item>(1) {
                            isVisible()
                            textViewLarge { hasText("1 element") }
                            click()
                        }
                        SystemClock.sleep(1500);
                        scrollToEnd()
                    }
                    testLogger.i("END NotificationsFragmentScreen")
                }
            }
        }
}
