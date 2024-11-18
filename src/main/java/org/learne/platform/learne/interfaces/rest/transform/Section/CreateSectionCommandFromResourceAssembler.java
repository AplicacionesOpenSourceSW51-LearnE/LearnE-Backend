package org.learne.platform.learne.interfaces.rest.transform.Section;

import org.learne.platform.learne.domain.model.commands.CreateSectionCommand;
import org.learne.platform.learne.interfaces.rest.resources.Section.CreateSectionResource;

public class CreateSectionCommandFromResourceAssembler {
    public static CreateSectionCommand toCommand(CreateSectionResource resource) {
        return new CreateSectionCommand(resource.unit_id(), resource.title(), resource.description(), resource.urlToVideo());
    }
}
