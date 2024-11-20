package org.learne.platform.learne.domain.model.valueobjects;
import org.learne.platform.learne.domain.model.aggregates.Course;
import org.learne.platform.learne.domain.model.entities.LearnePathItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;


import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Embeddable
public class LearnePath {
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<LearnePathItem> learnePathItems;

    /**
     * Default constructor
     * @summary
     * Initializes the learning path items list with an empty list.
     */
    public LearnePath() { this.learnePathItems = List.of(); }

    /**
     * Get the first learned path item where the predicate is true
     * @param predicate The predicate to evaluate
     * @return The first learned path item where the predicate is true
     * @see Predicate
     */
    private LearnePathItem getFirstLearnePathItemWhere(Predicate<LearnePathItem> predicate){
        return learnePathItems.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    /**
     * Get the first learned path item where the id is equal to the given id
     * @param itemId The id of the learned path item
     * @return The first learned path item where the id is equal to the given id
     */
    private LearnePathItem getLearningPathItemWithId(Long itemId){
//        return this.getFirstLearnePathItemWhere(item -> item.getId().equals(itemId));
        return null;

    }

    private LearnePathItem getLearnePathItemWithTeacherId(TeacherId teacherId){
        return this.getFirstLearnePathItemWhere(item -> item.getTeacherId().equals(teacherId));
    }

    /**
     * Get the next tutorial in the learning path
     * @param currentTeacherId The id of the current tutorial
     * @return The id of the next tutorial in the learning path
     */
    private TeacherId getNextTeacherInLearnePath(TeacherId currentTeacherId) {
        LearnePathItem nextItem = getLearnePathItemWithTeacherId(currentTeacherId).getNextItem();
        return !Objects.isNull(nextItem) ? nextItem.getTeacherId() : null;
    }

    public boolean isLastTutorialInLearningPath(TeacherId teacherId){
        return Objects.isNull(getLearnePathItemWithTeacherId(teacherId).getNextItem());
    }

    public TeacherId getFirstTeacherlInLearningPath(){
        return learnePathItems.getFirst().getTeacherId();
    }

    public LearnePathItem getLastItemInLearningPath(){
        return this.getFirstLearnePathItemWhere(item -> Objects.isNull(item.getNextItem()));
    }

    public boolean isEmpty(){
        return learnePathItems.isEmpty();
    }

    public void addItem(Course course, TeacherId teacherId, LearnePathItem nextItem){
        LearnePathItem item = new LearnePathItem(course, teacherId, nextItem);
        learnePathItems.add(item);
    }

    public void addItem(Course course, TeacherId teacherId){
        LearnePathItem item = new LearnePathItem(course, teacherId, null);
        LearnePathItem originalLastItem = null;
        if (!isEmpty()) originalLastItem = getLastItemInLearningPath();
        learnePathItems.add(item);
        if (!Objects.isNull(originalLastItem)) originalLastItem.updateNextItem(item);
    }

    public void addItem(Course course, TeacherId teacherId, TeacherId nexTeacherId){
        LearnePathItem nextItem = getLearnePathItemWithTeacherId(nexTeacherId);
        addItem(course, teacherId, nextItem);
    }
}
