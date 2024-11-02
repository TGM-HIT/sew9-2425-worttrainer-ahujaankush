package aahuja.tgm.ac.at.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class RechtschreibTrainer {
  private int totalGuesses;
  private int correctGuesses;
  private int currentQuestion;
  private ArrayList<Question> questions;

  public RechtschreibTrainer() {
    this.totalGuesses = 0;
    this.correctGuesses = 0;
    this.currentQuestion = -1;
    this.questions = new ArrayList<>();
  }

  public RechtschreibTrainer(int totalGuesses, int correctGuesses,
                             int currentQuestion,
                             ArrayList<Question> questions) {
    this.totalGuesses = totalGuesses;
    this.correctGuesses = correctGuesses;
    this.currentQuestion = currentQuestion;
    this.questions = questions;
  }

  public int getTotalGuesses() { return this.totalGuesses; }

  public int getCorrectGuesses() { return this.correctGuesses; }

  public void addQuestion(Question q) { this.questions.add(q); }

  public Question getRandomQuestion() {
    int randomIndex = (int)(Math.random() * questions.size());
    this.currentQuestion = randomIndex;
    return questions.get(randomIndex);
  }

  public Question getCurrentQuestion() {
    if (currentQuestion == -1)
      return this.getCurrentQuestion();
    return questions.get(currentQuestion);
  }

  public boolean checkQuestion(Question q, String w) {
    boolean is_correct = q.checkWort(w);
    totalGuesses++;
    if (is_correct)
      correctGuesses++;
    return is_correct;
  }

  public void save(String s) {
    JSONObject container = new JSONObject();
    container.append("totalGuesses", totalGuesses);
    container.append("correctGuesses", correctGuesses);
    container.append("currentQuestion", currentQuestion);
    container.append("questions", new JSONArray(this.questions));
    try {
      container.write(new FileWriter(new File(s)));
    } catch (IOException e) {
      System.err.println("Unable to write JSON to specified destination " +
                         e.getMessage());
    }
  }

  public static RechtschreibTrainer load(String s) {
    try {
      String content = new String(Files.readAllBytes(Paths.get(s)));
      JSONObject obj = new JSONObject(content);

      ArrayList<Question> list = new ArrayList<>();
      JSONArray questions = obj.getJSONArray("questions");
      for (Object q : questions) {
        list.add((Question)q);
      }

      RechtschreibTrainer rt = new RechtschreibTrainer(
          obj.getInt("totalGuesses"), obj.getInt("correctGuesses"),
          obj.getInt("currentQuestion"), list);

      return rt;
    } catch (IOException e) {
      System.err.println("Error reading the file: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error parsing JSON: " + e.getMessage());
    }
    return null;
  }
}
