package triagegrading.unittest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import triagegrading.model.Assignment;
import triagegrading.utils.Storage;

public class StorageTest {

    @Test
    public void testSavingObjectToFile() {
        int assignmentNumber = 1;
        int score = 2;
        Assignment assignment = new Assignment(assignmentNumber, score);
        try {
            Storage.save(assignment);
            Assert.assertTrue(true);
        } catch (IOException e) {
            Assert.assertFalse(true);
        }
    }

    @Test
    public void testReadingObjectFromFile() {
        int assignmentNumber = 1;
        int score = 2;
        Assignment assignment = new Assignment(assignmentNumber, score);
        try {
            Storage.save(assignment);
            Assignment assignmentFromFile = (Assignment) Storage.read();
            Assert.assertNotNull(assignmentFromFile);
            Assert.assertEquals(assignmentFromFile.getScore(), assignment.getScore());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error: " + e.getMessage());
            Assert.assertFalse(true);
        }
    }
}
