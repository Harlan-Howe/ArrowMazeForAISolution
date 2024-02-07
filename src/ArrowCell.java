import java.awt.*;

public class ArrowCell
{
    private int myRow, myCol;
    private int direction;
    private Color myColor;

    // Define constants
    public final static int LEFT = 0;
    public final static int UP = 1;
    public final static int RIGHT = 2;
    public final static int DOWN = 3;

    public final static int CELL_SIZE = 24;
    public final static int LEFT_MARGIN = 10;
    public final static int TOP_MARGIN = 10;

    private final static String[] ARROWS = {"←","↑","→","↓"};
    private static final Font ARROW_FONT = new Font("Arial",Font.BOLD, CELL_SIZE-4);

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
                ", direction=" + direction + " " + ARROWS[direction] +
                ", myColor=" + myColor +
                '}';
    }

    public void drawSelf(Graphics g)
    {
        // background
        g.setColor(myColor);
        g.fillRect(LEFT_MARGIN + CELL_SIZE*myCol, TOP_MARGIN + CELL_SIZE*myRow, CELL_SIZE, CELL_SIZE);
        // border
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(LEFT_MARGIN + CELL_SIZE*myCol, TOP_MARGIN + CELL_SIZE*myRow, CELL_SIZE, CELL_SIZE);
        // arrow
        g.setColor(Color.WHITE);
        g.setFont(ARROW_FONT);
        int arrowWidth = g.getFontMetrics().stringWidth(ARROWS[direction]); // width of arrow string in
                                                                                          // this font
        g.drawString(ARROWS[direction],LEFT_MARGIN + CELL_SIZE * myCol + CELL_SIZE/2 - arrowWidth/2,
                TOP_MARGIN + CELL_SIZE * (myRow) + CELL_SIZE/2 + 6);
    }
}
