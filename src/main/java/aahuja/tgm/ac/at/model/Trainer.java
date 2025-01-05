package aahuja.tgm.ac.at.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A trainer implementation storing contents in JSON
 * @author Ankush Ahuja
 * @version 06-11-24
 */
public class Trainer implements Savable {
  private int totalGuesses;
  private int correctGuesses;
  private int currentQuestion;
  private ArrayList<Question> questions;

  private static final Logger logger =
      Logger.getLogger(Trainer.class.getName());

  static {
    try {
      // Set up the logger to log messages to a file
      FileHandler fileHandler = new FileHandler(
          "trainer.log", true); // true for appending to the file
      fileHandler.setFormatter(
          new SimpleFormatter()); // Optional: Set a formatter
      logger.addHandler(fileHandler);

      // Set the logger level to capture all levels
      logger.setLevel(Level.ALL);

    } catch (IOException e) {
      System.err.println("Error setting up the file handler for the logger: " +
                         e.getMessage());
    }
  }

  public Trainer() {
    this.totalGuesses = 0;
    this.correctGuesses = 0;
    this.currentQuestion = -1;
    this.questions = new ArrayList<>();
  }

  public Trainer(int totalGuesses, int correctGuesses, int currentQuestion,
                 ArrayList<Question> questions) {
    this.totalGuesses = totalGuesses;
    this.correctGuesses = correctGuesses;
    this.currentQuestion = currentQuestion;
    this.questions = questions;
  }

  @Override
  public int getTotalGuesses() {
    return this.totalGuesses;
  }

  @Override
  public int getCorrectGuesses() {
    return this.correctGuesses;
  }

  @Override
  public void addQuestion(Question q) {
    this.questions.add(q);
  }

  @Override
  public Question getRandomQuestion() {
    if (questions.size() == 0) {
      logger.warning("No questions available to get a random question.");
      return null;
    }
    int randomIndex = (int)(Math.random() * questions.size());
    this.currentQuestion = randomIndex;
    return questions.get(randomIndex);
  }

  @Override
  public Question getCurrentQuestion() {
    if (questions.size() == 0) {
      logger.warning("No questions available.");
      return null;
    }
    if (currentQuestion == -1)
      return this.getRandomQuestion();
    return questions.get(currentQuestion);
  }

  @Override
  public boolean checkQuestion(Question q, String w) {
    boolean is_correct = q.checkWord(w);
    totalGuesses++;
    if (is_correct)
      correctGuesses++;
    return is_correct;
  }

  @Override
  public void save(File f) {
    JSONObject container = new JSONObject();
    container.put("totalGuesses", totalGuesses);
    container.put("correctGuesses", correctGuesses);
    container.put("currentQuestion", currentQuestion);
    container.put("questions", new JSONArray(this.questions));
    try (FileWriter fileWriter = new FileWriter(f)) {
      fileWriter.write(container.toString());
    } catch (IOException e) {
      logger.log(Level.SEVERE,
                 "Unable to write JSON to specified destination: " +
                     e.getMessage(),
                 e);
    }
  }

  @Override
  public boolean load(File f) {
    try {
      String content = new String(Files.readAllBytes(f.toPath()));
      JSONObject obj = new JSONObject(content);

      ArrayList<Question> list = new ArrayList<>();
      JSONArray questions = obj.getJSONArray("questions");
      for (Object q : questions) {
        JSONObject jso = (JSONObject)q;
        list.add(new Question(jso.getString("word"), jso.getString("url")));
      }

      this.totalGuesses = obj.getInt("totalGuesses");
      this.correctGuesses = obj.getInt("correctGuesses");
      this.currentQuestion = obj.getInt("currentQuestion");
      this.questions = list;
      return true;
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Error reading the file: " + e.getMessage(), e);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error parsing JSON: " + e.getMessage(), e);
    }
    return false;
  }
}
