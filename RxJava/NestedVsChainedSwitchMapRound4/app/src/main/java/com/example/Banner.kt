package com.example.nestedvschainedswitchmap

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class Banner(context: Context, parentView: ViewGroup) {

    private val banner: View? = LayoutInflater.from(context).inflate(R.layout.banner, parentView)
    private var bannerText = banner?.findViewById(R.id.banner_text) as? TextView
    private var bannerHeight = 200

    init {

        banner?.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, bannerHeight)
        banner?.visibility = View.GONE

    }

    fun showTlcSuccessBanner() {
        bannerText?.text = "Trailer Light Check Started"
        bannerText?.setTextColor(Color.GREEN)
        banner?.visibility = View.VISIBLE
    }

    fun showGenericErrorBanner() {
        bannerText?.text = "Something went wrong, please try again"
        bannerText?.setTextColor(Color.RED)
        banner?.visibility = View.VISIBLE
    }

    fun showNoConnectionBanner() {
        bannerText?.text = "No Internet Connection"
        bannerText?.setTextColor(Color.RED)
        banner?.visibility = View.VISIBLE
    }


    fun hideBanner() {
        banner?.visibility = View.GONE
    }

}


