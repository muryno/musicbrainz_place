package com.ubn.musicbrainz_place.view.viewServices.baseActivity

import android.app.Application
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ubn.musicbrainz_place.MusicBrainApplication
import com.ubn.musicbrainz_place.R
import com.ubn.musicbrainz_place.utils.CheckInternetConnection
import com.ubn.musicbrainz_place.utils.ProgressDialog


abstract class BaseActivity: AppCompatActivity() {
    private var snackbar: Snackbar? = null

    private var  view: View? = null


    @Volatile
    private var isOn = false




    override fun setContentView(@LayoutRes layoutResID: Int) {
        super.setContentView(layoutResID)
        view = getView()
        if (view != null) {

            //if internet is off it display a buttom snack
            snackbar = Snackbar.make(view!!, "Check your internet connection.", Snackbar.LENGTH_INDEFINITE)
            val snackBarView = snackbar!!.view
            snackBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            val textView= snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.gravity = View.TEXT_ALIGNMENT_CENTER
            textView.setTextColor(ContextCompat.getColor(this, R.color.white))


        }
    }

    private fun getView(): View? {
        val vg = findViewById<ViewGroup>(android.R.id.content)
        var rv: View? = null

        if (vg != null)
            rv = vg.getChildAt(0)
        if (rv == null)
            rv = window.decorView.rootView
        return rv
    }


    //creating generic snackBar for all class that will implement
    protected fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //creating generic progress dialog for all class that will implement
    protected  fun  progressDialog(message: String) : AlertDialog? {
        return  ProgressDialog.setProgressDialog(this, message)

    }



    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }



    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
        super.onPause()
        isOn = false
    }

    override fun onResume() {
        super.onResume()
        registerInternetCheckReceiver()
        isOn = true
    }




    private fun registerInternetCheckReceiver() {
        val internetFilter = IntentFilter()
        internetFilter.addAction("android.net.wifi.STATE_CHANGE")
        internetFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(broadcastReceiver, internetFilter)
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (CheckInternetConnection.isOnline()) {
                snackbar?.dismiss()
            } else {
                snackbar?.show()
            }
        }
    }




}
