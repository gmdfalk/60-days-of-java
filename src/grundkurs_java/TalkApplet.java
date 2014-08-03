package grundkurs_java;
/*
 * "Grundkurs Programmieren in Java (6. Auflage, 2011)"
 * 2003-2011, Carl Hanser Verlag
 * Loesungsvorschlag zu Aufgabe 20.3 (Version 2.0)
 * (c) 2003-2011 D. Ratz, J. Scheffler, D. Seese, J. Wiesenberger
 *
 */

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class TalkApplet extends JApplet implements Runnable {
  
  public static final int PORT = 7777;
  Container c;
  Socket socket;
  BufferedReader in;
  PrintWriter out;
  JTextField inputField;
  JTextArea outputArea;
  JScrollPane sp;
  String newline = "\n";
  boolean running;
  
  
  public void init() {
    c = getContentPane();
    FeldListener eingabe = new FeldListener();
    inputField = new JTextField();
    inputField.addActionListener(eingabe);
    outputArea = new JTextArea();
    outputArea.setFont( new Font("Courier", Font.PLAIN, 12));
    outputArea.setEditable(false);
    outputArea.setLineWrap(true);
    outputArea.setWrapStyleWord(true);
    sp = new JScrollPane(outputArea);
    
    c.setLayout(new BorderLayout());
    c.add(inputField, BorderLayout.SOUTH);
    c.add(sp, BorderLayout.CENTER);
    
    c.setBackground(Color.lightGray);
    c.setForeground(Color.black);
    inputField.setBackground(Color.white);
    outputArea.setBackground(Color.white);
    
    try {
      socket = new Socket(this.getCodeBase().getHost(), PORT);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(),true);
      say("Verbindung zum Server hergestellt!");
      say("Warte auf Partner!");
    }
    catch (IOException e) {
      say("Verbindung zum Server fehlgeschlagen!");
      System.exit(1);
    }
    running = true;
    new Thread(this).start();
  }
  
  public void destroy() {
    running = false;
    try {
      socket.close();
    }
    catch (IOException e) {
    }
  }
  
  public void run() {
    String line;
    try {
      while(running) {
        line = in.readLine();
        if(line!=null){
          outputArea.append(line+'\n' );
          outputArea.setCaretPosition(outputArea.getText().length());
        }
        try {
          Thread.sleep(10);
        }
        catch(InterruptedException e) {
          break;
        }
      }
    } catch (IOException e) { say("Verbindung zum Server abgebrochen"); }
  }
  
  class FeldListener implements ActionListener {
    public void actionPerformed(ActionEvent evt) {
      String text = inputField.getText();
      outputArea.append("<<< " + text + newline);
      outputArea.setCaretPosition(outputArea.getText().length());
      inputField.selectAll();
      out.println(">>> " + text);
    }
  }
  
  public void say(String msg) {
    outputArea.append("*** "+msg+" ***\n");
  }
}