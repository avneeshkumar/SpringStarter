package ai.edbox.SpringStarter.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import ai.edbox.SpringStarter.models.Customer;
import ai.edbox.coreutils.utils.LogUtils;

@Repository
public class CustomerDAO extends NamedParameterJdbcDaoSupport {
	// Dummy database. Initialize with some dummy values.
	
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	private static List<Customer> customers;
	{
		customers = new ArrayList<Customer>();
		customers.add(new Customer(101L, "John", "Doe", "djohn@gmail.com", "121-232-3435"));
		customers.add(new Customer(201L, "Russ", "Smith", "sruss@gmail.com", "343-545-2345"));
		customers.add(new Customer(301L, "Kate", "Williams", "kwilliams@gmail.com", "876-237-2987"));
	}
	
	/**
	 * Returns list of customers from dummy database.
	 * 
	 * @return list of customers
	 */
	public List<Customer> list() {
		
		return getJdbcTemplate().query(CustomerDBQueries.getAllUsers(),new BeanPropertyRowMapper<Customer>(Customer.class));
		
	}
	
	/**
	 * Return customer object for given id from dummy database. If customer is
	 * not found for id, returns null.
	 * 
	 * @param id
	 *            customer id
	 * @return customer object for given id
	 */
	public Customer get(Long id) {
		
		Customer bean = null;
		try {
			bean = getJdbcTemplate().queryForObject(CustomerDBQueries.getUserById(), new Object[] { id }, new BeanPropertyRowMapper<Customer>(Customer.class));
		} catch (Exception e) {
			LogUtils.error("Error in fetchProfile ", e);
			//throw new EdboxException(UserErrorCode.DATABASE_ERROR, UserErrorMsg.DATABASE_ERROR);
		}
		return bean;
		
	}
	
	/**
	 * Create new customer in dummy database. Updates the id and insert new
	 * customer in list.
	 * 
	 * @param customer
	 *            Customer object
	 * @return customer object with updated id
	 */
	public Customer create(Customer customer) {
		customer.setId(System.currentTimeMillis());
		customers.add(customer);
		return customer;
	}
	
	/**
	 * Delete the customer object from dummy database. If customer not found for
	 * given id, returns null.
	 * 
	 * @param id
	 *            the customer id
	 * @return id of deleted customer object
	 */
	public Long delete(Long id) {

		for (Customer c : customers) {
			if (c.getId().equals(id)) {
				customers.remove(c);
				return id;
			}
		}

		return null;
	}
	
	/**
	 * Update the customer object for given id in dummy database. If customer
	 * not exists, returns null
	 * 
	 * @param id
	 * @param customer
	 * @return customer object with id
	 */
	public Customer update(Long id, Customer customer) {

		for (Customer c : customers) {
			if (c.getId().equals(id)) {
				customer.setId(c.getId());
				customers.remove(c);
				customers.add(customer);
				return customer;
			}
		}

		return null;
	}

}
