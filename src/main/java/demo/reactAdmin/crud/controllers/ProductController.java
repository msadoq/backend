package demo.reactAdmin.crud.controllers;


import demo.reactAdmin.crud.entities.Product;
import demo.reactAdmin.crud.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactAdmin.rest.entities.FilterWrapper;
import reactAdmin.rest.services.FilterService;

@RestController
@RequestMapping("api/v1")
public class ProductController {

    @Autowired
    private FilterService<Product> filterService;

    @Autowired
    private ProductRepository repo;

    @RequestMapping(value = "products", method = RequestMethod.POST)
    public Product create(@RequestBody Product product) {
        return repo.save(product);
    }

    @RequestMapping(value = "products/{id}", method = RequestMethod.PUT)
    public Product update(@RequestBody Product product, @PathVariable int id) {
        product.id = id;
        return repo.save(product);
    }

    @RequestMapping(value = "products/{id}/published/{value}", method = RequestMethod.POST)
    public Product publishedUpdate(@PathVariable int id, @PathVariable boolean value) {
        Product product = repo.findOne(id);
        product.published = value;
        return repo.save(product);
    }

    @RequestMapping(value = "products/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable int id) {
        return repo.findOne(id);
    }

    @RequestMapping(value = "products", method = RequestMethod.GET)
    public Page<Product> filterBy(
            @RequestParam(required = false, name = "filter") String filterStr,
            Pageable pageable) {
        return repo.findAll(pageable);
    }
}
