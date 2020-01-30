package com.hexaware.MLP174.factory;

import com.hexaware.MLP174.persistence.CustomerDAO;
import com.hexaware.MLP174.model.Customer;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
//import java.text.ParseException;
import org.junit.Test;
//import org.junit.Before;
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
public class CustomerFactoryTest {
     /**
   * tests for constructor.
   */
  @Test
  public final void testConstructor() {
    assertNotNull(new CustomerFactory());
    assertNotNull(CustomerFactory.dao());
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
    Customer[] c = CustomerFactory.showCustomer();
    assertEquals(0, c.length);
  }
 /**
  * Tests that a list with some employees is handled correctly.
  * @param dao mocking the dao class
  */

  @Test
  public final void testListAllSome(@Mocked final CustomerDAO dao) {
    final Customer c2 = new Customer(1, "ABC", "ABC@GMAIL.COM",  "9999988888", "Salem", "ab1", "abc");
    final Customer c3 = new Customer(3, "YFD", "YFD@GMAIL.COM",  "8888899999", "Coimbatore", "yf2", "yfd");
    final ArrayList<Customer> cus = new ArrayList<Customer>();
    new Expectations() {
      {
        cus.add(c2);
        cus.add(c3);
        dao.show(); result = cus;
      }
    };
    new MockUp<CustomerFactory>() {
      @Mock
      CustomerDAO dao() {
        return dao;
      }
    };
    Customer[] cus1 = CustomerFactory.showCustomer();
    assertEquals(2, cus1.length);
    assertEquals(1, cus1[0].getCustomerId());
    assertEquals(3, cus1[1].getCustomerId());
    assertEquals("ABC", cus1[0].getCustomerName());
    assertEquals("YFD", cus1[1].getCustomerName());
    assertEquals("ABC@GMAIL.COM", cus1[0].getCustomerEmail());
    assertEquals("YFD@GMAIL.COM", cus1[1].getCustomerEmail());
    assertEquals("9999988888", cus1[0].getCustomerNumber());
    assertEquals("8888899999", cus1[1].getCustomerNumber());
    assertEquals("Salem", cus1[0].getCustomerAddress());
    assertEquals("Coimbatore", cus1[1].getCustomerAddress());
    assertEquals("ab1", cus1[0].getCustomerPassword());
    assertEquals("yf2", cus1[1].getCustomerPassword());
    assertEquals("abc", cus1[0].getCustomerUsername());
    assertEquals("yfd", cus1[1].getCustomerUsername());
  }
   /**
   * Tests that a list with some employees is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testFindByCustomerId(@Mocked final CustomerDAO dao) {
    final Customer c2 = new Customer(1, "ABC", "ABC@GMAIL.COM",  "9999988888", "Salem", "ab1", "abc");
    final Customer c3 = new Customer(3, "YFD", "YFD@GMAIL.COM",  "8888899999", "Coimbatore", "yf2", "yfd");
    new Expectations() {
      {
        dao.findByCustomerName("ABC"); result = c2;
        dao.findByCustomerName("YFD"); result = c3;
        dao.findByCustomerName("LMN"); result = null;
      }
    };
    new MockUp<CustomerFactory>() {
      @Mock
      CustomerDAO dao() {
        return dao;
      }
    };
    Customer customer1 = CustomerFactory.findByCustomerName("ABC");
    assertNotNull(customer1);
    Customer customer2 = CustomerFactory.findByCustomerName("YFD");
    assertNotNull(customer2);
    Customer customer3 = CustomerFactory.findByCustomerName("LMN");
    assertNull(customer3);
  }
  /**
   * Tests that a list Employ Search by customer id.
   * @param dao mocking the dao class.
   */
  @Test
  public final void testListByCustomerId(@Mocked final CustomerDAO dao) {
    final Customer c2 = new Customer(1, "ABC", "ABC@GMAIL.COM",  "9999988888", "Salem", "ab1", "abc");
    final Customer c3 = new Customer(3, "YFD", "YFD@GMAIL.COM",  "8888899999", "Coimbatore", "yf2", "yfd");
    new Expectations() {
      {
        dao.findByCustomerName("ABC"); result = c2;
        dao.findByCustomerName("YFD"); result = c3;
      }
    };
    new MockUp<CustomerFactory>() {
      @Mock
      CustomerDAO dao() {
        return dao;
      }
    };
    Customer cus1 = CustomerFactory.findByCustomerName("ABC");
    Customer cus2 = CustomerFactory.findByCustomerName("YFD");
    assertEquals(1, cus1.getCustomerId());
    assertEquals(3, cus2.getCustomerId());
  }
  /**
   * Tests that a list Employ Search by customer id.
   * @param dao mocking the dao class.
   */
  @Test
  public final void testValidateCustomer(@Mocked final CustomerDAO dao) {
    new Expectations() {
      {
        dao.authenticate("ABC", "ab1"); result = 1;
        dao.authenticate("YFD", "yf2"); result = 1;
        dao.authenticate("LMN", "lm3"); result = 0;
      }
    };
    new MockUp<CustomerFactory>() {
      @Mock
      CustomerDAO dao() {
        return dao;
      }
    };
    assertEquals(1, CustomerFactory.validateCustomer("ABC", "ab1"));
    assertEquals(1, CustomerFactory.validateCustomer("YFD", "yf2"));
    assertEquals(0, CustomerFactory.validateCustomer("LMN", "lm3"));
  }
}
