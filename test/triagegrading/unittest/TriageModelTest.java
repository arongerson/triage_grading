package triagegrading.unittest;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import triagegrading.model.Assignment;
import triagegrading.model.Storage;
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
        int nextAssignment = model.nextAssignmentNumber();
        Assert.assertEquals(1, nextAssignment);
        model.saveAssignment(2);
        nextAssignment = model.nextAssignmentNumber();
        Assert.assertEquals(2, nextAssignment);
        model.saveAssignment(1);
        nextAssignment = model.nextAssignmentNumber();
        Assert.assertEquals(3, nextAssignment);
    }
    
    @Test
    public void testTotalScore() {
        TriageModel model = new TriageModel();
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
    public void testClearData() {
        TriageModel model = new TriageModel();
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
        } catch (IOException | ClassNotFoundException | ClassCastException ex ) {
            Assert.assertTrue(false);
        }
    }
}
