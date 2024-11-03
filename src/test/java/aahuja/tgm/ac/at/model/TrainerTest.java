package aahuja.tgm.ac.at.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TrainerTest {
  @Test
  public void emptyQuestionListRetrieval() {
    Trainer t = new Trainer();
    assertNull(t.getRandomQuestion());
  }

  @Test
  public void defaultStatistics() {
    Trainer t = new Trainer();
    assertEquals(t.getTotalGuesses(), 0);
    assertEquals(t.getCorrectGuesses(), 0);
  }

  @Test
  public void questionAdded() {
    Trainer t = new Trainer();
    t.addQuestion(new Question("Test", "https://example.com"));
    assertNotNull(t.getRandomQuestion());
  }

  @Test
  public void checkStatisticsUpdate() {
    Trainer t = new Trainer();
    Question q = new Question("Test", "https://example.com");
    t.addQuestion(q);
    Question ret = t.getRandomQuestion();
    t.checkQuestion(ret, "Test");
    assertEquals(t.getTotalGuesses(), 1);
    assertEquals(t.getCorrectGuesses(), 1);
    t.checkQuestion(ret, "Nope");
    assertEquals(t.getTotalGuesses(), 2);
    assertEquals(t.getCorrectGuesses(), 1);
  }
}
