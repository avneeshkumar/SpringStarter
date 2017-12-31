package ai.edbox.SpringStarter.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ai.edbox.SpringStarter.dao.CustomerDAO;
import ai.edbox.SpringStarter.models.Customer;

@RestController
public class CustomerRestController {
	@Autowired
	CustomerDAO customerDAO;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerDAO.list();
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		Customer customer = customerDAO.get(id);
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}
}
