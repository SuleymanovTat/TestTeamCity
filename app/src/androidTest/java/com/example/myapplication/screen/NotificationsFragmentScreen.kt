package com.example.myapplication

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KTextView
import com.example.myapplication.ui.notifications.NotificationsFragment
import com.kaspersky.kaspresso.screens.KScreen
import org.hamcrest.Matcher

object NotificationsFragmentScreen : KScreen<NotificationsFragmentScreen>() {

    override val layoutId: Int? = R.layout.fragment_notifications
    override val viewClass: Class<*>? = NotificationsFragment::class.java

    val recycler: KRecyclerView = KRecyclerView({
        withId(R.id.recyclerView)
    }, itemTypeBuilder = {
        itemType(::ImageItem)
        itemType(::Item)
    })

}

class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {

    val textViewLarge: KTextView = KTextView(parent) { withId(R.id.textViewLarge) }
    val textViewSmall: KTextView = KTextView(parent) { withId(R.id.textViewSmall) }
}

class ImageItem(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {

    val imageView: KImageView = KImageView(parent) { withId(R.id.imageView) }
}

