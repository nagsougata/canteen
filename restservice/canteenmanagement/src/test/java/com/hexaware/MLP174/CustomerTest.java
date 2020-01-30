package com.hexaware.MLP174.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


 import com.hexaware.MLP174.persistence.CustomerDAO;
 import com.hexaware.MLP174.factory.CustomerFactory;
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
 * Test class for Customer.
 */
@RunWith(JMockit.class)
public class CustomerTest {
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
  public final void testCustomer() {
    Customer m = new Customer();
    assertNotNull(m);
    Customer customer = new Customer(1, "Leela", "LEELA@GMAIL.COM", "9999988888", "CHENNAI", "pink1", "leela1");
    assertEquals(1, customer.getCustomerId());
    assertEquals("Leela", customer.getCustomerName());
    assertEquals("LEELA@GMAIL.COM", customer.getCustomerEmail());
    assertEquals("9999988888", customer.getCustomerNumber());
    assertEquals("CHENNAI", customer.getCustomerAddress());
    assertEquals("pink1", customer.getCustomerPassword());
    assertEquals("leela1", customer.getCustomerUsername());


    m.setCustomerId(1);
    m.setCustomerName("Leela");
    m.setCustomerEmail("LEELA@GMAIL.COM");
    m.setCustomerNumber("9999988888");
    m.setCustomerAddress("CHENNAI");
    m.setCustomerPassword("pink1");
    m.setCustomerUsername("leela1");

    assertEquals(1, m.getCustomerId());
    assertEquals("Leela", m.getCustomerName());
    assertEquals("LEELA@GMAIL.COM", m.getCustomerEmail());
    assertEquals("9999988888", m.getCustomerNumber());
    assertEquals("CHENNAI", m.getCustomerAddress());
    assertEquals("pink1", m.getCustomerPassword());
    assertEquals("leela1", m.getCustomerUsername());
  }
  /**
   * Tests the toString() methods of the customer class.
   * @throws ParseException for date format validation.
   */
  @Test
  public final void testToString() throws ParseException {
    Customer m = new Customer(1, "Leela", "LEELA@GMAIL.COM", "9999988888", "CHENNAI", "pink1", "leela1");
    String result = String.format("%15s %15s %15s %15s %15s %15s %15s",
          m.getCustomerId(), m.getCustomerName(), m.getCustomerEmail(), m.getCustomerNumber(),
          m.getCustomerAddress(), m.getCustomerPassword(), m.getCustomerUsername());
    assertEquals(result, m.toString());
  }
  /**
   * Tests for equals.
   */
  @Test
  public final void testEquals() {
    Customer m = new Customer();
    Customer m1 = null;
    Customer m2 = new Customer(1, "Leela", "LEELA@GMAIL.COM", "9999988888", "CHENNAI", "pink1", "leela1");
    Customer m3 = new Customer(1, "Leela", "LEELA@GMAIL.COM", "9999988888", "CHENNAI", "pink1", "leela1");
    assertFalse(m2.equals(m1));
    assertTrue(m2.equals(m3));
    assertEquals(m2.hashCode(), m3.hashCode());
    Vendor vendor = new Vendor();
    assertFalse(m2.equals(vendor));
    assertFalse(m.equals(m2));
  }
  /**
   * tests that empty employee list is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
   public final void testListAllEmpty(@Mocked final CustomerDAO dao) {
    new Expectations() {
      {
        dao.show(); result = new ArrayList<Customer>();
      }
    };
    new MockUp<CustomerFactory>() {
      @Mock
       CustomerDAO dao() {
        return dao;
      }
    };
    Customer[] me = CustomerFactory.showCustomer();
    assertEquals(0, me.length);
  }
  /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
   public final void testListAllSome(@Mocked final CustomerDAO dao) {
    final Customer m2 = new Customer(1, "Leela", "LEELA@GMAIL.COM", "9999988888", "CHENNAI", "pink1", "leela1");
    final Customer m3 = new Customer(2, "Sivanie", "SIVANIE@GMAIL.COM", "9999988877", "COIMBATORE", "blue2", "sivanie2");
    final ArrayList<Customer> mn = new ArrayList<Customer>();
    new Expectations() {
      {
        mn.add(m2);
        mn.add(m3);
        dao.show(); result = mn;
      }
    };
    new MockUp<CustomerFactory>() {
      @Mock
       CustomerDAO dao() {
        return dao;
      }
    };
    Customer[] mn1 = CustomerFactory.showCustomer();
    assertEquals(2, mn1.length);
    assertEquals(1, mn1[0].getCustomerId());
    assertEquals(2, mn1[1].getCustomerId());
    assertEquals("Leela", mn1[0].getCustomerName());
    assertEquals("Sivanie", mn1[1].getCustomerName());
    assertEquals("LEELA@GMAIL.COM", mn1[0].getCustomerEmail());
    assertEquals("SIVANIE@GMAIL.COM", mn1[1].getCustomerEmail());
    assertEquals("9999988888", mn1[0].getCustomerNumber());
    assertEquals("9999988877", mn1[1].getCustomerNumber());
    assertEquals("CHENNAI", mn1[0].getCustomerAddress());
    assertEquals("COIMBATORE", mn1[1].getCustomerAddress());
    assertEquals("pink1", mn1[0].getCustomerPassword());
    assertEquals("blue2", mn1[1].getCustomerPassword());
    assertEquals("leela1", mn1[0].getCustomerUsername());
    assertEquals("sivanie2", mn1[1].getCustomerUsername());
  }
}

