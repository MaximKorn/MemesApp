package com.pack.memesapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod

import android.view.MotionEvent
import android.view.View

import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pack.memesapp.R
import com.pack.memesapp.network.models.AuthInfo
import com.pack.memesapp.network.models.LoginUserInfo
import com.pack.memesapp.network.NetworkService
import com.pack.memesapp.repo.UserInfoRepo

import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthActivity : AppCompatActivity() {

    private lateinit var passwordFieldText: ExtendedEditText
    private lateinit var passwordField: TextFieldBoxes
    private lateinit var loginFieldText: ExtendedEditText
    private lateinit var loginField: TextFieldBoxes
    private lateinit var passwordVisibilityButton: ImageButton
    private lateinit var spinner: ProgressBar
    private lateinit var loginButton: Button
    private lateinit var errorTextView: View
    private lateinit var textView: TextView

    private val passwordHintString = R.string.password_hint
    private val emptyFieldErrorString = R.string.empty_field_error

    private val loginUserInfo = LoginUserInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initializeVars()

        passwordFieldText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        passwordFieldText.transformationMethod = PasswordTransformationMethod.getInstance()
        passwordFieldText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length < 6) {
                    passwordField.helperText = getString(passwordHintString)
                } else {
                    passwordField.helperText = " "
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        passwordFieldText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            run {
                if (!hasFocus && passwordFieldText.text.isEmpty()) {
                    passwordField.setError(getString(emptyFieldErrorString), false)
                }
            }
        }

        loginFieldText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            run {
                if (!hasFocus && loginFieldText.text.isEmpty()) {
                    loginField.setError(getString(emptyFieldErrorString), false)
                }
            }
        }
    }

    fun changePasswordVisibility(view: View) {
        if (passwordFieldText.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            makePasswordVisible()
        } else {
            makePasswordInvisible()
        }
    }

    fun login(view: View) {
        if (loginFieldText.text.isEmpty() || passwordFieldText.text.length < 6) {
            return
        }

        spinner.visibility = View.VISIBLE
        loginButton.text = ""

        loginUserInfo.login = "qwerty"
        loginUserInfo.password = passwordFieldText.text.toString()

        Handler().postDelayed({
            NetworkService.authClient.login(loginUserInfo)?.enqueue(object : Callback<AuthInfo?> {

                override fun onResponse(call: Call<AuthInfo?>, response: Response<AuthInfo?>) {
                    if (response.isSuccessful) {
                        val requestResult = response.body()
                        if (requestResult != null) {
                            val repo = UserInfoRepo(applicationContext)
                            repo.saveUserData(requestResult)
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                        }
                    } else {
                        val errorResponse: Error? = Gson().fromJson(
                            response.errorBody()?.charStream(),
                            object : TypeToken<Error>() {}.type
                        )

                        errorTextView.visibility = View.VISIBLE
                        textView.visibility = View.VISIBLE
                    }

                    spinner.visibility = View.INVISIBLE
                    loginButton.text = getString(R.string.sign_in)
                }

                override fun onFailure(call: Call<AuthInfo?>, t: Throwable) {}
            })
        }, 1500)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (errorTextView.y > event.rawY && errorTextView.visibility != View.GONE) {
            errorTextView.visibility = View.GONE
            textView.visibility = View.GONE
        }

        return super.dispatchTouchEvent(event)
    }

    fun hideError(view: View) {
        if (errorTextView.visibility != View.GONE) {
            errorTextView.visibility = View.GONE
            textView.visibility = View.GONE
        }
    }

    private fun initializeVars() {
        passwordFieldText = findViewById(R.id.passwordFieldText)
        passwordField = findViewById(R.id.passwordField)
        loginFieldText = findViewById(R.id.loginFieldText)
        loginField = findViewById(R.id.loginField)
        passwordVisibilityButton = findViewById(R.id.passwordVisibilityButton)
        spinner = findViewById(R.id.progressBar)
        loginButton = findViewById(R.id.loginButton)
        errorTextView = findViewById(R.id.view)
        textView = findViewById(R.id.textView)
    }

    private fun makePasswordVisible() {
        passwordVisibilityButton.setImageResource(R.drawable.normal_eye)
        passwordFieldText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        passwordFieldText.transformationMethod = null
        passwordFieldText.setSelection(passwordFieldText.text.length)
    }

    private fun makePasswordInvisible() {
        passwordVisibilityButton.setImageResource(R.drawable.crossed_out_eye)
        passwordFieldText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        passwordFieldText.transformationMethod = PasswordTransformationMethod.getInstance()
        passwordFieldText.setSelection(passwordFieldText.text.length)
    }
}