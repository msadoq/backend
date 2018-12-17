package demo.reactAdmin.crud.controllers;

import demo.reactAdmin.crud.entities.Template;
import demo.reactAdmin.crud.repos.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactAdmin.rest.entities.FilterWrapper;
import reactAdmin.rest.services.FilterService;

@RestController
@RequestMapping("api/v1")
public class TemplateController {

    @Autowired
    private FilterService<Template> filterService;

    @Autowired
    private TemplateRepository temp;

    @RequestMapping(value = "templates", method = RequestMethod.POST)
    public Template create(@RequestBody Template template) {
        return temp.save(template);
    }

    @RequestMapping(value = "templates/{id}", method = RequestMethod.PUT)
    public Template update(@RequestBody Template template, @PathVariable Integer id) {
        template.id = id;
        return temp.save(template);
    }

    @RequestMapping(value = "templates/{id}/published/{value}", method = RequestMethod.POST)
    public Template publishedUpdate(@PathVariable int id, @PathVariable boolean value) {
        Template template = temp.findOne(id);
        template.published = value;
        return temp.save(template);
    }

    @RequestMapping(value = "templates/{id}", method = RequestMethod.GET)
    public Template getById(@PathVariable Integer id) {
        return temp.findOne(id);
    }

    @RequestMapping(value = "templates", method = RequestMethod.GET)
    public Iterable<Template> filterBy(
            @RequestParam(required = false, name = "filter") String filterStr,
            @RequestParam(required = false, name = "range") String rangeStr, @RequestParam(required = false, name = "sort") String sortStr) {

        FilterWrapper wrapper = filterService.extractFilterWrapper(filterStr, rangeStr, sortStr);
        return filterService.filterBy(wrapper, temp);
    }
}