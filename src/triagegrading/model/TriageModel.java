package triagegrading.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TriageModel {

    private List<Grade> grades;

    public void init() throws IOException, ClassNotFoundException {
        Object data = Storage.read();
        if (data != null) {
            grades = (List<Grade>) Storage.read();
        }
    }

    public void saveAssignment(int score) {
        Assignment assignment = new Assignment(nextAssignmentNumber(), score);
        Grade grade = new Grade(assignment, getTotalScore());
        if (grades == null) {
            grades = new ArrayList<>();
        }
        grades.add(grade);
    }
    
    public void saveToFile() throws IOException {
        Storage.save(grades);
    }

    public int nextAssignmentNumber() {
        if (grades == null || grades.isEmpty()) {
            return 1;
        }
        return grades.size() + 1;
    }
    
    public int getTotalScore() {
        Grade lastGrade = findLastAddedGrade();
        if (lastGrade == null) {
            return 0;
        }
        return lastGrade.getTotalScore();
    }

    public boolean clearData() {
        grades = null;
        return Storage.clearData();
    }

    private Grade findLastAddedGrade() {
        if (grades == null) {
            return null;
        }
        return grades.get(grades.size() - 1);
    }
}
