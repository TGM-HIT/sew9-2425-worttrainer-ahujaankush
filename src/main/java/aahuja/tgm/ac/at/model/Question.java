package aahuja.tgm.ac.at.model;

import java.net.MalformedURLException;
import java.net.URL;

public class Question {
  private final String word;
  private final URL url;

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

  public boolean checkWord(final String word) { return this.word.equals(word); }
  public URL getUrl() { return this.url; }
  public String getWord() { return this.word; }
}
