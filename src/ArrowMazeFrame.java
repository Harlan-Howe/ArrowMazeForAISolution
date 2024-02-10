import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrowMazeFrame extends JFrame implements ActionListener
{
    private ArrowMazePanel mazePanel;
    private JButton resetButton, find1PathButton, findAllPathsButton;
    private JLabel statusLabel;


    public ArrowMazeFrame() {
        super("ArrowMaze");
        setSize((ArrowCell.LEFT_MARGIN * 2 + ArrowCell.CELL_SIZE * ArrowMazePanel.NUM_COLS),
                ArrowCell.TOP_MARGIN * 2 + ArrowCell.CELL_SIZE * ArrowMazePanel.NUM_ROWS + 80);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // add the maze panel to the center
        mazePanel = new ArrowMazePanel();
        getContentPane().add(mazePanel, BorderLayout.CENTER);

        // now layout the bottom panel with the buttons
        Box bottomPanel = Box.createVerticalBox();
        JPanel bottomButtonPanel = new JPanel();
        resetButton = new JButton("Reset");
        find1PathButton = new JButton("Find1Path");
        findAllPathsButton = new JButton("Find All Paths");
        resetButton.addActionListener(this);
        find1PathButton.addActionListener(this);
        findAllPathsButton.addActionListener(this);
        bottomButtonPanel.add(resetButton);
        bottomButtonPanel.add(find1PathButton);
        bottomButtonPanel.add(findAllPathsButton);

        statusLabel = new JLabel("Started.");
        bottomPanel.add(bottomButtonPanel);
        bottomPanel.add(statusLabel);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent aEvt)
    {
        if (aEvt.getSource()==resetButton)
        {
            System.out.println("Reset pressed");
            mazePanel.resetField();
            statusLabel.setText("Reset.");
        }
        if (aEvt.getSource()==find1PathButton)
        {
            System.out.println("Find 1 path pressed.");
            mazePanel.find1Path();
            statusLabel.setText((int)(100*mazePanel.getPercentageNonBlack())+"% colored");
        }
        if (aEvt.getSource()== findAllPathsButton)
        {
            System.out.println("Execute pressed");
            mazePanel.findAllPaths();
            if (mazePanel.noBlackCellsRemain())
                statusLabel.setText("Confirmed all cells colored.");
            else
                statusLabel.setText("Error. Not all cells colored.");
        }
    }
}
