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

public class BoggleGameFrame extends JFrame implements ActionListener {
  private final int HEIGHT = 500;
  private final int WIDTH = 500;
  private final int BUTTON_WIDTH = 75;
  private JPanel boardPanel;
  private JPanel topPanel;
  private JButton SolveButton = new JButton("Solve");
  private JButton newGameButton = new JButton("New");
  private JProgressBar wordsBar;
  private JTextField inputField;
  private JTextArea wordsSubmittedArea;
  private JScrollPane scrollableArea; 
  private BoggleBoard board;
  private String[] messages = {"First blood", "nice!", "double kill", "triple kill", "Quadra kill", "PENTAKILL", "Killing spree", "Rampage", "Dominating", "Unstoppable", "Godlike", "Legendary"};
  private int messagePtr = 0;
  private Color[] colorArray = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.BLUE, new Color(238,130,238)};
  private int colorPtr = 0;
  private PanelForBoard panelGraphic;

  BoggleGameFrame() {
    super("Boogle!");
    setLayout(new BorderLayout(5, 5));
    board = new BoggleBoard();

    // for(String s : board.getPossibleWords()){
    //         System.out.println(s);
    //     }

    // inputField
    inputField = new JTextField();
    inputField.setPreferredSize(new Dimension(WIDTH - BUTTON_WIDTH - 30, 30));
    inputField.setSize(getPreferredSize());
    inputField.addActionListener(this);

    // SubmitButton
    SolveButton.setPreferredSize(new Dimension(BUTTON_WIDTH, 30));
    SolveButton.setSize(getPreferredSize());
    SolveButton.addActionListener(this);

    //newGameBUtton
    newGameButton.setPreferredSize(new Dimension(BUTTON_WIDTH, 30));
    newGameButton.setSize(getPreferredSize());
    newGameButton.addActionListener(this);

    // Top Panel
    topPanel = new JPanel();
    topPanel.setPreferredSize(new Dimension(WIDTH, 35));
    topPanel.setSize(getPreferredSize());
    topPanel.add(inputField);
    topPanel.add(SolveButton);

    // BoardPanel
    panelGraphic = new PanelForBoard(board.getBoard());
    boardPanel = new JPanel();
    boardPanel.setLayout(new BorderLayout(10,10));
    boardPanel.setBackground(Color.BLACK);
    boardPanel.add(panelGraphic, BorderLayout.CENTER);

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
    //setLocationRelativeTo(null);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    setSize(getPreferredSize());
    setVisible(true);
    setResizable(false);
  }

  
  @Override
  public void actionPerformed(ActionEvent e) {
    //inputField
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

        wordsBar.setString("Valid word but not in board/was answered");
        if(messagePtr != 0) 
          messagePtr = 1;

      } else if (!answer.equals(null)){

        wordsBar.setString("not a word");
        if(messagePtr != 0)   
          messagePtr = 1;

      }

      inputField.setText("");
      if(wordsBar.getValue() == wordsBar.getMaximum()){
        wordsBar.setString("ACE");
      }

    // submitButton
    } else if (e.getSource() == SolveButton) {

        wordsSubmittedArea.append("-POSSIBLE WORDS-\n");
        for (String s : board.getPossibleWords()) {
          wordsSubmittedArea.append(s + "\n");
        }

        topPanel.remove(SolveButton);
        topPanel.add(newGameButton);
        revalidate();
        repaint();
      
    } else if (e.getSource() == newGameButton){
        board = new BoggleBoard(4);
        wordsBar.setValue(0);
        wordsBar.setMaximum(board.getPossibleWords().size());
        wordsSubmittedArea.setText("");
        topPanel.remove(newGameButton);
        topPanel.add(SolveButton);
        panelGraphic.setBoard(board.getBoard());
        panelGraphic.paint();
        boardPanel.revalidate();
        boardPanel.repaint();
    }
  }
}
