/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.BasicSample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.android.testing.BasicSample.R;

/**
 * An {@link Activity} that represents an input form page where the user can provide his name, date
 * of birth and email address. The personal information can be saved to {@link SharedPreferences}
 * by clicking a button.
 */
public class MainActivity extends Activity {

    // Logger for this class.
    private static final String TAG = "MainActivity";

    // The input field where the user enters his email.
    private EditText mEmailText;

    // The input field where the user enters his password.
    private EditText mPasswordText;

    // The validator for the email input field.
    private EmailValidator mEmailValidator;

    // The validator for the password input field.
    private PasswordValidator mPasswordValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Shortcuts to input fields.
        mPasswordText = (EditText) findViewById(R.id.userPasswordInput);
        mEmailText = (EditText) findViewById(R.id.emailInput);

        // Setup field validators.
        mEmailValidator = new EmailValidator();
        mEmailText.addTextChangedListener(mEmailValidator);
        mPasswordValidator = new PasswordValidator();
        mPasswordText.addTextChangedListener(mPasswordValidator);
    }

    /**
     * Called when the "Save" button is clicked.
     */
    public void onLoginClick(View view) {
        // Close the soft keyboard.
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        // Don't save if the fields do not validate.
        if (!mEmailValidator.isValid()) {
            mEmailText.setError(getResources().getString(R.string.email_error));
            Log.w(TAG, "Not going to login: Invalid email");
            return;
        }

        // Don't save if the fields do not validate.
        if (!mPasswordValidator.isValid()) {
            mPasswordText.setError(getResources().getString(R.string.password_error));
            Log.w(TAG, "Not going to login: Invalid password");
            mPasswordText.requestFocus();
            return;
        }

        // Get the text from the input fields.
        String password = mPasswordText.getText().toString();
        String email = mEmailText.getText().toString();

        Intent myIntent = new Intent(this, Welcome.class);
        startActivity(myIntent);
    }
}
