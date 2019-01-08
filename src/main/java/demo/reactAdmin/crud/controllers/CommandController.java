package demo.reactAdmin.crud.controllers;


import demo.reactAdmin.crud.entities.Command;
import demo.reactAdmin.crud.entities.QuantifiedProduct;
import demo.reactAdmin.crud.repos.CommandRepository;
import demo.reactAdmin.crud.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactAdmin.rest.services.FilterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("api/v1")
public class CommandController {

    @Autowired
    private FilterService<Command> filterService;

    @Autowired
    private CommandRepository repo;

    @Autowired
    private ProductRepository productRepo;

    @RequestMapping(value = "commands", method = RequestMethod.POST)
    public Command create(@RequestBody Command command) {
        for (QuantifiedProduct qp: command.basket) {
            qp.product = productRepo.findOne(qp.productId);
        }
        return repo.save(command);
    }

    @RequestMapping(value = "commands/{id}", method = RequestMethod.PUT)
    public Command update(@RequestBody Command command, @PathVariable int id) {
        command.id = id;
        for (QuantifiedProduct qp: command.basket) {
            qp.product = productRepo.findOne(qp.productId);
        }
        return repo.save(command);
    }

    @RequestMapping(value = "commands/{id}/published/{value}", method = RequestMethod.POST)
    public Command publishedUpdate(@PathVariable int id, @PathVariable boolean value) {
        Command command = repo.findOne(id);
        command.published = value;
        return repo.save(command);
    }

    @RequestMapping(value = "commands/{id}", method = RequestMethod.GET)
    public Command getById(@PathVariable int id) {
        return repo.findOne(id);
    }

    @RequestMapping(value = "commands", method = RequestMethod.GET)
    public Page<Command> filterBy(
            @RequestParam(required = false, name = "filter") String filterStr,
            Pageable pageable) {
        return repo.findAll(pageable);
    }
}