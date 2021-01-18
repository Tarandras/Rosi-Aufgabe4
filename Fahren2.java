/////////////////////////////////////////
//Praktikum Grundlagen der Programmierung
//  Aufgabe 4
/////////////////////////////////////////

import java.awt.*;
import java.io.*;
import java.net.*;

public class Fahren2  
{
  public static void main(String[] args) 
  {  
         new RosiBeispiel();
   }
}

class RosiBeispiel extends Rosi  
{
  LichtSensor lsl,lsr,lsm;

  public void los()
  {
    programmBezeichnung = "Fahren in einer Linie mit zwei Sensoren";

    lsl = new LichtSensor(1);
    lsl.positionieren(20,10);    
    lsr = new LichtSensor(2);
    lsr.positionieren(20,-10); 
      
    hintergrundBild("linie");
    positionieren(-40,250,0);
    
    setDisplayOn();

    fertig();

    Prozess pr= new Prozess(10)
    {
      public void Aktion()
      {                
        if(lsl.hell()) rechts(150);
        if(lsr.hell()) links(150);
        
        plot();
      }
    };

    pr.starten();
    fahren(200);
  }
  
  public void zeichnen(Graphics2D g)
  {
         
  }
 }