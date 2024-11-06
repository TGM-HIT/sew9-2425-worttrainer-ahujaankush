# Worttrainer
Im Worttrainer ist zu einem Bild das dazugehoerende Wort zu finden.

## Funktionsweise
Die Applikation wurde mit dem Strategy Pattern und dem MVC Modell implementiert.
Das Strategy Pattern ermoeglicht das Wechseln der Speicher-Methode (im Moment wird JSON genutzt). Das MVC ermoeglicht eine saubere Trennung der Funktionen.

## Controller
- Der Controller hat Referenzen zum Trainer und zu der UI. Methoden in der Trainer & UI ermoeglichen das Abaendern der entsprechenden Zustaende.

## Model
- Das Model ist der Uebergriff fuer jegliches Datenmanagement.
- Eine Question repraesentiert dabei das Paar von einem Wort und dem entsprechenden Bild (als URL).
- Der Trainer ist die Management-Klasse. Sie implementiert das Savable Interface und ermoeglicht das Speichern als JSON.

## View
- Die View ist einfach eine Java-Swing implementierte UI. Die Implementierung ermoeglicht das setzen der Button-Aktionen von aussen.

## Ausfuehren
- Setzt eine funktionsweise Gradle Umgebung vorraus.
```
gradle run
```

