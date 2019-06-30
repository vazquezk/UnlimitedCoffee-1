package SuiteRegisterActivities;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.unlimitedcoffee.RegisterActivity_IsValidMobileTest;
import com.unlimitedcoffee.RegisterActivity_IsValidPasswordTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RegisterActivity_IsValidMobileTest.class,
        RegisterActivity_IsValidPasswordTest.class,
})
public class RegisterActivitySuite {
}
