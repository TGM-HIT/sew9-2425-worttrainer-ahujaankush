package aahuja.tgm.ac.at.model;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class QuestionTest {
  @Test
  public void invalidURL() {
    assertThrows(IllegalArgumentException.class,
                 () -> { new Question("Test", "ThisIsNotAValidURL"); });
  }

  @Test
  public void wordIsNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      new Question(null, "https://ThisIsNotAValidURL.com");
    });
  }

  @Test
  public void urlIsNull() {
    assertThrows(IllegalArgumentException.class,
                 () -> { new Question("Test", null); });
  }

  @Test
  public void validURL() {
    assertDoesNotThrow(
        () -> { new Question("Test", "https://ThisIsNotAValidURL.com"); });
  }
}
