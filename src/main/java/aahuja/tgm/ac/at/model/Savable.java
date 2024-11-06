package aahuja.tgm.ac.at.model;

import java.io.File;

public interface Savable {
  public int getTotalGuesses();
  public int getCorrectGuesses();
  public void addQuestion(Question q);
  public Question getRandomQuestion();
  public Question getCurrentQuestion();
  public boolean checkQuestion(Question q, String w);
  public void save(File f);
  public boolean load(File f);
}
