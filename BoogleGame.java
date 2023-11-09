import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class BoogleGame extends JFrame{
  private final int HEIGHT = 500;
  private final int WIDTH = 750;
  private final int BUTTON_WIDTH = 75;
  private JPanel boardPanel;
  private JPanel topPanel;
  private JButton submitButton = new JButton("Submit");
  private JProgressBar wordsBar;
  private JTextField inputField;
  private JTextField wordsSubmittedField;

  BoogleGame(){
    super("Boogle!");
    setLayout(new BorderLayout(5,5));

    //inputField
    inputField = new JTextField();
    inputField.setPreferredSize(new Dimension(WIDTH - BUTTON_WIDTH - 30, 30));
    inputField.setSize(getPreferredSize());

    //SubmitButton
    submitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, 30));
    submitButton.setSize(getPreferredSize());

    //Top Panel
    topPanel = new JPanel();
    topPanel.setPreferredSize(new Dimension(WIDTH, 35));
    topPanel.setSize(getPreferredSize());
    topPanel.add(inputField);
    topPanel.add(submitButton);

    //BoardPanel
    boardPanel = new JPanel();

    //words submitted field
    wordsSubmittedField = new JTextField();
    wordsSubmittedField.setPreferredSize(new Dimension(125, HEIGHT));

    //progressbar
    wordsBar = new JProgressBar();
    wordsBar.setPreferredSize(new Dimension(WIDTH, 30));

    //Add all components
    add(topPanel, BorderLayout.NORTH);
    add(boardPanel, BorderLayout.CENTER);
    add(wordsSubmittedField, BorderLayout.EAST);
    add(wordsBar, BorderLayout.SOUTH);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setSize(getPreferredSize());
    setVisible(true);
    //setResizable(false);
  }
}
