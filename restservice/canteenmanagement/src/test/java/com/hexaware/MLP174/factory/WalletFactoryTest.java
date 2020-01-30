package com.hexaware.MLP174.factory;
import com.hexaware.MLP174.persistence.WalletDAO;
import com.hexaware.MLP174.model.Wallet;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
import com.hexaware.MLP174.model.WalletType;

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
 * Test class for Menu.
 */
@RunWith(JMockit.class)
public class WalletFactoryTest {
    /**
   * tests for constructor.
   */
  @Test
  public final void testConstructor() {
    assertNotNull(new WalletFactory());
    assertNotNull(WalletFactory.dao());
  }
     /**
   * Tests that a list with MenuHandled is handled correctly.
   * @param dao mocking the dao class
   */
  @Test
  public final void testListAllEmpty(@Mocked final WalletDAO dao) {
    new Expectations() {
      {
        dao.show(); result = new ArrayList<Wallet>();
      }
    };
    new MockUp<WalletFactory>() {
      @Mock
       WalletDAO dao() {
        return dao;
      }
    };
    Wallet[] w = WalletFactory.showWallet();
    assertEquals(0, w.length);
  }

/**
 * tests that empty employee list is handled correctly.
 * @param dao mocking the dao class
 */
  @Test
  public final void testwalletId(@Mocked final WalletDAO dao) {
    final Wallet w1 = new Wallet(1, WalletType.CREDIT_CARD, 1500.45, 1);
    final Wallet w2 = new Wallet(1, WalletType.DEBIT_CARD, 1500.45, 1);
    final Wallet w3 = new Wallet(2, WalletType.CREDIT_CARD, 1500.45, 1);
    final Wallet w4 = new Wallet(2, WalletType.DEBIT_CARD, 2500.45, 2);
    final ArrayList<Wallet> list1 = new ArrayList<Wallet>();
    list1.add(w1);
    list1.add(w2);
    final ArrayList<Wallet> list2 = new ArrayList<Wallet>();
    list2.add(w3);
    list2.add(w4);
    new Expectations() {
      {
        dao.showByWalletId(1); result = list1;
        dao.showByWalletId(2); result = list2;
      }
    };
    new MockUp<WalletFactory>() {
      @Mock
      WalletDAO dao() {
        return dao;
      }
    };
    Wallet[] e1 = WalletFactory.showWallet(1);
    Wallet[] e2 = WalletFactory.showWallet(2);
    assertEquals(2, e1.length);
    assertEquals(2, e2.length);
  }
}


