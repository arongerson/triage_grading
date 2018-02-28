/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triagegrading.model;

/**
 *
 * @author user1
 */
public class LetterGradeCalculator {
    public static final String NOT_APPLICABLE = "NA";
    private static double score;
    public static String getLetterGrade(double score) {
        LetterGradeCalculator.score = score;
        if (isAPlus()) {
            return "A+";
        } else if (isA()) {
            return "A";
        } else if (isBPlus()) {
            return "B+";
        } else if (isB()) {
            return "B";
        } else if (isBMinus()) {
            return "B-";
        } else if (isCPlus()) {
            return "C+";
        } else if (isC()) {
            return "C";
        } else if (isCMinus()) {
            return "C-";
        } else if (isDPlus()) {
            return "D+";
        } else if (isD()) {
            return "D";
        } else if (isDMinus()) {
            return "D-";
        } else if (isF()) {
            return "F";
        }
        return NOT_APPLICABLE;
    }
    
    private static boolean isAPlus() {
        return score > 94 && score <= 100;
    }
    
    private static boolean isA() {
        return score > 89 && score <= 94;
    }
    
    private static boolean isBPlus() {
        return score > 83 && score <= 89;
    }
    
    private static boolean isB() {
        return score > 78 && score <= 83;
    }
    
    private static boolean isBMinus() {
        return score > 72 && score <= 78;
    }
    
    private static boolean isCPlus() {
        return score > 67 && score <= 72;
    }
    
    private static boolean isC() {
        return score > 60 && score <= 67;
    }
    
    private static boolean isCMinus() {
        return score > 53 && score <= 60;
    }
    
    private static boolean isDPlus() {
        return score > 47 && score <= 53;
    }
    
    private static boolean isD() {
        return score > 40 && score <= 47;
    }
    
    private static boolean isDMinus() {
        return score > 33 && score <= 40;
    }
    
    private static boolean isF() {
        return score >= 0 && score <= 33;
    }
}
