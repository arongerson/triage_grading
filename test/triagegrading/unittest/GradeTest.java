package triagegrading.unittest;

import org.junit.Assert;
import org.junit.Test;
import triagegrading.model.Assignment;
import triagegrading.model.Grade;

public class GradeTest {
    
    @Test
    public void testGradeAfterAddingFirstAssignmentWithZeroScore() {
        int assignmentNumber = 1;
        double expectedNumericalGrade = 0;
        int expectedTotalScore = 0;
        double delta = 0;
        int score = 0;
        int totalScoreBeforeThisGrade = 0;
        int expectedFullScore = 3;
        Assignment assignment = new Assignment(assignmentNumber, score);
        Grade grade = new Grade(assignment, totalScoreBeforeThisGrade);
        Assert.assertEquals(grade.getAssignmentNumber(), assignmentNumber);
        Assert.assertEquals("F", grade.getLetterGrade());
        Assert.assertEquals(expectedNumericalGrade, grade.getNumericalPercentageGrade(), delta);
        Assert.assertEquals(expectedTotalScore, grade.getTotalScore());
        Assert.assertEquals(expectedFullScore, grade.getFullScore());
    }
    
    @Test
    public void testGradeAfterAddingFirstAssignmentWithNonZeroScore() {
        int assignmentNumber = 1;
        int score = 2;
        double expectedNumericalGrade = 66.7;
        int expectedTotalScore = 2;
        double delta = 0.1;
        int totalScoreBeforeThisGrade = 0;
        int expectedFullScore = 3;
        Assignment assignment = new Assignment(assignmentNumber, score);
        Grade grade = new Grade(assignment, totalScoreBeforeThisGrade);
        Assert.assertEquals(grade.getAssignmentNumber(), assignmentNumber);
        Assert.assertEquals("C", grade.getLetterGrade());
        Assert.assertEquals(expectedNumericalGrade, grade.getNumericalPercentageGrade(), delta);
        Assert.assertEquals(expectedTotalScore, grade.getTotalScore());
        Assert.assertEquals(expectedFullScore, grade.getFullScore());
    }
}
