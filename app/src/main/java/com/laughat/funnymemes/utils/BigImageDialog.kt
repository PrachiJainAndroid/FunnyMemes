package com.laughat.funnymemes.utils

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.laughat.funnymemes.R
import kotlinx.android.synthetic.main.customdialog_image.view.*

class BigImageDialog(): DialogFragment() {
    private var imageUrl = ""
    lateinit var  mcontext :Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUrl = arguments?.getString("url")!!
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mcontext= context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater!!.inflate(R.layout.customdialog_image, container, false)
        this.dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        Glide.with(mcontext).load(imageUrl).into(v.iv_dialog)
        return v
    }

    override fun onStart() {
        super.onStart()

        val dialog = dialog
        if (dialog != null) {
            dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(imageUrl: String) =
            BigImageDialog().apply {
                arguments = Bundle().apply {
                    putString("url", imageUrl)
                }
            }
    }
}