package aahuja.tgm.ac.at.model;

import java.net.MalformedURLException;
import java.net.URL;

public class Question {
  private final String wort;
  private final String url;

  public Question(String wort, final String url)
      throws IllegalArgumentException {
    if (wort == null || url == null)
      throw new IllegalArgumentException(
          "wort and url may not be equal to null");
    if (!checkUrlValidity(url))
      throw new IllegalArgumentException("Invalid URL");

    this.wort = wort;
    this.url = url;
  }

  public boolean checkUrlValidity(final String urlString) {
    try {
      final URL url = new URL(urlString);
      return url.getProtocol().equals("http") ||
          url.getProtocol().equals("https") || url.getProtocol().equals("file");
    } catch (final MalformedURLException e) {
      return false;
    }
  }
  public boolean checkWort(final String wort) { return this.wort == wort; }
  public boolean checkUrl(final String url) { return this.url == url; }
}
