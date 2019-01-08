package demo.reactAdmin.crud.controllers;


import demo.reactAdmin.crud.entities.Command;
import demo.reactAdmin.crud.entities.Customer;
import demo.reactAdmin.crud.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactAdmin.rest.entities.FilterWrapper;
import reactAdmin.rest.services.FilterService;

@RestController
@RequestMapping("api/v1")
public class CustomerController {

    @Autowired
    private FilterService<Customer> filterService;

    @Autowired
    private CustomerRepository repo;

    @RequestMapping(value = "customers", method = RequestMethod.POST)
    public Customer create(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    @RequestMapping(value = "customers/{id}", method = RequestMethod.PUT)
    public Customer update(@RequestBody Customer customer, @PathVariable int id) {
        customer.id = id;
        return repo.save(customer);
    }

    @RequestMapping(value = "customers/{id}/published/{value}", method = RequestMethod.POST)
    public Customer publishedUpdate(@PathVariable int id, @PathVariable boolean value) {
        Customer customer = repo.findOne(id);
        customer.published = value;
        return repo.save(customer);
    }


    @RequestMapping(value = "customers/{id}", method = RequestMethod.GET)
    public Customer getById(@PathVariable int id) {
        return repo.findOne(id);
    }

    @RequestMapping(value = "customers", method = RequestMethod.GET)
    public Page<Customer> filterBy(
            @RequestParam(required = false, name = "filter") String filterStr,
            Pageable pageable) {
        return repo.findAll(pageable);
    }
}
