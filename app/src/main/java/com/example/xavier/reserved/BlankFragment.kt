package com.example.xavier.reserved

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.xavier.R
import com.example.xavier.base.viewstratum.fragment.SimpleDecorViewFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment : SimpleDecorViewFragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun init() {

    }

    override fun onPrepare() {
        param1?.let { setDefaultTitle(it) }
    }

    override fun contentLayoutRes(): Int {
        return R.layout.fragment_blank
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}