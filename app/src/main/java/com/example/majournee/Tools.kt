package com.example.majournee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth

/** Singletons **/
val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


/** Extensions */
fun ViewGroup.inflate(layoutId: Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)