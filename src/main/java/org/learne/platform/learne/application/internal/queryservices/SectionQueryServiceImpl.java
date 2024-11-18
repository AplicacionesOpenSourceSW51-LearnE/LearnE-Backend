package org.learne.platform.learne.application.internal.queryservices;

import org.learne.platform.learne.domain.model.aggregates.Section;
import org.learne.platform.learne.domain.model.queries.GetSectionByTitleQuery;
import org.learne.platform.learne.domain.services.Section.SectionQueryService;
import org.learne.platform.learne.infrastructure.persistence.jpa.SectionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionQueryServiceImpl implements SectionQueryService {
    public final SectionRepository _sectionRepository;

    public SectionQueryServiceImpl(SectionRepository sectionRepository) { this._sectionRepository = sectionRepository; }

    @Override
    public Optional<Section> handle(GetSectionByTitleQuery query) {
        return _sectionRepository.findByTitle(query.title());
    }
}
