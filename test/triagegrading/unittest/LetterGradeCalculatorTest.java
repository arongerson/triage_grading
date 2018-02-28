/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triagegrading.unittest;

import org.junit.Assert;
import org.junit.Test;
import triagegrading.model.LetterGradeCalculator;

/**
 *
 * @author user1
 */
public class LetterGradeCalculatorTest {
    
    @Test
    public void shouldReturnAPlus() {
        double score = 94.1;
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals("A+", letterGrade);
        score = 100;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals("A+", letterGrade);
        score = 100;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals("A+", letterGrade);
    }
    
    @Test
    public void shouldReturnA() {
        double score = 94;
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals("A", letterGrade);
        score = 90;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals("A", letterGrade);
        score = 89.1;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals("A", letterGrade);
    }
    
    @Test
    public void shouldReturnBPlus() {
        double score = 89;
        String expectedLetterGrade = "B+";
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 87;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 83.1;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
    
    @Test
    public void shouldReturnB() {
        double score = 83;
        String expectedLetterGrade = "B";
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 79;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 78.1;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
    
    @Test
    public void shouldReturnBMinus() {
        double score = 78;
        String expectedLetterGrade = "B-";
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 74;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 72.1;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
    
    @Test
    public void shouldReturnCPlus() {
        double score = 72;
        String expectedLetterGrade = "C+";
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 69;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 67.1;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
    
    @Test
    public void shouldReturnC() {
        double score = 67;
        String expectedLetterGrade = "C";
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 65;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 60.1;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
    
    @Test
    public void shouldReturnCMinus() {
        double score = 60;
        String expectedLetterGrade = "C-";
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 57;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 53.1;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
    
    @Test
    public void shouldReturnDPlus() {
        double score = 53;
        String expectedLetterGrade = "D+";
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 50;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 47.1;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
    
    @Test
    public void shouldReturnD() {
        double score = 47;
        String expectedLetterGrade = "D";
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 45;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 40.1;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
    
    @Test
    public void shouldReturnDMinus() {
        double score = 40;
        String expectedLetterGrade = "D-";
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 35;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 33.1;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
    
    @Test
    public void shouldReturnF() {
        double score = 33;
        String expectedLetterGrade = "F";
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 10;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 0;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
    
    @Test
    public void shouldReturnNA() {
        double score = 100.1;
        String expectedLetterGrade = LetterGradeCalculator.NOT_APPLICABLE;
        String letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = -2;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
        score = 500;
        letterGrade = LetterGradeCalculator.getLetterGrade(score);
        Assert.assertEquals(expectedLetterGrade, letterGrade);
    }
}
