import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ArrowMazePanel extends JPanel
{
    public ArrowCell[][] myGrid;

    public static final int NUM_ROWS = 25;
    public static final int NUM_COLS = 25;
    public ArrowMazePanel()
    {
        super();
        myGrid = new ArrowCell[NUM_ROWS][NUM_COLS];
        resetField();

    }

    /**
     * recreates the NUM_ROWS x NUM_COLS field with randomized Cells
     */
    public void resetField()
    {
        for (int r=0; r<NUM_ROWS; r++)
            for (int c=0; c<NUM_COLS; c++)
                myGrid[r][c] = new ArrowCell(r,c);
        repaint();
    }

    /**
     * refreshes the screen by telling all the cells to draw themselves.
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g)
    {
        for (int r=0; r<NUM_ROWS; r++)
            for (int c=0; c<NUM_COLS; c++)
                myGrid[r][c].drawSelf(g);
    }

    /**
     * determines whether the coloring process has finished.
     * @return false if any of the ArrowCells are still black, true otherwise.
     */
    public boolean noBlackCellsRemain()
    {
        for (int r=0; r<NUM_ROWS; r++)
            for (int c=0; c<NUM_COLS; c++)
                if (myGrid[r][c].getMyColor() == Color.BLACK)
                    return false;
        return true;
    }

    /**
     * gets the cell that is pointed to by the given cell, or null if it is pointing out of bounds.
     * @param cell - the cell that is doing the pointing
     * @return - the cell that is pointed at, or null if cell pointed out of bounds.
     */
    public ArrowCell targetOfArrowCell(ArrowCell cell)
    {
        switch (cell.getDirection())
        {
            case ArrowCell.RIGHT:
                if (cell.getMyCol() == NUM_COLS-1)
                    return null;
                return myGrid[cell.getMyRow()][cell.getMyCol()+1];
            case ArrowCell.LEFT:
                if (cell.getMyCol() == 0)
                    return null;
                return myGrid[cell.getMyRow()][cell.getMyCol()-1];
            case ArrowCell.DOWN:
                if (cell.getMyRow() == NUM_ROWS-1)
                    return null;
                return myGrid[cell.getMyRow()+1][cell.getMyCol()];
            case ArrowCell.UP:
                if (cell.getMyRow() == 0)
                    return null;
                return myGrid[cell.getMyRow()-1][cell.getMyCol()];

            default:
                return null;
        }

    }

    /**
     * tells all the ArrowCells in the given list to take on the given color
     * @param c - the color to change to
     * @param path - an ArrayList of ArrowCells
     */
    public void setColorForPath(Color c, ArrayList<ArrowCell> path)
    {
        for (ArrowCell cell:path)
        {
            cell.setMyColor(c);
        }
        repaint();
    }

    /**
     * If the cell at the given location is black...
     * Starting at the given location, trace a path until this path either:
     * a) goes out of bounds
     * b) points to a member of its own path
     * c) points to another, non-black cell
     * In the case of (a) or (b) pick a random color for all the cells in this path
     * In the case of (c), set all the cells in the path to the color to which we just ran into
     * @param row - row of start cell
     * @param col - col of start cell
     */
    public void colorPathStartingAt(int row, int col)
    {
        ArrowCell startCell = myGrid[row][col];
        if (startCell.getMyColor() != Color.BLACK)
            return;
        ArrayList<ArrowCell> path = new ArrayList<>();
        path.add(startCell);
        while(true)
        {
            ArrowCell nextCell = targetOfArrowCell(path.get(path.size()-1));
            if (nextCell == null || path.contains(nextCell))
            {
                if (nextCell != null)
                    path.add(nextCell);
                setColorForPath(new Color((int)(Math.random()*128)+64,
                                (int)(Math.random()*128)+64,
                                (int)(Math.random()*128)+64),
                        path);
                return;
            }
            if (nextCell.getMyColor() != Color.BLACK)
            {
                setColorForPath(nextCell.getMyColor(), path);
                return;
            }
            path.add(nextCell);
        }
    }

    /**
     * trace paths until all ArrowCells are colored other than black.
     */
    public void execute()
    {
        for (int cellNum = 0; cellNum<NUM_ROWS*NUM_COLS; cellNum++)
            colorPathStartingAt(cellNum/NUM_COLS, cellNum%NUM_COLS);

    }
}
