package com.hexaware.MLP174.util;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.hexaware.MLP174.model.Vendor;
import com.hexaware.MLP174.factory.VendorFactory;

/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/vendor")
public class VendorRest {
  /**
   * Returns vendor details.
   * @return the vendor details
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public final Vendor[] listVendor() {
    final Vendor[] vendor = VendorFactory.showVendor();
    return vendor;
  }

 /**
   * Return Vendor Deatils by name.
   * @return the vendor details.
   * @param i name.
   */
  @GET
  @Path("/vendorName/{i}")
  @Produces(MediaType.APPLICATION_JSON)
  public final Vendor listVendorId(@PathParam("i") final String i) {
    final Vendor vendor = VendorFactory.findByVendorName(i);
    if (vendor == null) {
      throw new NotFoundException("no such vendor" + i);
    }
    return vendor;
  }
  /**
   * Return Vendor Details by authenticating.
   * @return the vendor details.
   * @param name name.
   * @param password password.
   */
  @GET
  @Path("/validateVendor/{name}/{password}")
  @Produces(MediaType.APPLICATION_JSON)
  public final int validateVendorId(@PathParam("name") final String name, @PathParam("password") final String password) {
    final int vendor = VendorFactory.validateVendor(name, password);
    return vendor;
  }

}
