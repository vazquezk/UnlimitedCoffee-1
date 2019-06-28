package com.unlimitedcoffee;

import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class RegisterActivity_IsValidMobileTest {
    private String expected;
    private String inputParm;

    public RegisterActivity_IsValidMobileTest(String expectedResult, String inputStr){
        this.expected = expectedResult;
        this.inputParm = inputStr;
    }

    @Parameterized.Parameters
    public static Collection strInputs() {
        return Arrays.asList(new String[][]{
                // valid parameters
                {"true", "13127735221"},        // 00: US # w/ country code, no spaces
                {"true", "1 312 773 5221"},     // 01: US # w/ country code, formatting spaces
                {"true", "1-312-773-5221"},     // 02: US # w/ country code, formatting dashes
                {"true", "+1-312-773-5221"},    // 03: US # w/ country code, formatting dashes, plus sign
                {"true", "1(312) 773-5221"},    // 04: US # w/ country code, formatting dashes and spaces, parens
                {"true", "1 604 555 5555"},     // 05: Canada # w/ country code
                {"true", "52 55 1234 5678"},    // 06: Mexico # w/ country code
                {"true", "1-242-4555-598"},     // 07: Bahamas # w/ country code
                {"true", "36 55 728 179"},      // 08: Hungary # w/ country code
                {"true", "353-855-5529-94"},    // 09: Ireland # w/ country code
                {"true", "+44 20 7946 0901"},   // 10: London # w/ country code
                {"true", "49 89 636 48018"},    // 11: Germany # w/ country code
                {"true", "30 21 1 234 5678"},   // 12: Greece # w/ country code
                {"true", "495 333-44-55"},      // 13: Russia # w/ country code
                {"true", "81-905-5525-701"},    // 14: Japan # w/ country code
                {"true", "66-855-5153-81"},     // 15: Thailand # w/ country code
                {"true", "852 2921 2222"},      // 16: Hong Kong # w/ country code
                {"true", "39 333 1234567"},     // 17: Italy # w/ country code
                {"true", "61 2 9876 5432"},     // 18: Australia # w/ country code
                // invalid parameters
                {"false", ""},                  // 19: blank input
                {"false", "0"},                 // 20: single number (too short)
                {"false", "00000000000"},       // 21: incorrect format - many zeroes
                {"false", "1234567890123456789"},//22: 19 characters (too long)
                {"false", "7733185221"},        // 23: US # w/o country code
                {"false", "AA BBB CCC DDDD"},   // 24: alpha chars
        });
    }

    /**
     * Test of isValidMobile method, Utilities class
     */
    @Test
    public void testIsValidMobile() {
        System.out.println("RegisterActivityJUnit4Test: isValidMobile()");
        RegisterActivity instance = new RegisterActivity();
        boolean expectedBool = Boolean.valueOf(expected);
        System.out.println("Input: " + inputParm);
        boolean result = instance.isValidMobile(inputParm);
        assertEquals(expectedBool, result);
    }
}