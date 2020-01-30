package com.hexaware.MLP174.factory;

import com.hexaware.MLP174.persistence.VendorDAO;
import com.hexaware.MLP174.model.Vendor;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;

//import com.hexaware.MLP174.model.Vendor;
//import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
//import java.util.Date;
//import java.text.SimpleDateFormat;
//import java.text.ParseException;
import org.junit.Test;
//import org.junit.Before;
import org.junit.runner.RunWith;
import java.util.ArrayList;

import mockit.Expectations;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Mock;
import mockit.integration.junit4.JMockit;
//import java.util.ArrayList;
/**
 * Test class for Vendor.
 */
@RunWith(JMockit.class)
public class VendorFactoryTest {
    /**
   * tests for constructor.
   */
  @Test
  public final void testConstructor() {
    assertNotNull(new VendorFactory());
    assertNotNull(VendorFactory.dao());
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
    Vendor[] v = VendorFactory.showVendor();
    assertEquals(0, v.length);
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */

  @Test
  public final void testListAllSome(@Mocked final VendorDAO dao) {
    final Vendor v2 = new Vendor(101, "ABC", "abc", "9959595959", "ABC@GMAIL.COM", "123");
    final Vendor v3 = new Vendor(102, "DEF", "def", "8686868868", "DEF@GMAIL.COM", "456");
    final ArrayList<Vendor> ven = new ArrayList<Vendor>();
    new Expectations() {
      {
        ven.add(v2);
        ven.add(v3);
        dao.show(); result = ven;
      }
    };
    new MockUp<VendorFactory>() {
      @Mock
      VendorDAO dao() {
        return dao;
      }
    };
    Vendor[] ven1 = VendorFactory.showVendor();
    assertEquals(2, ven1.length);
    assertEquals(101, ven1[0].getVendorId());
    assertEquals(102, ven1[1].getVendorId());
    assertEquals("ABC", ven1[0].getVendorName());
    assertEquals("DEF", ven1[1].getVendorName());
    assertEquals("abc", ven1[0].getVendorUsername());
    assertEquals("def", ven1[1].getVendorUsername());
    assertEquals("9959595959", ven1[0].getVendorNumber());
    assertEquals("8686868868", ven1[1].getVendorNumber());
    assertEquals("ABC@GMAIL.COM", ven1[0].getVendorEmail());
    assertEquals("DEF@GMAIL.COM", ven1[1].getVendorEmail());
    assertEquals("123", ven1[0].getVendorPassword());
    assertEquals("456", ven1[1].getVendorPassword());
  }
   /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testFindByVendorId(@Mocked final VendorDAO dao) {
    final Vendor v2 = new Vendor(101, "ABC", "abc", "9959595959", "ABC@GMAIL.COM", "123");
    final Vendor v3 = new Vendor(102, "DEF", "def", "8686868868", "DEF@GMAIL.COM", "456");
    new Expectations() {
      {
        dao.findByVendorName("ABC"); result = v2;
        dao.findByVendorName("DEF"); result = v3;
        dao.findByVendorName("LMN"); result = null;
      }
    };
    new MockUp<VendorFactory>() {
      @Mock
      VendorDAO dao() {
        return dao;
      }
    };
    Vendor vendor1 = VendorFactory.findByVendorName("ABC");
    assertNotNull(vendor1);
    Vendor vendor2 = VendorFactory.findByVendorName("DEF");
    assertNotNull(vendor2);
    Vendor vendor3 = VendorFactory.findByVendorName("LMN");
    assertNull(vendor3);
  }
  /**
   * Tests that a list Employ Search by Vendor id.
   * @param dao mocking the dao class.
   */
  @Test
  public final void testListByVendorId(@Mocked final VendorDAO dao) {
    final Vendor v2 = new Vendor(101, "ABC", "abc", "9959595959", "ABC@GMAIL.COM", "123");
    final Vendor v3 = new Vendor(102, "DEF", "def", "8686868868", "DEF@GMAIL.COM", "456");
    new Expectations() {
      {
        dao.findByVendorName("ABC"); result = v2;
        dao.findByVendorName("DEF"); result = v3;
      }
    };
    new MockUp<VendorFactory>() {
      @Mock
      VendorDAO dao() {
        return dao;
      }
    };
    Vendor ven1 = VendorFactory.findByVendorName("ABC");
    Vendor ven2 = VendorFactory.findByVendorName("DEF");
    assertEquals(101, ven1.getVendorId());
    assertEquals(102, ven2.getVendorId());
  }
  /**
   * Tests that a list Employ Search by Vendor id.
   * @param dao mocking the dao class.
   */
  @Test
  public final void testValidateVendor(@Mocked final VendorDAO dao) {
    new Expectations() {
      {
        dao.authenticate("ABC", "123"); result = 1;
        dao.authenticate("DEF", "456"); result = 1;
        dao.authenticate("LMN", "789"); result = 0;
      }
    };
    new MockUp<VendorFactory>() {
      @Mock
      VendorDAO dao() {
        return dao;
      }
    };
    assertEquals(1, VendorFactory.validateVendor("ABC", "123"));
    assertEquals(1, VendorFactory.validateVendor("DEF", "456"));
    assertEquals(0, VendorFactory.validateVendor("LMN", "789"));
  }
}
