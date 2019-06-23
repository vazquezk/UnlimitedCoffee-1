package com.unlimitedcoffee;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class DatabaseHelperUnitTest {

    @Test
    public void testDatabaseHelper()  {

    }
    @Test
    public void testOnCreate() {

    }
    @Test
    public void testOnUpgrade() {

    }

    @Test
    public void testAddUser() {

    }
    @Test
    public void testDeleteUser() {

    }
    @Test
    public void testCheckUser() {

    }

    @Test
    public void testCheckPhone_CorrectPhone() {
        assertTrue(DatabaseHelper.checkPhone("13125555555"));
    }


}