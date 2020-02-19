package com.hexaware.MLP174.util;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.hexaware.MLP174.model.Wallet;
import com.hexaware.MLP174.factory.WalletFactory;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/wallet")
public class WalletRest {
  /**
   * Returns wallet details.
   * @return the wallet details
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Wallet[] listWallet() {
    final Wallet[] wallet = WalletFactory.showWallet();
    return wallet;
  }
  /**
   * Return Wallet Details by authenticating.
   * @return the wallet details.
   * @param id name.
   */
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Wallet[] validateWallet(@PathParam("id") final int id) {
    final Wallet[] wallet = WalletFactory.showWallet(id);
    return wallet;
  }
}
