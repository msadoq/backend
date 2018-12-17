package demo.reactAdmin.crud.controllers;

import demo.reactAdmin.crud.entities.NotificationDef;
import demo.reactAdmin.crud.repos.NotificationDefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactAdmin.rest.entities.FilterWrapper;
import reactAdmin.rest.services.FilterService;

@RestController
@RequestMapping("api/v1")
public class NotificationDefController {

    @Autowired
    private FilterService<NotificationDef> filterService;

    @Autowired
    private NotificationDefRepository notifdef;

    @RequestMapping(value = "notificationsdef", method = RequestMethod.POST)
    public NotificationDef create(@RequestBody NotificationDef notificationDef) {
        return notifdef.save(notificationDef);
    }

    @RequestMapping(value = "notificationsdef/{id}", method = RequestMethod.PUT)
    public NotificationDef update(@RequestBody NotificationDef notificationDef, @PathVariable Integer id) {
        notificationDef.id = id;
        return notifdef.save(notificationDef);
    }

    @RequestMapping(value = "notificationsdef/{id}/published/{value}", method = RequestMethod.POST)
    public NotificationDef publishedUpdate(@PathVariable int id, @PathVariable boolean value) {
        NotificationDef nd = notifdef.findOne(id);
        nd.published = value;
        return notifdef.save(nd);
    }

    @RequestMapping(value = "notificationsdef/{id}", method = RequestMethod.DELETE)
    public NotificationDef remove(@PathVariable int id) {
        NotificationDef nd = notifdef.findOne(id);
        return notifdef.save(nd);
    }

    @RequestMapping(value = "notificationsdef/{id}", method = RequestMethod.GET)
    public NotificationDef getById(@PathVariable Integer id) {
        return notifdef.findOne(id);
    }

    @RequestMapping(value = "notificationsdef", method = RequestMethod.GET)
    public Iterable<NotificationDef> filterBy(
            @RequestParam(required = false, name = "filter") String filterStr,
            @RequestParam(required = false, name = "range") String rangeStr, @RequestParam(required = false, name = "sort") String sortStr) {

        FilterWrapper wrapper = filterService.extractFilterWrapper(filterStr, rangeStr, sortStr);
        return filterService.filterBy(wrapper, notifdef);
    }
}