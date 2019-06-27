package com.unlimitedcoffee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class Utilities_EscapeTest {

    private String expected;
    private String inputParm;

    public Utilities_EscapeTest(String expectedResult, String inputStr){
        this.expected = expectedResult;
        this.inputParm = inputStr;
    }

    @Parameterized.Parameters
    public static Collection strInputs() {
        return Arrays.asList(new String[][]{
                {"", ""}, // blank input
                // test tags
                {"&amp;lt;&amp;gt; &amp;quot;", "<> \""},
                // test breaks
                {"<br/>", "\n"},
                {"", "\r"},
                // test special chars
                {"&amp;", "&"},
                {"&AElig;", "Æ"},
                {"&Aacute;", "Á"},
                {"&Acirc;", "Â"},
                {"&Agrave;", "À"},
                {"&Aring;", "Å"},
                {"&Atilde;", "Ã"},
                {"&Auml;", "Ä"},
                {"&Ccedil;", "Ç"},
                {"&ETH;", "Ð"},
                {"&Eacute;", "É"},
                {"&Ecirc;", "Ê"},
                {"&Egrave;", "È"},
                {"&Euml;", "Ë"},
                {"&Iacute;", "Í"},
                {"&Icirc;", "Î"},
                {"&Igrave;", "Ì"},
                {"&Iuml;", "Ï"},
                {"&Ntilde;", "Ñ"},
                {"&Oacute;", "Ó"},
                {"&Ocirc;", "Ô"},
                {"&Ograve;", "Ò"},
                {"&Oslash;", "Ø"},
                {"&Otilde;", "Õ"},
                {"&Ouml;", "Ö"},
                {"&THORN;", "Þ"},
                {"&Uacute;", "Ú"},
                {"&Ucirc;", "Û"},
                {"&Ugrave;", "Ù"},
                {"&Uuml;", "Ü"},
                {"&Yacute;", "Ý"},
                {"&aacute;", "á"},
                {"&acirc;", "â"},
                {"&aelig;", "æ"},
                {"&agrave;", "à"},
                {"&aring;", "å"},
                {"&atilde;", "ã"},
                {"&auml;", "ä"},
                {"&ccedil;", "ç"},
                {"&eacute;", "é"},
                {"&ecirc;", "ê"},
                {"&egrave;", "è"},
                {"&eth;", "ð"},
                {"&euml;", "ë"},
                {"&iacute;", "í"},
                {"&icirc;", "î"},
                {"&igrave;", "ì"},
                {"&iuml;", "ï"},
                {"&ntilde;", "ñ"},
                {"&oacute;", "ó"},
                {"&ocirc;", "ô"},
                {"&ograve;", "ò"},
                {"&oslash;", "ø"},
                {"&otilde;", "õ"},
                {"&ouml;", "ö"},
                {"&szlig;", "ß"},
                {"&thorn;", "þ"},
                {"&uacute;", "ú"},
                {"&ucirc;", "û"},
                {"&ugrave;", "ù"},
                {"&uuml;", "ü"},
                {"&yacute;", "ý"},
                {"&yuml;", "ÿ"},
                {"&cent;", "¢"},});
    }
    /**
     * Test of sanitize method, of Utilities class
     */
    @Test
    public void testSanitize() {
        System.out.println("UtilitiesJUnit4Test: escape()");
        Utilities instance = new Utilities();
        System.out.println("Input: " + inputParm);
        String result = instance.escape(inputParm);
        System.out.println("Result: " + result);
        assertEquals(expected, result);
    }
}