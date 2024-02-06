import javax.swing.*;
import java.awt.*;

public class ArrowMazePanel extends JPanel
{
    public ArrowCell[][] myGrid;

    public static final int NUM_ROWS = 15;
    public static final int NUM_COLS = 15;
    public ArrowMazePanel()
    {
        super();
        myGrid = new ArrowCell[NUM_ROWS][NUM_COLS];
        resetField();

    }

    public void resetField()
    {
        for (int r=0; r<NUM_ROWS; r++)
            for (int c=0; c<NUM_COLS; c++)
                myGrid[r][c] = new ArrowCell(r,c);
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        for (int r=0; r<NUM_ROWS; r++)
            for (int c=0; c<NUM_COLS; c++)
                myGrid[r][c].drawSelf(g);
    }
}
