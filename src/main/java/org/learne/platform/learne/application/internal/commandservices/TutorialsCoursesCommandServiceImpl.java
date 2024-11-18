package org.learne.platform.learne.application.internal.commandservices;

import org.learne.platform.learne.domain.model.aggregates.TutorialsCourses;
import org.learne.platform.learne.domain.model.commands.CreateTutorialsCoursesCommand;
import org.learne.platform.learne.domain.services.TutorialsCourses.TutorialsCoursesCommandService;
import org.learne.platform.learne.infrastructure.persistence.jpa.TutorialsCoursesRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorialsCoursesCommandServiceImpl implements TutorialsCoursesCommandService {
    private final TutorialsCoursesRepository tutorialsCoursesRepository;
    public TutorialsCoursesCommandServiceImpl(TutorialsCoursesRepository tutorialsCoursesRepository) {
        this.tutorialsCoursesRepository = tutorialsCoursesRepository;
    }

    @Override
    public Long handle(CreateTutorialsCoursesCommand command) {
        if(tutorialsCoursesRepository.existsByDateAndHour(command.date(), command.hour())) {
            throw new IllegalArgumentException("Tutorials Course in the date " + command.date() +
                    " and hour " + command.hour() + " already exists");
        }
        var newTutorialsCourses = new TutorialsCourses(command);

        try {
            tutorialsCoursesRepository.save(newTutorialsCourses);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("An error occurred while saving the tutorials course " + e.getMessage());
        }
        return newTutorialsCourses.getId();
    }
}
