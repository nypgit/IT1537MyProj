package staywell.entities.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import staywell.entities.Customer;
// this is a change
public class CustomerDaoTest {

	@Test
	public void testRegister() {
		CustomerDao customerDao = new CustomerDao();
		Customer customer  = new Customer();
		customer.setCustomerName("Adrian");
		customer.setCustomerTel("12312312");
		
		Customer customer2 = customerDao.register(customer);
		assertTrue(customer2.getCustomerId() != null);
		
		
		Customer customer3 = customerDao.searchByName("Adrian");
		assertTrue(customer3.getCustomerName().equals("Adrian"));
		assertTrue(customer3.getCustomerTel().equals("12312312"));
		
		
		
	}

}
