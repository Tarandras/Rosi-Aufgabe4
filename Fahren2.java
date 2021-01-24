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
      int startPosition_Y = es.liesWert(90);
      int startPosition_X = es.liesWert(0);
      int startPosition_XY = es.liesWert(-45);
      int fahrPosition_Y = es.liesWert(90);
      int fahrPosition_X = es.liesWert(0);
      int fahrPosition_XY = es.liesWert(-45);
      long taskBeendenNach = 60000;
      Long laufzeit = laufzeit();

      public void Aktion() {
        for (Long i = laufzeit(); i < taskBeendenNach; i = laufzeit()) {
          fahrPosition_Y = es.liesWert(90);
          fahrPosition_X = es.liesWert(0);
          fahrPosition_XY = es.liesWert(-45);
          // negativ runden!!!
          if (startPosition_X == fahrPosition_X && startPosition_Y == fahrPosition_Y
              && startPosition_XY == fahrPosition_XY) {
            System.out.print("Dauer: " + (laufzeit() - laufzeit));
            laufzeit += laufzeit();
          }
          if (lsl.hell())
            rechts(150);
          if (lsr.hell())
            links(150);
          System.out.println(laufzeit());
          plot();
          // zeichnet punkt auf Display
        }
      }
    };

    pr.starten();
    fahren(200);
  }

  public void zeichnen(Graphics2D g) {

  }
}