package triagegrading.model;

import triagegrading.utils.Storage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TriageModel {

    private List<Grade> grades;

    public void init() throws IOException, ClassNotFoundException {
        Object data = Storage.read();
        if (fileHasContent(data)) {
            grades = (List<Grade>) data;
        }
    }

    private static boolean fileHasContent(Object data) {
        return data != null;
    }

    public void saveAssignment(int score) {
        Assignment assignment = new Assignment(getNextAssignmentNumber(), score);
        Grade grade = new Grade(assignment, getTotalScore());
        createGradeObjectIfNull();
        grades.add(grade);
    }

    private void createGradeObjectIfNull() {
        if (grades == null) {
            grades = new ArrayList<>();
        }
    }
    
    public void saveToFile() throws IOException {
        Storage.save(grades);
    }

    public int getNextAssignmentNumber() {
        if (!hasGradeAdded()) {
            return 1;
        }
        return grades.size() + 1;
    }

    public boolean hasGradeAdded() {
        return !(grades == null || grades.isEmpty());
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
    
    public List<Grade> getGrades() {
        return grades;
    }
    
    public double getCurrentNumericalGrade() {
        Grade finalGrade = findLastAddedGrade();
        return (finalGrade == null) ? 0 : finalGrade.getNumericalPercentageGrade();
    }
    
    public String getCurrentLetterGrade() {
        Grade finalGrade = findLastAddedGrade();
        return (finalGrade == null) ? "F" : finalGrade.getLetterGrade();
    }

    public static boolean isInputLengthValid(String input) {
        try {
            int number = Integer.parseInt(input);
            return number < 4;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
    
    private Grade findLastAddedGrade() {
        if (grades == null) {
            return null;
        }
        return grades.get(grades.size() - 1);
    }
}
