package aahuja.tgm.ac.at.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * UI Implementation using Swing
 * @author Ankush Ahuja
 * @version 05-11-2024
 */
public class View extends JFrame {
  private JLabel totalGuessesLabel;
  private JLabel correctGuessesLabel;
  private JLabel imageLabel;
  private JTextField wordTextField;
  private JButton validateInputButton;
  private JButton addQuestionsButton;

  public View() {
    super();
    this.totalGuessesLabel = new JLabel("Total guesses: 0");
    this.correctGuessesLabel = new JLabel("Correct guesses: 0");
    this.totalGuessesLabel.setForeground(Color.GREEN);
    this.imageLabel = new JLabel(); // You can set an image here if needed
    imageLabel.setSize(new Dimension(120, 120));
    this.wordTextField = new JTextField(15); // Width of 15 columns
    this.validateInputButton = new JButton("Check!");
    this.addQuestionsButton = new JButton("Add questions");

    JPanel parentPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.add(totalGuessesLabel);
    leftPanel.add(correctGuessesLabel);
    leftPanel.add(wordTextField);
    leftPanel.add(validateInputButton);
    leftPanel.add(addQuestionsButton);
    rightPanel.add(imageLabel);
    parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.X_AXIS));
    parentPanel.add(leftPanel);
    parentPanel.add(rightPanel);
    this.add(parentPanel);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setTitle("Worttrainer v2");
    this.setVisible(true);
  }

  public void setTotalGuessesText(String s) {
    this.totalGuessesLabel.setText("Total guesses: " + s);
  }

  public void setCorrectGuessesText(String s) {
    this.correctGuessesLabel.setText("Correct guesses: " + s);
  }

  public void setImage(URL u) {
    try {
      ImageIcon imageIcon = new ImageIcon(ImageIO.read(u));
      this.imageLabel.setIcon(imageIcon);
      this.imageLabel.repaint();
    } catch (IOException e) {
      JOptionPane.showMessageDialog(null,
                                    "Error loading image: " + e.getMessage());
    }
  }

  public String getWordInput() { return this.wordTextField.getText(); }

  public void addValidatorButtonAction(ActionListener al) {
    this.validateInputButton.addActionListener(al);
  }

  public void setValidatorButtonEnabled(boolean status) {
    this.validateInputButton.setEnabled(status);
  }

  public void addQuestionsButtonAction(ActionListener al) {
    this.addQuestionsButton.addActionListener(al);
  }
}
