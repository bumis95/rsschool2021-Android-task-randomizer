package com.rsschool.android2021

interface OnFirstFragmentListener {
    fun toSecondPage(min: Int, max: Int)
}

interface OnSecondFragmentListener {
    fun toFirstPage(prev: Int)
}