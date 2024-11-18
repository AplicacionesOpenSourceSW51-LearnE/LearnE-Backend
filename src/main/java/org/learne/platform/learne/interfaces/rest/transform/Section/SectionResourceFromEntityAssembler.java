package org.learne.platform.learne.interfaces.rest.transform.Section;

import org.learne.platform.learne.domain.model.aggregates.Section;
import org.learne.platform.learne.interfaces.rest.resources.Section.SectionResource;

public class SectionResourceFromEntityAssembler {
    public static SectionResource toResourceFromEntity(Section entity) {
        return new SectionResource(entity.getUnit_id(), entity.getTitle(), entity.getDescription(), entity.getUrlToVideo().toString());
    }
}
