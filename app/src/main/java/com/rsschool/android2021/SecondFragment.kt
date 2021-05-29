package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment(), MainActivity.OnBackPressedListener {

    private var backButton: Button? = null
    private var result: TextView? = null
    private var listener: OnSecondFragmentListener? = null
    private var generateNumber: Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnSecondFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.setOnBackPressedListener(this)

        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        generateNumber = generate(min, max)
        result?.text = generateNumber.toString()

        backButton?.setOnClickListener {
            listener?.toFirstPage(generateNumber)
        }
    }

    override fun doBack() {
        if (requireActivity().supportFragmentManager.backStackEntryCount > 0) {
            listener?.toFirstPage(generateNumber)
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    private fun generate(min: Int, max: Int): Int =
        Random.nextInt(min, max + INCLUSIVE_RIGHT_BOUND)

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args
            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}
