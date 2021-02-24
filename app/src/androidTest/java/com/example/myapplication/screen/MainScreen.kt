package com.example.myapplication

import android.view.View
import androidx.test.espresso.DataInteraction
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.agoda.kakao.common.actions.BaseActions
import com.agoda.kakao.common.builders.ViewBuilder
import com.agoda.kakao.common.views.KBaseView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kaspersky.kaspresso.screens.KScreen
import org.hamcrest.Matcher

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = R.layout.activity_main
    override val viewClass: Class<*>? = MainActivity::class.java
    val bottomNavigationBarView = KBottomNavigationBarView { withId(R.id.nav_view) }
}

class KBottomNavigationBarView : KBaseView<KBottomNavigationBarView>,
    BottomNavigationBarViewViewActions {
    constructor(function: ViewBuilder.() -> Unit) : super(function)
    constructor(parent: Matcher<View>, function: ViewBuilder.() -> Unit) : super(parent, function)
    constructor(parent: DataInteraction, function: ViewBuilder.() -> Unit) : super(parent, function)
}

interface BottomNavigationBarViewViewActions : BaseActions {
    /**
     * Sets the given item id as selected
     *
     * @param id menu item id to be set
     */
    fun setSelectedItem(id: Int) {
        view.perform(object : ViewAction {
            override fun getDescription() = "Sets given item id as selected: $id"

            override fun getConstraints() =
                ViewMatchers.isAssignableFrom(BottomNavigationView::class.java)

            override fun perform(uiController: UiController, view: View) {
                if (view is BottomNavigationView) {
                    view.selectedItemId = id
                }
            }
        })
    }
}
