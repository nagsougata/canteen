package com.hexaware.MLP174.util;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.hexaware.MLP174.model.Customer;
import javax.ws.rs.NotFoundException;
import com.hexaware.MLP174.factory.CustomerFactory;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/customer")
public class CustomerRest {
    /**
    * Return Customer details.
    * @return the customer details.
    */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Customer[] listCustomer() {
    final Customer[] customer = CustomerFactory.showCustomer();
    return customer;
  }
      /**
    * Return Customer details.
    * @return the customer details.
    * @param i for customer.
    */
  @GET
  @Path("/customerName/{i}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Customer listCustomerName(@PathParam("i") final String i) {
    final Customer customer = CustomerFactory.findByCustomerName(i);
    if (customer == null) {
      throw new NotFoundException("no such customer" + i);
    }
    return customer;
  }
  /**
  * Return customer details by authenticating.
  * @return the vendor name.
  * @param name name.
  * @param password password.
  */
  @GET
  @Path("/validateCustomer/{name}/{password}")
  @Produces(MediaType.APPLICATION_JSON)
  public final int validateCustomerId(@PathParam("name") final String name, @PathParam("password") final String password) {
    final int customer = CustomerFactory.validateCustomer(name, password);
    return customer;
  }
}
