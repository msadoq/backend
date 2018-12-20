package demo.reactAdmin.crud.controllers;

import demo.reactAdmin.crud.entities.Parameter;
import demo.reactAdmin.crud.repos.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactAdmin.rest.entities.FilterWrapper;
import reactAdmin.rest.services.FilterService;

@RestController
@RequestMapping("api/v1")
public class ParameterController {

    @Autowired
    private FilterService<Parameter> filterService;

    @Autowired
    private ParameterRepository param;

    @RequestMapping(value = "parameters", method = RequestMethod.POST)
    public Parameter create(@RequestBody Parameter parameter) {
        return param.save(parameter);
    }

    @RequestMapping(value = "parameters/{id}", method = RequestMethod.PUT)
    public Parameter update(@RequestBody Parameter parameter, @PathVariable Integer id) {
        parameter.id = id;
        return param.save(parameter);
    }

    @RequestMapping(value = "parameters/{id}/published/{value}", method = RequestMethod.POST)
    public Parameter publishedUpdate(@PathVariable int id, @PathVariable boolean value) {
        Parameter parameter = param.findOne(id);
        return param.save(parameter);
    }

    @RequestMapping(value = "parameters/{id}", method = RequestMethod.GET)
    public Parameter getById(@PathVariable Integer id) {
        return param.findOne(id);
    }

    @RequestMapping(value = "parameters", method = RequestMethod.GET)
    public Iterable<Parameter> filterBy(
            @RequestParam(required = false, name = "filter") String filterStr,
            @RequestParam(required = false, name = "range") String rangeStr, @RequestParam(required = false, name = "sort") String sortStr) {

        FilterWrapper wrapper = filterService.extractFilterWrapper(filterStr, rangeStr, sortStr);
        return filterService.filterBy(wrapper, param);
    }
}