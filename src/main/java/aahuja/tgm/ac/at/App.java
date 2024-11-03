package aahuja.tgm.ac.at;

import aahuja.tgm.ac.at.controller.Controller;
import javax.swing.UIManager;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    System.setProperty("awt.useSystemAAFontSettings", "on");
    System.setProperty("swing.aatext", "true");
    new Controller();
  }
}
