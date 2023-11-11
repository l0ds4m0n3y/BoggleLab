import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class PanelForBoard extends JPanel{
    private JLabel[][] boardCells;
    private final int WIDTH = 300;
    
    PanelForBoard(String[][] board){
      boardCells = new JLabel[board.length][board[0].length];
      setPreferredSize(new Dimension(WIDTH, WIDTH));
      setSize(getPreferredSize());
      setBackground(Color.WHITE);
      setLayout(new GridLayout(board.length, board.length));
      
      setBorder(new LineBorder(Color.black, 20));
      
      for(int i = 0; i < board.length; i++){
        for(int j = 0; j < board[0].length; j++){
          boardCells[i][j] = new JLabel();
          boardCells[i][j].setText(board[i][j]);
          boardCells[i][j].setBorder(new LineBorder(Color.black, 5));
          boardCells[i][j].setHorizontalAlignment(JLabel.CENTER);
          boardCells[i][j].setFont(new Font("Consolas", Font.BOLD, 50));
          add(boardCells[i][j]);
        }
      }

    }
  }