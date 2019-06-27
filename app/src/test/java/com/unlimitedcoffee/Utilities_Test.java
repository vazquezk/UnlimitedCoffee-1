package com.unlimitedcoffee;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.unlimitedcoffee.Utilities.escapeTags;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;


public class Utilities_Test {
    /**
     * Test of escapeTags method, of class HtmlEscape.
     */
    @Test
    public void testEscapeTags() {
        System.out.println("HtmlEscapeJUnit4Test: escapeTags()");
        String original = "<> \"";
        String expResult = "&lt;&gt; &quot;";
        String result = Utilities.escapeTags(original);
        assertEquals(expResult, result);
    }

    /**
     * Test of escapeBr method, of class HtmlEscape.
     */
    @Test
    public void testEscapeBr() {
        System.out.println("HtmlEscapeJUnit4Test: escapeBr()");
        String original = "\n \r";
        String expResult = "<br/> ";
        String result = Utilities.escapeBr(original);
        assertEquals(expResult, result);
    }

}