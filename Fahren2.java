/////////////////////////////////////////
//Praktikum Grundlagen der Programmierung
//  Aufgabe 4
/////////////////////////////////////////

//import java.awt.*;
//import java.io.*;
//import java.net.*;
//import java.util.PrimitiveIterator.OfLong;
//import java.lang.Math;

public class Fahren2 {
  public static void main(String[] args) {
    new RosiBeispiel();
  }
}

class RosiBeispiel extends Rosi {

  public void los() {

    programmBezeichnung = "Fahren in einer Linie mit zwei Sensoren";
    LichtSensor lsl, lsr;
    EntfernungsSensor es = new EntfernungsSensor();

    es.positionieren(0, 0);

    lsl = new LichtSensor(1);
    lsl.positionieren(20, 23);
    lsr = new LichtSensor(2);
    lsr.positionieren(20, -23);

    hintergrundBild("linie");
    positionieren(-40, 250, 0);

    setDisplayOn();

    fertig();

    Prozess pr = new Prozess(100) {
      int geschwindigkeit = 0;
      int startPosition_X = 0;
      Long summeRundenZeiten = (long) 0;
      int schalterStartposition = 1;
      int rundenZahl = 0;
      long rundenZeit;

      public void Aktion() {
        fahren(geschwindigkeit);
        geschwindigkeit = goFast(geschwindigkeit);
        int boost = 75;// zwischen 0 - 100
        if (lsr.hell()) {
          linksDrehen(boost);
          // links(boost);
        }

        if (lsl.hell()) {
          rechtsDrehen(boost);
          // rechts(boost);
        }

        if (-200 > y() && -300 > x()) {
          schalterStartposition = 0;
        }
        if (schalterStartposition == 0 && startPosition_X == x() / 10) {
          schalterStartposition++;
          rundenZahl++;
          rundenZeit = laufzeit() - summeRundenZeiten;
          System.out.println("boost, maxSpeed, RundenZeit: " + boost + "\t" + geschwindigkeit + "\t" + rundenZeit);
          summeRundenZeiten += rundenZeit; // um möglichst exakt zu sein, weit oben
        }
        int taktgeber = 100;
        int lauftakt = (int) (laufzeit());
        if (lauftakt % taktgeber == 0) {
          display.clear(true);
          text("Geschwindigkeit: " + (geschwindigkeit), 5, 60);
        }
        if (rundenZahl == 1) {
          // if (rundenZahl == 1 || lauftakt > 750000) {
          programmBeenden();
        }

      }
    };
    pr.starten();
  }

  public static int goFast(int currentSpeed) {
    int maxSpeed = 300;
    if (currentSpeed >= maxSpeed) {
      return maxSpeed;
    } else {
      currentSpeed = 1 + currentSpeed * 110 / 100;
      return currentSpeed;
    }
  }

  public static int goSlow(int currentSpeed) {
    if (currentSpeed * 90 / 100 <= 20) {
      if (currentSpeed - 20 <= 0) {
        return 20;
      } else {
        currentSpeed = currentSpeed - 8;
        return currentSpeed;
      }
    } else {
      currentSpeed = currentSpeed * 90 / 100;
      return currentSpeed;
    }
  }
}
