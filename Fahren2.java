/////////////////////////////////////////
//Praktikum Grundlagen der Programmierung
//  Aufgabe 4
/////////////////////////////////////////

import java.awt.*;
import java.io.*;
import java.net.*;
import java.lang.Math;

public class Fahren2 {
  public static void main(String[] args) {
    new RosiBeispiel();
  }
}

class RosiBeispiel extends Rosi {
  LichtSensor lsl, lsr, lsm;

  public void los() {
    programmBezeichnung = "Fahren in einer Linie mit zwei Sensoren";

    EntfernungsSensor es = new EntfernungsSensor();
    es.positionieren(0, 0);
    lsl = new LichtSensor(1);
    lsl.positionieren(20, 10);
    lsr = new LichtSensor(2);
    lsr.positionieren(20, -10);

    hintergrundBild("linie");
    positionieren(-40, 250, 0);

    setDisplayOn();

    fertig();

    Prozess pr = new Prozess(10) {
      // int startPosition_Y = y();
      int startPosition_X = x();
      long taskBeendenNach = 20000;
      Long laufzeit = (long) 0;
      int schalterStartposition = 0;

      public void Aktion() {
        /*
         * System.out.print("startPosition_Y: " + y()); y 250
         */
        for (Long i = laufzeit(); i < taskBeendenNach; i = laufzeit()) {
          // negativ runden!!!
          if (startPosition_X / 3 == x() / 3 && 240 < y() && schalterStartposition == 0) {
            System.out.println("Dauer: " + (laufzeit() - laufzeit));
            laufzeit += laufzeit();
            schalterStartposition++;
            warten(10);
          }
          if (-200 < y() && schalterStartposition != 0) {
            schalterStartposition = 0;
          }
          if (lsl.hell())
            rechts(150);
          if (lsr.hell())
            links(150);
          plot();
          // zeichnet punkt auf Display
        }
        programmBeenden();
      }
    };

    pr.starten();
    fahren(200);
  }

  public void zeichnen(Graphics2D g) {

  }
}