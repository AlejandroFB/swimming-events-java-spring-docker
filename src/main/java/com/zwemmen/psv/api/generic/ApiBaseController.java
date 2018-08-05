package com.zwemmen.psv.api.generic;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Base service, with the methods all API services need.
 *
 * @author afernandez
 */
public abstract class ApiBaseController<INPUT extends ApiInputView, OUTPUT extends ApiOutputView, ID,
        SERVICE extends ApiService<INPUT, OUTPUT, ID>> {

    protected abstract SERVICE getService();

    // TODO: Now create is used ONLY to create competitions and swimmers for test purposes, and that will be done
    // TODO: internally in the future, then we can remove it from the API

    // TODO: Put update is not used at all. Maybe in the future could be useful for coaches to update entities.

    @GetMapping
    public List<OUTPUT> findAll() {
        return getService().findAll();
    }

    @GetMapping("/{entityId}")
    public OUTPUT findOne(@PathVariable("entityId") ID entityId) {
        return getService().findOne(entityId);
    }

    @PostMapping
    public OUTPUT create(@RequestBody INPUT viewObject) {
        return getService().create(viewObject);
    }

    @PutMapping("/{entityId}")
    public OUTPUT update(@PathVariable("entityId") ID entityId, @RequestBody INPUT viewObject) {
        return getService().update(entityId, viewObject);
    }

    public static class SwimmerIdentifier {
        private Integer swimmerId;

        public Integer getSwimmerId() {
            return swimmerId;
        }
    }

    public static class MeetIdentifiers {
        private List<Integer> meetIds;

        public List<Integer> getMeetIds() {
            return meetIds;
        }
    }
}