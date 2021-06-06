package gov.taxation.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropTest {

    @Test
    public void success() {
        String result = Prop.getDBProperty("invalid.fields");
        assertEquals(Prop.getDBProperty("invalid.fields"), result);

        String result2 = Prop.getProperty("connection.pass");
        assertEquals(Prop.getProperty("connection.pass"), result2);
    }

    @Test
    public void error() {
        String result = Prop.getDBProperty("invalid.fields.test");
        assertNull(result);
    }

}
