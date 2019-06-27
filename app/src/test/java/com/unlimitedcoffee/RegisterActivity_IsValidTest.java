package com.unlimitedcoffee;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class RegisterActivity_IsValidTest {
    private String expected;
    private String inputParm;

    public RegisterActivity_IsValidTest(String expectedResult, String inputStr){
        this.expected = expectedResult;
        this.inputParm = inputStr;
    }

    @Parameterized.Parameters
    public static Collection strInputs() {
        return Arrays.asList(new String[][]{
                {"false", ""}, // blank input
                // test special chars
                {"true", "1aA@"}, // baseline valid input - 1 number, 1 lower, 1 upper, 1 special @ [@#$%^&+=!]
                {"true", "1aA#"}, // baseline valid input - 1 number, 1 lower, 1 upper, 1 special #
                {"true", "1aA$"}, // baseline valid input - 1 number, 1 lower, 1 upper, 1 special $
                {"true", "1aA%"}, // baseline valid input - 1 number, 1 lower, 1 upper, 1 special %
                {"true", "1aA^"}, // baseline valid input - 1 number, 1 lower, 1 upper, 1 special ^
                {"true", "1aA&"}, // baseline valid input - 1 number, 1 lower, 1 upper, 1 special &
                {"true", "1aA+"}, // baseline valid input - 1 number, 1 lower, 1 upper, 1 special +
                {"true", "1aA="}, // baseline valid input - 1 number, 1 lower, 1 upper, 1 special =
                {"true", "1aA!"}, // baseline valid input - 1 number, 1 lower, 1 upper, 1 special !
                // test pattern omissions
                {"false", "xaA@"}, // no number
                {"false", "1XA@"}, // no lower
                {"false", "1ax@"}, // no upper
                {"false", "1aAx"}, // no special
                // other bad
                {"false", "1Bad Password!"}, // pword w/ space
                // good variations
                {"true", "1GoodPassword!"}, // basic good
                {"true", "1GoodPassword!isneverenoughreallythereareneverenoughpasswordstogoaround"}, // long good
        });
    }
    /**
     * Test of isValidPassword method, of Utilities class
     */
    @Test
    public void testIsValidPassword() {
        System.out.println("RegisterActivityJUnit4Test: isValidPassword()");
        RegisterActivity instance = new RegisterActivity();
        boolean expectedBool = Boolean.valueOf(expected);
        System.out.println("Input: " + inputParm);
        boolean result = instance.isValidPassword(inputParm);
        System.out.println("Result: " + result);
        assertEquals(expectedBool, instance.isValidPassword(inputParm));
    }
}