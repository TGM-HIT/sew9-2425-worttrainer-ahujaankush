package aahuja.tgm.ac.at.model;

import java.util.ArrayList;

public class RechtschreibTrainer {
  private int totalGuesses;
  private int correctGuesses;
  private ArrayList<Question> questions;

  public RechtschreibTrainer() {
    this.totalGuesses = 0;
    this.correctGuesses = 0;
    this.questions = new ArrayList<>();
  }

  public int getTotalGuesses() { return this.totalGuesses; }

  public int getCorrectGuesses() { return this.correctGuesses; }

  public void addQuestion(Question q) { this.questions.add(q); }

  public Question getRandomQuestion() {
    int randomIndex = (int)(Math.random() * questions.size());
    return questions.get(randomIndex);
  }

  public boolean checkQuestion(Question q, String w) {
    boolean is_correct = q.checkWort(w);
    totalGuesses++;
    if (is_correct)
      correctGuesses++;
    return is_correct;
  }
}
