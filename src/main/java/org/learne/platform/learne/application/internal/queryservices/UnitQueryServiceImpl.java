package org.learne.platform.learne.application.internal.queryservices;

import org.learne.platform.learne.domain.model.aggregates.Unit;
import org.learne.platform.learne.domain.model.queries.GetUnitByCourseIdQuery;
import org.learne.platform.learne.domain.services.Unit.UnitQueryService;
import org.learne.platform.learne.infrastructure.persistence.jpa.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnitQueryServiceImpl implements UnitQueryService {
    private final UnitRepository _unitRepository;

    public UnitQueryServiceImpl(UnitRepository unitRepository) {
        this._unitRepository = unitRepository;
    }

    @Override
    public Optional<Unit> handle(GetUnitByCourseIdQuery query) {
        return _unitRepository.findUnitByCourseId(query.courseId());
    }
}
