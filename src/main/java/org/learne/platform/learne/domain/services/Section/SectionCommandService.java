package org.learne.platform.learne.domain.services.Section;

import org.learne.platform.learne.domain.model.aggregates.Section;
import org.learne.platform.learne.domain.model.commands.CreateSectionCommand;

import java.util.Optional;

public interface SectionCommandService {
    Optional<Section> handle(CreateSectionCommand command);
}
