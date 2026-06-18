package com.linkin.stepsandbites.auth

import com.google.firebase.auth.FirebaseAuth

actual fun isFirebaseUserSignedIn(): Boolean =
    FirebaseAuth.getInstance().currentUser != null

actual fun signOutUser() = FirebaseAuth.getInstance().signOut()
