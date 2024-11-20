package org.learne.platform.learne.interfaces.rest.transform.Unit;

import org.learne.platform.learne.domain.model.aggregates.Unit;
import org.learne.platform.learne.interfaces.rest.resources.Unit.UnitResource;

public class UnitResourceFromEntityAssembler {
    public static UnitResource toResourceFromEntity(Unit entity) {
        return new UnitResource(entity.getId(), entity.getCourse().getId(), entity.getTitle());
    }
}
