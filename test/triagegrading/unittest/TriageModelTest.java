package triagegrading.unittest;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import triagegrading.model.Assignment;
import triagegrading.utils.Storage;
import triagegrading.model.TriageModel;

public class TriageModelTest {

    @Test
    public void testSaveAssignment() {
        TriageModel model = new TriageModel();
        model.saveAssignment(2);
        int totalScore = model.getTotalScore();
        Assert.assertEquals(2, totalScore);
    }

    @Test
    public void testNextAssignmentNumber() {
        TriageModel model = new TriageModel();
        model.clearData();
        int nextAssignment = model.getNextAssignmentNumber();
        Assert.assertEquals(1, nextAssignment);
        model.saveAssignment(2);
        nextAssignment = model.getNextAssignmentNumber();
        Assert.assertEquals(2, nextAssignment);
        model.saveAssignment(1);
        nextAssignment = model.getNextAssignmentNumber();
        Assert.assertEquals(3, nextAssignment);
    }

    @Test
    public void testTotalScore() {
        TriageModel model = new TriageModel();
        model.clearData();
        int totalScore = model.getTotalScore();
        Assert.assertEquals(0, totalScore);
        model.saveAssignment(0);
        totalScore = model.getTotalScore();
        Assert.assertEquals(0, totalScore);
        model.saveAssignment(1);
        totalScore = model.getTotalScore();
        Assert.assertEquals(1, totalScore);
        model.saveAssignment(2);
        totalScore = model.getTotalScore();
        Assert.assertEquals(3, totalScore);
    }

    @Test
    public void testClearData() throws IOException, ClassNotFoundException {
        TriageModel model = new TriageModel();
        model.saveAssignment(0); 
        model.saveToFile(); // this ensures that the file has been created
        boolean returnValue = model.clearData();
        Assert.assertTrue(returnValue);
    }

    @Test
    public void testInitWithNoFile() {
        try {
            TriageModel model = new TriageModel();
            model.clearData(); // deletes the file
            model.init();
            Assert.assertTrue(false);
        } catch (IOException ex) {
            // since no file exists IOException is thrown
            Assert.assertTrue(true);
        } catch (ClassNotFoundException ex) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testInitWithFileContainingInvalidData() {
        try {
            TriageModel model = new TriageModel();
            Assignment assignment = new Assignment(1, 2);
            Storage.save(assignment); // Assignment object saved but the init method expects an ArrayList<Grade>
            model.init(); // should throw exception
            Assert.assertTrue(false);
        } catch (IOException ex) {
            Assert.assertTrue(false);
        } catch (ClassNotFoundException ex) {
            Assert.assertTrue(false);
        } catch (ClassCastException ex) {
            // since no file exists ClassCastException is thrown
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testInitWithFileContainingValidData() {
        try {
            TriageModel model = new TriageModel();
            model.saveAssignment(2);
            model.saveAssignment(1);
            model.saveAssignment(3);
            model.saveAssignment(2);
            model.saveToFile();
            model.init();
            Assert.assertEquals(8, model.getTotalScore());
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testGetCurrentNumericalAndLetterGradeWithFileDeleted() throws ClassNotFoundException {
        TriageModel model = new TriageModel();
        model.clearData();
        try {
            model.init();
            Assert.assertTrue(false);
        } catch (IOException e) {
            Assert.assertEquals(0, model.getCurrentNumericalGrade(), 0);
            Assert.assertEquals("F", model.getCurrentLetterGrade());
        }
    }

    @Test
    public void testGetCurrentNumericalAndLetterGradeWithGradesAdded() throws ClassNotFoundException {
        TriageModel model = new TriageModel();
        model.clearData();
        try {
            model.init();
        } catch (IOException e) {
            // the file has been deleted so exception will be thrown, but when we save the first
            // assignment the file will be created
        }
        model.saveAssignment(0); // a score of 0 added
        Assert.assertEquals(0, model.getCurrentNumericalGrade(), 0);
        Assert.assertEquals("F", model.getCurrentLetterGrade());
        model.saveAssignment(1); // a score of 1 added, 1 out of 6
        Assert.assertEquals(16.67, model.getCurrentNumericalGrade(), 0.1);
        Assert.assertEquals("F", model.getCurrentLetterGrade());
        model.saveAssignment(3); // a score of 3 added, 4 out of 9
        Assert.assertEquals(44.44, model.getCurrentNumericalGrade(), 0.1);
        Assert.assertEquals("D", model.getCurrentLetterGrade());
    }
    
    @Test
    public void testHasGradeAdded() throws IOException, ClassNotFoundException {
        TriageModel model = new TriageModel();
        model.saveAssignment(0);
        model.saveToFile(); // makes sure the file exists
        model.init();
        model.clearData();  // no grade added
        Assert.assertEquals(false, model.hasGradeAdded());
        model.saveAssignment(3); // grade has been added
        model.saveToFile();
        model.init();
        Assert.assertEquals(true, model.hasGradeAdded()); 
    }
    
    @Test
    public void testIsInputLenghtValid() {
        Assert.assertFalse(TriageModel.isInputLengthValid("4"));
        Assert.assertFalse(TriageModel.isInputLengthValid("-"));
        Assert.assertFalse(TriageModel.isInputLengthValid("w"));
        Assert.assertFalse(TriageModel.isInputLengthValid("x"));
        
        Assert.assertTrue(TriageModel.isInputLengthValid("0"));
        Assert.assertTrue(TriageModel.isInputLengthValid("1"));
        Assert.assertTrue(TriageModel.isInputLengthValid("2"));
        Assert.assertTrue(TriageModel.isInputLengthValid("3"));
    }
}
