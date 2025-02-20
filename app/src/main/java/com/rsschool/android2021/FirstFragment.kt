package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var minValue: TextView
    private lateinit var maxValue: TextView
    private var listener: OnFirstFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnFirstFragmentListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValue = view.findViewById(R.id.min_value)
        maxValue = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            val min = minValue.text.toString()
            val max = maxValue.text.toString()
            if (min.isNotEmpty() && max.isNotEmpty()) {
                if (checking(min, max))
                    listener?.toSecondPage(min.toInt(), max.toInt())
            } else {
                Toast.makeText(context, "Empty field(s)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    private fun checking(minValue: String, maxValue: String): Boolean {
        return try {
            val min = minValue.toInt()
            val max = maxValue.toInt()
            if (min < 0 || max < 0) {
                Toast.makeText(context, "Wrong input: min or max less than 0", Toast.LENGTH_SHORT)
                    .show()
                false
            } else if (min > max) {
                Toast.makeText(context, "Wrong input: min > max", Toast.LENGTH_SHORT).show()
                false
            } else true
        } catch (e: Exception) {
            Toast.makeText(context, "Wrong input: min or max more than Int.max", Toast.LENGTH_SHORT)
                .show()
            false
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}
