package com.ClockApp;

//import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;



public class Main
{

    public static void main(String[] args)
    {
        // write your code here


        Frame f = new Frame();
        f.addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent e)
            {
                System.exit(0);
            }

            ;
        });
        Clock clock = new Clock();
        f.add(clock);
        f.pack();
        clock.init();
        f.setSize(800, 800); // add 20, seems enough for the Frame title,
        f.show();


    }
}

 class Clock extends Panel implements ActionListener,Runnable
 {
     Button btnAnalogue, btnDigital;

     Panel pnlHeader, pnlBody;
     int Choice = 0;                                        // choice for analogue Or digital
     int hr;
     int min;
     int sec;
     Calendar calendar;
     Thread thread;


     public void init()
     {
         thread = new Thread(this);
         Label title = new Label("Clock for u");
         btnAnalogue = new Button("Analogue");
         btnDigital = new Button("Digital");

         btnAnalogue.addActionListener(this);
         btnDigital.addActionListener(this);

         pnlHeader = new Panel();
         pnlHeader.add(title, "center");

         pnlBody = new Panel();
         pnlBody.add(btnAnalogue);
         pnlBody.add(btnDigital);

         add(pnlHeader);
         add(pnlBody);
         // setting size in case of applet
         // setSize(600,500);

     }


     public void actionPerformed(ActionEvent ae)
     {
         if (ae.getSource() == btnAnalogue)
         {
             Choice = 1;
         }
         if (ae.getSource() == btnDigital)
         {
             Choice = 2;
         }
         thread.start();
     }


     public void paint(Graphics g)
     {
         hr = calendar.get(Calendar.HOUR);
         min = calendar.get(Calendar.MINUTE);
         sec = calendar.get(Calendar.SECOND);
         if (Choice == 2)
         {
             g.setColor(new Color(255, 0, 0));
             g.fillRect(290, 180, 35, 30);
             g.fillRect(335, 180, 35, 30);
             g.fillRect(380, 180, 35, 30);
             g.setColor(new Color(255, 255, 255));
             g.drawString(" " + min + ":", 340, 200);
             g.drawString(" " + hr + ":", 300, 200);
             g.drawString(" " + sec, 385, 200);
         }


         if (Choice == 1)
         {
             g.setColor(new Color(0, 0, 0));

             g.fillOval(200, 150, 200, 200);

             int cx = 300;                       //  locating x & y of centre of circle
             int cy = 250;


             for (int i = 0; i < 60; i++)
             {
                 Double adjust = 6 * i * Math.PI / 180;
                 int R = 100;
                 double xx = R * Math.sin(adjust) + cx;
                 double yy = cy - (R * Math.cos(adjust));
                 int x1 = (int) xx;
                 int y1 = (int) yy;

                 R = 95;
                 if (i % 5 == 0)
                 {
                     R = 85;
                 }
                 xx = R * Math.sin(adjust) + cx;
                 yy = cy - (R * Math.cos(adjust));
                 int x = (int) xx;
                 int y = (int) yy;
                 g.setColor(Color.white);
                 g.drawLine(x1, y1, x, y);
             }


             g.setColor(new Color(255, 0, 0));
//  *****************  seconds

             Double ang = 6 * sec * Math.PI / 180;
             int r = 80;
             double xx = r * Math.sin(ang) + cx;
             double yy = cy - (r * Math.cos(ang));
             int x = (int) xx;
             int y = (int) yy;
             g.drawLine(cx, cy, x, y);


             g.setColor(new Color(255, 255, 255));
//  *****************  mins

             ang = 6 * min * Math.PI / 180;
             r = 70;
             xx = r * Math.sin(ang) + cx;
             yy = cy - (r * Math.cos(ang));
             x = (int) xx;
             y = (int) yy;
             g.drawLine(cx, cy, x, y);


             g.setColor(new Color(255, 255, 255));
//  *****************  hours

             ang = 6 * (hr * 5 + min / 12) * Math.PI / 180;
             r = 50;
             xx = r * Math.sin(ang) + cx;
             yy = cy - (r * Math.cos(ang));
             x = (int) xx;
             y = (int) yy;
             g.drawLine(cx, cy, x, y);


         }
     }


     public void run()
     {
         while (true)
         {
             calendar = new GregorianCalendar();
             repaint();
             try
             {
                 thread.sleep(500);
             } catch (Exception e)
             {
             }

         }
     }


 }