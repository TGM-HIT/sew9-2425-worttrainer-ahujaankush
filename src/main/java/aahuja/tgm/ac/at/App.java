package aahuja.tgm.ac.at;

import aahuja.tgm.ac.at.view.WorttrainerFrame;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    System.setProperty("awt.useSystemAAFontSettings", "on");
    System.setProperty("swing.aatext", "true");
    new WorttrainerFrame();
  }
}
