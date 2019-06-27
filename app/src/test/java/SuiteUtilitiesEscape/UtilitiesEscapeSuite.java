package SuiteUtilitiesEscape;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.unlimitedcoffee.Utilities_EscapeTest;
import com.unlimitedcoffee.Utilities_SanitizeTest;
import com.unlimitedcoffee.Utilities_Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Utilities_EscapeTest.class,
        Utilities_SanitizeTest.class,
        Utilities_Test.class
})
public class UtilitiesEscapeSuite {
}
