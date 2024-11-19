package org.learne.platform.learne.domain.services.Unit;

import org.learne.platform.learne.domain.model.aggregates.Unit;
import org.learne.platform.learne.domain.model.queries.GetUnitByCourseIdQuery;

import java.util.Optional;

public interface UnitQueryService {
    Optional<Unit> handle(GetUnitByCourseIdQuery query);
}
