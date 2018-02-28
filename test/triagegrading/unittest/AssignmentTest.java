package triagegrading.unittest;

import org.junit.Assert;
import org.junit.Test;
import triagegrading.model.Assignment;

public class AssignmentTest {
    
    @Test
    public void shouldCreateAndReturnCorrectScore() {
        int score = 2;
        int assignmentNumber = 1;
        Assignment assignment = new Assignment(assignmentNumber, score); 
        Assert.assertEquals(assignment.getScore(), score);
    }
    
    @Test
    public void shouldCreateAndReturnCorrectAssignmentNumber() {
        int score = 2;
        int assignmentNumber = 1;
        Assignment assignment = new Assignment(assignmentNumber, score); 
        Assert.assertEquals(assignment.getAssignmentNumber(), assignmentNumber);
    }
}
