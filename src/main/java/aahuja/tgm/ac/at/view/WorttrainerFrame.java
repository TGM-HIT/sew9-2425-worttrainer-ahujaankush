package aahuja.tgm.ac.at.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WorttrainerFrame extends JFrame {
  private JLabel totalGuesses;
  private JLabel correctGuesses;
  private JLabel image;
  private JTextField wordInput;
  private JButton checkWord;

  public WorttrainerFrame() {
    super();
    this.totalGuesses = new JLabel("Total guesses: 0");
    this.correctGuesses = new JLabel("Correct guesses: 0");
    this.totalGuesses.setForeground(Color.GREEN);
    this.image = new JLabel();           // You can set an image here if needed
    this.wordInput = new JTextField(15); // Width of 15 columns
    this.checkWord = new JButton("Check!");

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.add(totalGuesses);
    panel.add(correctGuesses);
    panel.add(wordInput);
    panel.add(checkWord);
    panel.add(image);
    this.add(panel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setTitle("Worttrainer v2");
    this.setVisible(true);
  }

  public void setTotalGuesses(String s) { this.totalGuesses.setText(s); }

  public void setCorrectGuesses(String s) { this.correctGuesses.setText(s); }

  public void setImage(String s) {}

  public String getUserInput() { return this.wordInput.getText(); }

  public void addValidatorButtonAction(ActionListener al) {
    this.checkWord.addActionListener(al);
  }
}
