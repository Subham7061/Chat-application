
//  package chatting.application;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class App implements ActionListener {
  JTextField text;
  static JPanel p2;
  static Box vertical = Box.createVerticalBox();/* it is created to make the msg vertically align */
  static JFrame f = new JFrame();
  static DataOutputStream messageSend;

  App() {
    /* make a panel which is act like a header */
    f.setLayout(null);
    JPanel p1 = new JPanel();/* panel is for making header and we are amking object of panel */
    p1.setBackground(new Color(7, 94, 84));/* header background */
    p1.setBounds(0, 0, 400, 70);/* header background color */
    p1.setLayout(null);
    f.add(p1);

    // /*adding backbutton image in the panel by making object */
    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("image/3.png"));
    Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel back = new JLabel(i3);
    back.setBounds(5, 25, 20, 20);
    p1.add(back);
    /* click on the back button to go back */
    back.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent ae) {
        System.exit(0);
      }
    });
    /* adding nect image */
    ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("image/1.png"));
    Image img2 = img1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
    ImageIcon img3 = new ImageIcon(img2);
    JLabel user1 = new JLabel(img3);
    user1.setBounds(40, 10, 50, 50);
    p1.add(user1);
    /* displaying name of user */
    JLabel name = new JLabel("subham yadav");
    name.setBounds(93, 22, 110, 13);
    name.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
    name.setForeground(Color.WHITE);
    p1.add(name);
    /* active status display */
    JLabel active = new JLabel("active now");
    active.setBounds(93, 33, 110, 25);
    active.setFont(new Font("SAN_SERIF", Font.BOLD, 10));
    active.setForeground(Color.WHITE);
    p1.add(active);

    /* videocall image */
    ImageIcon img4 = new ImageIcon(ClassLoader.getSystemResource("image/video.png"));
    Image img5 = img4.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
    ImageIcon img6 = new ImageIcon(img5);
    JLabel video = new JLabel(img6);
    video.setBounds(260, 10, 50, 50);
    p1.add(video);
    /* audiocall image adding */
    ImageIcon img7 = new ImageIcon(ClassLoader.getSystemResource("image/phone.png"));
    Image img8 = img7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
    ImageIcon img9 = new ImageIcon(img8);
    JLabel audio = new JLabel(img9);
    audio.setBounds(310, 10, 50, 50);
    p1.add(audio);
    /* three arrow botton image */
    ImageIcon img10 = new ImageIcon(ClassLoader.getSystemResource("image/3icon.png"));
    Image img11 = img10.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
    ImageIcon img12 = new ImageIcon(img11);
    JLabel threePoint = new JLabel(img12);
    threePoint.setBounds(370, 25, 5, 25);
    p1.add(threePoint);

    /* new pannel for chatting */
    p2 = new JPanel();
    p2.setBackground(new Color(245, 232, 228));
    p2.setBounds(5, 72, 390, 570);
    f.add(p2);

    /* writing msg area */
    text = new JTextField();
    text.setBounds(5, 650, 280, 36);
    text.setFont(new Font("SANS_SARIF", Font.BOLD, 15));
    f.add(text);
    /* send button making */
    JButton send = new JButton("Send");
    send.setBounds(290, 650, 100, 36);
    send.setBackground(new Color(7, 94, 84));
    send.setForeground(Color.WHITE);
    send.addActionListener(this);
    send.setFont(new Font("SANS_SARIF", Font.BOLD, 14));
    f.add(send);

    /* this is the main layout heigh and width */
    f.setSize(400, 690);
    f.setLocation(100, 50);
    f.setUndecorated(true);
    f.getContentPane().setBackground(Color.white);
    f.setVisible(true);

  }

  public void actionPerformed(ActionEvent ae) {
   
       try {String output = text.getText();

      JPanel p = textDecorate(output);

      p2.setLayout(new BorderLayout());

      JPanel right = new JPanel(new BorderLayout());
      right.add(p, BorderLayout.LINE_END);/* end of the panel */
      vertical.add(right);/* to show the msg panel on right */
      vertical.add(Box.createVerticalStrut(10));/* msg vertical space */
      p2.add(vertical, BorderLayout.PAGE_START);

      messageSend.writeUTF(output);
      text.setText("");
      f.validate();/* print msg on the msg panel */
      f.repaint();/* both are same to print msg */
      f.invalidate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /* function to decorate msg */
  public static JPanel textDecorate(String output) {
    JPanel newPanel = new JPanel();
    newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
    JLabel out = new JLabel("<html><p style=\"width:150px\">" + output + "</p></html>");
    out.setFont(new Font("SANS_SARIF", Font.BOLD, 14));
    out.setBackground(new Color(16, 44, 87));
    out.setForeground(Color.WHITE);
    out.setOpaque(true);
    out.setBorder(new EmptyBorder(15, 15, 15, 40));
    newPanel.add(out);
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
    JLabel time = new JLabel();
    time.setText(sdf.format(cal.getTime()));
    newPanel.add(time);
    return newPanel;
  }

  public static void main(String[] args) throws Exception {
    new App();

    try (ServerSocket skt = new ServerSocket(5000)) {
      while (true) {
        Socket s = skt.accept();
        DataInputStream messageReceive = new DataInputStream(s.getInputStream());
        messageSend = new DataOutputStream(s.getOutputStream());

        while (true) {
          /* protocol to read and write msg */
          String msg = messageReceive.readUTF();
          JPanel panel = textDecorate(msg);

          JPanel left = new JPanel(new BorderLayout());
          left.add(panel, BorderLayout.LINE_START);
          vertical.add(left);
          f.validate();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
