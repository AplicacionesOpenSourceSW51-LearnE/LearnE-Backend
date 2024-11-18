package org.learne.platform.learne.application.internal.commandservices;
import org.learne.platform.learne.domain.model.aggregates.Section;
import org.learne.platform.learne.domain.model.commands.CreateSectionCommand;
import org.learne.platform.learne.domain.services.Section.SectionCommandService;
import org.learne.platform.learne.infrastructure.persistence.jpa.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionCommandServiceImpl implements SectionCommandService {
    private final SectionRepository _sectionRepository;

    public SectionCommandServiceImpl(SectionRepository sectionRepository) { this._sectionRepository = sectionRepository; }

    @Override
    public Optional<Section> handle(CreateSectionCommand command) {
        if (_sectionRepository.existsByTitle(command.title()))
            throw new IllegalArgumentException("Title already exists.");
        var section = new Section(command);
        var createCourse = _sectionRepository.save(section);
        return Optional.of(createCourse);
    }
}
