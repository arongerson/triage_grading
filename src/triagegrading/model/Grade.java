package triagegrading.model;

import java.io.Serializable;

public class Grade implements Serializable {
    
    public static final int HIGHEST_SCORE = 3;
    private double numericalGrade;
    private final int totalBeforeThisGrade;
    private final Assignment assignment;
    
    public Grade(Assignment assignment, int totalBeforeThisGrade) {
        this.totalBeforeThisGrade = totalBeforeThisGrade;
        this.assignment = assignment;
    }
    
    public String getLetterGrade() {
        numericalGrade = getNumericalPercentageGrade();
        return LetterGradeCalculator.getLetterGrade(numericalGrade);
    }
    
    public double getNumericalPercentageGrade() {
        int sum = getTotalScore();
        return  ((double)sum / getFullScore()) * 100;
    }
    
    public int getAssignmentNumber() {
        return assignment.getAssignmentNumber();
    }
    
    public int getTotalScore() {
        return totalBeforeThisGrade + assignment.getScore();
    }
    
    public int getFullScore() {
        return assignment.getAssignmentNumber() * HIGHEST_SCORE;
    }
}
