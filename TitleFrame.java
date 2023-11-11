import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TitleFrame extends JFrame implements ActionListener{
    JLabel title = new JLabel("Boggle!");
    JButton newGameButton = new JButton("New Game");
    JButton solveGameButton = new JButton("Solve");
    
    TitleFrame(){    
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setSize(getPreferredSize());
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newGameButton){
            new BoggleGameFrame();
        }
        else{
            new SolveFrame();
        }
    }
}
