import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrowMazeFrame extends JFrame implements ActionListener
{
    private ArrowMazePanel mazePanel;
    private JButton resetButton, executeButton;


    public ArrowMazeFrame()
    {
        super("ArrowMaze");
        setSize((ArrowCell.LEFT_MARGIN*2+ArrowCell.CELL_SIZE*ArrowMazePanel.NUM_COLS),
                ArrowCell.TOP_MARGIN*2+ArrowCell.CELL_SIZE*ArrowMazePanel.NUM_ROWS+80);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        mazePanel = new ArrowMazePanel();
        getContentPane().add(mazePanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        resetButton = new JButton("Reset");
        executeButton =new JButton("Execute");
        resetButton.addActionListener(this);
        executeButton.addActionListener(this);
        bottomPanel.add(resetButton);
        bottomPanel.add(executeButton);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent aEvt)
    {
        if (aEvt.getSource()==resetButton)
        {
            System.out.println("Reset pressed");
            mazePanel.resetField();
        }
        if (aEvt.getSource()==executeButton)
        {
            System.out.println("Execute pressed");
            mazePanel.execute();
        }
    }
}
