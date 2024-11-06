package aahuja.tgm.ac.at.model;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Data class to store a question
 * @author Ankush Ahuja
 * @version 06-11-24
 */
public class Question {
  private final String word;
  private final URL url;

  /**
   * Constructor, will requires valid word & url
   *
   * @param word 
   * @param url 
   * @throws IllegalArgumentException 
   * @throws throw new IllegalArgumentException( 
   * @throws throw new IllegalArgumentException("Invalid URL"); 
   */
  public Question(String word, final String url)
      throws IllegalArgumentException {
    if (word == null || url == null)
      throw new IllegalArgumentException(
          "wort and url may not be equal to null");

    try {
      this.word = word;
      this.url = new URL(url);
    } catch (final MalformedURLException e) {
      throw new IllegalArgumentException("Invalid URL");
    }
  }

  /**
   * Checks if passed word matches the one of the question
   *
   * @param word to check
   * @return if word matches the internal one
   */
  public boolean checkWord(final String word) { return this.word.equals(word); }
  
    /**
   * Will return URL
   *
   * @return URL
   */
  public URL getUrl() { return this.url; }
  
    /**
   * Will return word
   *
   * @return word
   */
  public String getWord() { return this.word; }
}
