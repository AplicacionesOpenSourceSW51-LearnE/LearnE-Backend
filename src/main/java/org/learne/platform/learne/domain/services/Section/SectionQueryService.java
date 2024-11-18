package org.learne.platform.learne.domain.services.Section;

import org.learne.platform.learne.domain.model.aggregates.Section;
import org.learne.platform.learne.domain.model.queries.GetSectionByTitleQuery;

import java.util.Optional;

public interface SectionQueryService {
    Optional<Section> handle(GetSectionByTitleQuery query);
}
