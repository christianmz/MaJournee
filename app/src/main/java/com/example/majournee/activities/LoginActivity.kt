package com.example.majournee.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.majournee.R
import com.example.majournee.mAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.newTask

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            signIn(
                et_login_email.text.toString(),
                et_login_password.text.toString()
            )
        }

        btn_create_account.setOnClickListener {
            createAccount(
                et_login_email.text.toString(),
                et_login_password.text.toString()
            )
        }
    }

    private fun createAccount(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                startActivity(intentFor<MainActivity>().newTask().clearTask())
            } else {
                try {
                    throw it.exception!!
                } catch (e: FirebaseAuthUserCollisionException) {
                    longToast(R.string.email_already_exists)
                } catch (e: Exception) {
                    longToast("${R.string.error} ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }

    private fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                startActivity(intentFor<MainActivity>().newTask().clearTask())
            } else {
                try {
                    throw it.exception!!
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    longToast(R.string.wrong_password)
                } catch (e: FirebaseAuthInvalidUserException) {
                    longToast(R.string.not_found_user)
                } catch (e: Exception) {
                    longToast("${R.string.error} ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }
}
