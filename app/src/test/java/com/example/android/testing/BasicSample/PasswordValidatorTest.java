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

import com.example.android.testing.BasicSample.PasswordValidator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Unit tests for the PasswordValidator logic.
 */
public class PasswordValidatorTest {


    @Test
    public void passwordValidator_CorrectPasswordSimple_ReturnsTrue() {
        assertTrue(PasswordValidator.isValidPassword("longerThan8Symbols"));
    }

    @Test
    public void passwordValidator_CorrectPasswordSubDomain_ReturnsTrue() {
        assertTrue(PasswordValidator.isValidPassword("AlsoLongerThan8"));
    }

    @Test
    public void passwordValidator_InvalidPasswordNoTld_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("short"));
    }

    @Test
    public void passwordValidator_InvalidPasswordDoubleDot_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("shrt"));
    }

    @Test
    public void passwordValidator_InvalidPasswordNoUsername_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("st"));
    }

    @Test
    public void passwordValidator_ShortString_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword("s"));
    }

    @Test
    public void passwordValidator_EmptyPassword_ReturnsFalse() {
        assertFalse(PasswordValidator.isValidPassword(""));
    }
}
