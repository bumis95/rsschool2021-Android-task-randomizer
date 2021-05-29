package com.rsschool.android2021

fun CharSequence.toInt(): Int =
    Integer.parseInt(this.toString())

const val INCLUSIVE_RIGHT_BOUND = 1