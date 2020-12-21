package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.google.firebase.analytics.FirebaseAnalytics

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    var count = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        textView.setOnClickListener {
            activity?.let {
                Log.e("my", "click")
                val mFirebaseAnalytics = FirebaseAnalytics.getInstance(it);
                val bundle = Bundle()
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "99")
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name99")
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

                val params = Bundle()
                params.putString("image_name", "image_name")
                params.putString("full_text", "full_text")
                mFirebaseAnalytics.logEvent("share_image", params)

                count++
                val paramsComment = Bundle()
                paramsComment.putInt("count", count)
                mFirebaseAnalytics.logEvent("delete_comments", params)

                mFirebaseAnalytics.setUserProperty("favorite_food", "apple")
            }
        }
        return root
    }
}