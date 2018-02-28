package triagegrading.model;

import java.io.Serializable;

public class Assignment implements Serializable {
    private final int assignmentNumber;
    private final int score;
    
    public Assignment(int assignmentNumber, int score) {
        this.assignmentNumber = assignmentNumber;
        this.score = score;
    }
    
    public int getScore() {
        return score;
    }
    
    public int getAssignmentNumber() {
        return assignmentNumber;
    }
}
