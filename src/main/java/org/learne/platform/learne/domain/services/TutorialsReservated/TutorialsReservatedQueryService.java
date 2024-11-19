package org.learne.platform.learne.domain.services.TutorialsReservated;

import org.learne.platform.learne.domain.model.aggregates.TutorialsReservated;
import org.learne.platform.learne.domain.model.queries.TutorialsReservated.GetAllTutorialsReservatedQuery;
import org.learne.platform.learne.domain.model.queries.TutorialsReservated.GetTutorialsReservatedByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TutorialsReservatedQueryService {
    Optional<TutorialsReservated> handle(GetTutorialsReservatedByIdQuery query);
    List<TutorialsReservated> handle(GetAllTutorialsReservatedQuery query);
}
