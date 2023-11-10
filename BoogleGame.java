import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BoogleGame extends JFrame implements ActionListener {
  private final int HEIGHT = 500;
  private final int WIDTH = 750;
  private final int BUTTON_WIDTH = 75;
  private JPanel boardPanel;
  private JPanel topPanel;
  private JButton submitButton = new JButton("Submit");
  private JProgressBar wordsBar;
  private JTextField inputField;
  private JTextArea wordsSubmittedArea;
  private JScrollPane scrollableArea; 
  private BoggleBoard board;
  private String[] messages = {"First blood", "nice!", "double kill", "triple kill", "Quadra kill", "PENTAKILL", "Killing spree", "Rampage", "Dominating", "Unstoppable", "Godlike", "Legendary"};
  private int messagePtr = 0;
  private Color[] colorArray = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.BLUE, new Color(238,130,238)};
  private int colorPtr = 0;

  BoogleGame() {
    super("Boogle!");
    setLayout(new BorderLayout(5, 5));
    board = new BoggleBoard();

    for(String s : board.getPossibleWords()){
            System.out.println(s);
        }

    // inputField
    inputField = new JTextField();
    inputField.setPreferredSize(new Dimension(WIDTH - BUTTON_WIDTH - 30, 30));
    inputField.setSize(getPreferredSize());
    inputField.addActionListener(this);

    // SubmitButton
    submitButton.setPreferredSize(new Dimension(BUTTON_WIDTH, 30));
    submitButton.setSize(getPreferredSize());
    submitButton.addActionListener(this);

    // Top Panel
    topPanel = new JPanel();
    topPanel.setPreferredSize(new Dimension(WIDTH, 35));
    topPanel.setSize(getPreferredSize());
    topPanel.add(inputField);
    topPanel.add(submitButton);

    // BoardPanel //TODO make it visible
    boardPanel = new JPanel();

    // words submitted field
    wordsSubmittedArea = new JTextArea();
    wordsSubmittedArea.setPreferredSize(new Dimension(125, HEIGHT));
    wordsSubmittedArea.setEditable(false);
    scrollableArea = new JScrollPane(wordsSubmittedArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    // progressbar
    wordsBar = new JProgressBar(0, board.getPossibleWords().size());
    wordsBar.setValue(0);
    wordsBar.setPreferredSize(new Dimension(WIDTH, 30));
    wordsBar.setFont(new Font("Consolas", Font.BOLD, 15));

    // Add all components
    add(topPanel, BorderLayout.NORTH);
    add(boardPanel, BorderLayout.CENTER);
    add(scrollableArea, BorderLayout.EAST);
    add(wordsBar, BorderLayout.SOUTH);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setSize(getPreferredSize());
    setVisible(true);
    setResizable(false);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == inputField) {

      String answer = inputField.getText().trim();
      wordsBar.setStringPainted(true);

      if (board.hasWord(answer)) {
        wordsSubmittedArea.append(answer + "\n");
        wordsBar.setValue(wordsBar.getValue() + 1);

        
        if(messagePtr == messages.length) 
          messagePtr--;
        
        wordsBar.setString(messages[messagePtr]);
        
        if(messagePtr == 0) 
          messagePtr = 1;

        if(colorPtr == colorArray.length) 
          colorPtr = 0;

        wordsBar.setForeground(colorArray[colorPtr]);
        
        messagePtr++;
        colorPtr++;

      } else if (board.isInDictionary(answer)) {

        wordsBar.setString("Valid but no");
        if(messagePtr != 0) 
          messagePtr = 1;

      } else {

        wordsBar.setString("not a word");
        if(messagePtr != 0)   
          messagePtr = 1;

      }

      inputField.setText("");
      if(wordsBar.getValue() == wordsBar.getMaximum()){
        wordsBar.setString("ACE");
      }

    } else if (e.getSource() == submitButton) {

        wordsSubmittedArea.append("-MISSED WORDS-\n");
        for (String s : board.getPossibleWords()) {
          wordsSubmittedArea.append(s + "\n");
        }

    }
  }
}
