package aahuja.tgm.ac.at.controller;

import aahuja.tgm.ac.at.model.Question;
import aahuja.tgm.ac.at.model.Trainer;
import aahuja.tgm.ac.at.view.View;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Controller {
  private Trainer trainer;
  private View view;
  private File selectedFile;

  public Controller() {
    JFileChooser fileChooser = new JFileChooser();
    int fileChooserStatus = fileChooser.showSaveDialog(null);

    if (fileChooserStatus == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
    }

    this.trainer = (selectedFile == null || !selectedFile.exists())
                       ? new Trainer()
                       : Trainer.load(selectedFile);
    this.view = new View();

    init();

    this.view.addValidatorButtonAction((ActionEvent e) -> {
      String input = view.getWordInput();
      boolean correct =
          trainer.checkQuestion(trainer.getCurrentQuestion(), input);
      if (correct) {
        Question randQuestion = trainer.getRandomQuestion();
        view.setImage(randQuestion.getUrl());
        view.setCorrectGuessesText(
            Integer.toString(trainer.getCorrectGuesses()));
      }
      view.setTotalGuessesText(Integer.toString(trainer.getTotalGuesses()));
      if (selectedFile != null) {
        trainer.save(selectedFile);
      }
    });

    this.view.addQuestionsButtonAction((ActionEvent e) -> {
      JTextField word = new JTextField();
      JTextField url = new JTextField();
      Object[] message = {"Word", word, "URL", url};

      int option = JOptionPane.showConfirmDialog(null, message, "Add Question",
                                                 JOptionPane.OK_CANCEL_OPTION);
      if (option == JOptionPane.OK_OPTION) {
        try {
          Question q = new Question(word.getText(), url.getText());
          if (trainer.getCurrentQuestion() == null) {
            trainer.addQuestion(q);
            init();
          } else
            trainer.addQuestion(q);
        } catch (IllegalArgumentException err) {
          JOptionPane.showMessageDialog(
              null, "Can't add the question pair, check for valid URL!");
        }
      }
      if (selectedFile != null) {
        trainer.save(selectedFile);
      }
    });
  }

  void init() {
    view.setTotalGuessesText(Integer.toString(trainer.getTotalGuesses()));
    view.setCorrectGuessesText(Integer.toString(trainer.getCorrectGuesses()));
    if (trainer.getCurrentQuestion() != null) {
      view.setValidatorButtonEnabled(true);
      view.setImage(trainer.getCurrentQuestion().getUrl());
    } else {
      view.setValidatorButtonEnabled(false);
    }
  }
}
