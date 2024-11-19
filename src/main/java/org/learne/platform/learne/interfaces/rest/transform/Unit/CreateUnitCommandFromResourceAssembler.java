package org.learne.platform.learne.interfaces.rest.transform.Unit;

import org.learne.platform.learne.domain.model.commands.CreateUnitCommand;
import org.learne.platform.learne.interfaces.rest.resources.Unit.CreateUnitResource;

public class CreateUnitCommandFromResourceAssembler {
    public static CreateUnitCommand toCommandFromResource(CreateUnitResource resource) {
        return new CreateUnitCommand(resource.course_id(), resource.title());
    }
}
