import java.awt.*;

public class ArrowCell
{
    private int myRow, myCol;
    private int direction;
    private Color myColor;

    public final static int RIGHT = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 2;
    public final static int UP = 3;

    public final static int CELL_SIZE = 30;
    public final static int LEFT_MARGIN = 10;
    public final static int TOP_MARGIN = 10;

    private final static String[] arrows = {"←","↑","→","↓"};
    private static final Font arrowFont = new Font("Arial",Font.BOLD, CELL_SIZE-4);
    public ArrowCell(int myRow, int myCol) {
        this(myRow,myCol, (int)(Math.random()*4));
    }

    public ArrowCell(int myRow, int myCol, int direction)
    {
        this.myRow = myRow;
        this.myCol = myCol;
        this.direction = direction;
        this.myColor = Color.BLACK;
    }

    public int getMyRow() {
        return myRow;
    }

    public int getMyCol() {
        return myCol;
    }

    public int getDirection() {
        return direction;
    }

    public Color getMyColor() {
        return myColor;
    }

    public void setMyColor(Color c)
    {
        myColor = c;
    }
    @Override
    public String toString() {
        return "ArrowCell{" +
                "myRow=" + myRow +
                ", myCol=" + myCol +
                ", direction=" + direction + " " + arrows[direction] +
                ", myColor=" + myColor +
                '}';
    }

    public void drawSelf(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(LEFT_MARGIN + CELL_SIZE*myCol, TOP_MARGIN + CELL_SIZE*myRow, CELL_SIZE, CELL_SIZE)
        g.setColor(Color.BLACK);
        g.drawRect(LEFT_MARGIN + CELL_SIZE*myCol, TOP_MARGIN + CELL_SIZE*myRow, CELL_SIZE, CELL_SIZE)
        g.setColor(myColor);
        g.setFont(arrowFont);
        int arrowWidth = ((Graphics2D)g).getFontMetrics().stringWidth(arrows[direction]);
        g.drawString(arrows[direction],LEFT_MARGIN+CELL_SIZE*myCol+CELL_SIZE/2 -arrowWidth/2,
                TOP_MARGIN+CELL_SIZE*(myRow+1) - 2);
    }
}
