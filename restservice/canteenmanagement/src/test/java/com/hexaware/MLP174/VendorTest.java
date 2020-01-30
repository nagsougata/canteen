package com.hexaware.MLP174.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


 import com.hexaware.MLP174.persistence.VendorDAO;
 import com.hexaware.MLP174.factory.VendorFactory;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;

import java.text.ParseException;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

 import mockit.Expectations;
 import mockit.MockUp;
 import mockit.Mocked;
 import mockit.Mock;
 import mockit.integration.junit4.JMockit;
 import java.util.ArrayList;

/**
 * Test class for Vendor.
 */
@RunWith(JMockit.class)
public class VendorTest {
    /**
   * setup method.
   */
  @Before
  public void initInput() {

  }
  /**
   * Tests the equals/hashcode methods of the employee class.
   */
  @Test
  public final void testVendor() {
    Vendor m = new Vendor();
    assertNotNull(m);
    Vendor vendor = new Vendor(2, "Yogesh", "yogesh2", "9876213217", "YOGESH@GMAIL.COM", "1234A");
    assertEquals(2, vendor.getVendorId());
    assertEquals("Yogesh", vendor.getVendorName());
    assertEquals("yogesh2", vendor.getVendorUsername());
    assertEquals("9876213217", vendor.getVendorNumber());
    assertEquals("YOGESH@GMAIL.COM", vendor.getVendorEmail());
    assertEquals("1234A", vendor.getVendorPassword());

    m.setVendorId(2);
    m.setVendorName("Yogesh");
    m.setVendorUsername("yogesh2");
    m.setVendorNumber("9876213217");
    m.setVendorEmail("YOGESH@GMAIL.COM");
    m.setVendorPassword("1234A");

    assertEquals(2, m.getVendorId());
    assertEquals("Yogesh", m.getVendorName());
    assertEquals("yogesh2", m.getVendorUsername());
    assertEquals("9876213217", m.getVendorNumber());
    assertEquals("YOGESH@GMAIL.COM", m.getVendorEmail());
    assertEquals("1234A", m.getVendorPassword());
  }
  /**
   * Tests for equals.
   */
  @Test
  public final void testEquals() {
    Vendor m = new Vendor();
    Vendor m1 = null;
    Vendor m2 = new Vendor(2, "Yogesh", "yogesh2", "9876213217", "YOGESH@GMAIL.COM", "1234A");
    Vendor m3 = new Vendor(2, "Yogesh", "yogesh2", "9876213217", "YOGESH@GMAIL.COM", "1234A");
    assertFalse(m2.equals(m1));
    assertTrue(m2.equals(m3));
    assertEquals(m2.hashCode(), m3.hashCode());
    Vendor vendor = new Vendor();
    assertFalse(m2.equals(vendor));
    assertFalse(m.equals(m2));
  }
  /**
   * Tests the toString() methods of the Vendor class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testToString() throws ParseException {
    Vendor m = new Vendor(2, "Yogesh", "yogesh2", "9876213217", "YOGESH@GMAIL.COM", "1234A");
    String result = String.format("%15s %15s %15s %15s %15s %15s",
          m.getVendorId(), m.getVendorName(), m.getVendorUsername(), m.getVendorNumber(),
          m.getVendorEmail(), m.getVendorPassword());
    assertEquals(result, m.toString());
  }
  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
   public final void testListAllEmpty(@Mocked final VendorDAO dao) {
    new Expectations() {
      {
        dao.show(); result = new ArrayList<Vendor>();
      }
    };
    new MockUp<VendorFactory>() {
      @Mock
       VendorDAO dao() {
        return dao;
      }
    };
    Vendor[] me = VendorFactory.showVendor();
    assertEquals(0, me.length);
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
   public final void testListAllSome(@Mocked final VendorDAO dao) {
    final Vendor m2 = new Vendor(2, "Yogesh", "yogesh2", "9876213217", "YOGESH@GMAIL.COM", "1234A");
    final Vendor m3 = new Vendor(3, "Shyam", "shyam3", "9876213219", "SHYAM@GMAIL.COM", "12345A");
    final ArrayList<Vendor> mn = new ArrayList<Vendor>();
    new Expectations() {
      {
        mn.add(m2);
        mn.add(m3);
        dao.show(); result = mn;
      }
    };
    new MockUp<VendorFactory>() {
      @Mock
       VendorDAO dao() {
        return dao;
      }
    };
    Vendor[] mn1 = VendorFactory.showVendor();
    assertEquals(2, mn1.length);
    assertEquals(2, mn1[0].getVendorId());
    assertEquals(3, mn1[1].getVendorId());
    assertEquals("Yogesh", mn1[0].getVendorName());
    assertEquals("Shyam", mn1[1].getVendorName());
    assertEquals("yogesh2", mn1[0].getVendorUsername());
    assertEquals("shyam3", mn1[1].getVendorUsername());
    assertEquals("9876213217", mn1[0].getVendorNumber());
    assertEquals("9876213219", mn1[1].getVendorNumber());
    assertEquals("YOGESH@GMAIL.COM", mn1[0].getVendorEmail());
    assertEquals("SHYAM@GMAIL.COM", mn1[1].getVendorEmail());
    assertEquals("1234A", mn1[0].getVendorPassword());
    assertEquals("12345A", mn1[1].getVendorPassword());
  }
}
