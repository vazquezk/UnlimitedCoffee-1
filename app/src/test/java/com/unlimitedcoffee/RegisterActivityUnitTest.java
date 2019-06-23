package com.unlimitedcoffee;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class RegisterActivityUnitTest {

    @Test
    public void testOnBackPressed()  {

    }
    @Test
    public void testOnCreate() {

    }
    @Test
    public void testIsValidMobile_Correct() {
        assertTrue(RegisterActivity.isValidMobile("13125556666");
    }
    @Test
    public void testIsValidMobile_BadNoRegion() {
        assertFalse(RegisterActivity.isValidMobile("3125556666");
    }
    @Test
    public void testIsValidMobile_BadAlphaChar() {
        assertFalse(RegisterActivity.isValidMobile("A3125556666");
    }
    @Test
    public void testIsValidMobile_BadTooShort() {
        assertFalse(RegisterActivity.isValidMobile("1312555666");
    }
    @Test
    public void testIsValidMobile_BadTooLong() {
        assertFalse(RegisterActivity.isValidMobile("1312555666677");
    }

    @Test
    public void testIsValidPassword_Correct() {
        assertTrue(RegisterActivity.isValidPassword("GoodPassword55!"));
    }
    @Test
    public void testIsValidPassword_BadNoNum() {
        assertFalse(RegisterActivity.isValidPassword("BadPassword!"));
    }
    @Test
    public void testIsValidPassword_BadNoSpecChar() {
        assertFalse(RegisterActivity.isValidPassword("BadPassword55"));
    }
    @Test
    public void testIsValidPassword_BadNoCap() {
        assertFalse(RegisterActivity.isValidPassword("badpassword55!"));
    }
    @Test
    public void testIsValidPassword_BadNoLower() {
        assertFalse(RegisterActivity.isValidPassword("BADPASSWORD55!"));
    }
}