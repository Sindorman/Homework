
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

interface Shape {

    // this method should render your Shape. If the shape is in the "hot" state,
    // meaning the mouse is over the shape, it should render fully opaque, with
    // the color specified by setColor. If the shape is not in the hot state, it
    // should render a white background (fill) and just a border in the color specified
    // by setColor.
    public void render(Graphics g);

    // determine if the given point is within the shape. Keep track of if the
    // shape is currently hit tested or not, and call listener.OnHitTestChange()
    // whenever the state changes. You will need this state to determine if it should be
    // rendered in the "hot" state or not.
    public boolean hitTest(Point pt, HitTestChangeListener listener);

    // allow the outline color to be set (or fill color while in the "hot" state).
    public void setColor(Color color);

    // calculate the area of the shape.
    public double getArea();

    // get/set the position of the shape. This point can be anywhere on (or even off)
    // the shape that you want, but you must be consistent and accept a change to
    // setPosition().
    public Point getPosition();

    public void setPosition(Point point);
}

// The ShapeFactory will create shapes of the specified type, sized reasonably for the given
// panel with and height
class ShapeFactory {
    private enum Shapes {
        RECTANGLE,
        SQUARE,
        CIRCLE,
        OVAL,
        TRIANGLE,		
        HEXAGON,   
        OCTAGON };

    public static String[] getSupportedShapes() {
        // have the enumeration provide strings for each enum value
        String[] ret = new String[Shapes.values().length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = Shapes.values()[i].toString();
        }
        return ret;
    }

    public static Shape createShape(String shapeString, int panelWidth, int panelHeight) {
        Shapes shape = Shapes.valueOf(shapeString);        
        Random random = new Random();
        if (shapeString.equals("RECTANGLE")){
        	Rectangle test = new Rectangle(random.nextInt(350)+20, random.nextInt(350)+20, random.nextInt(100)+8, random.nextInt(100)+8);
        	return test;
        }else if (shapeString.equals("SQUARE")){
        	int side = random.nextInt(100)+8;
        	Rectangle test = new Square(random.nextInt(350)+20, random.nextInt(350)+20, side , side);
        	return test;
        }else if (shapeString.equals("OVAL")){
        	Oval test = new Oval(random.nextInt(350)+20, random.nextInt(350)+20, random.nextInt(100)+8, random.nextInt(100)+8);
        	return test;
        }else if (shapeString.equals("CIRCLE")){
        	Oval test = new Circle(random.nextInt(350)+20, random.nextInt(350)+20, random.nextInt(100)+8, random.nextInt(100)+8);
        	return test;
        }else if (shapeString.equals("TRIANGLE")){
        	int size = random.nextInt(4)+1;
        	Point one = new Point(random.nextInt(350)+20, random.nextInt(350)+20);
        	Triangle test = new Triangle(one, size);
        	return test;
        }else if (shapeString.equals("HEXAGON")){
        	int size = random.nextInt(4)+1;
        	Point one = new Point(random.nextInt(350)+20, random.nextInt(350)+20);
        	Hexagon test = new Hexagon(one, size);
        	return test;
        }else if (shapeString.equals("OCTAGON")){
        	int size = random.nextInt(4)+1;
        	Point one = new Point(random.nextInt(350)+20, random.nextInt(350)+20);
        	Octagon test = new Octagon(one, size);
        	return test;
        }
        
       
        // TODO: Implement this method, to create the specified shape. No shape should be larger
        // than 25% of the panel, and should be initially positioned at least partially on screen.

        return null;
    }
}


//This class will be created on mouse down to keep track of the selected shape, the mouse
//down point, and the original location of the selected shape. On subsequent mouse drag events
//we will move the selected shape appropriately.
class DragData {
    private Point mouseDragStart;
    private Point shapeStart;
    private Shape shape;

    public DragData(Point mouseDragStart, Shape shape) {
        this.mouseDragStart = mouseDragStart;
        this.shapeStart = shape.getPosition();
        this.shape = shape;
    }

    public void handleMouseDrag(MouseEvent e) {
        int deltaX = e.getPoint().x - mouseDragStart.x; 
        int deltaY = e.getPoint().y - mouseDragStart.y;
        shape.setPosition(new Point(shapeStart.x + deltaX, shapeStart.y + deltaY));
    }
}

// callback interface so that the drawing panel can know to repaint if a shape used to be
// hit and is no longer hit, or vice-versa.
interface HitTestChangeListener {
    public void OnHitTestChange();
}

// Abstract class for your drawing area, you need to extend this and implement
// all the abstract methods to work with an ArrayList of Shape objects
abstract class DrawingAreaBase extends JPanel implements HitTestChangeListener {
    private DragData dragData = null;
    private Color colorSelection = Color.BLACK;

    // compute the cummulative area of all shapes
    abstract public double getTotalShapeArea();

    // render all shapes
    abstract public void renderShapes(Graphics g);

    // find the first shape hit by the given point
    abstract public Shape findFirstShapeHit(Point point);

    // handle a click event on the current shape as per functional requirements
    abstract public void handleShapeClicked(Shape shape);

    // perform hit testing on all shapes
    abstract public void hitTestAllShapes(Point point);

    // add a shape to the drawing area
    abstract public void addShape(Shape s);

    // remove all shapes from the drawing area
    abstract public void removeAllShapes();

    public DrawingAreaBase() {
        setPreferredSize(new Dimension(500, 500));

        enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }

    public void refreshBackgroundColorByShapeArea() {
        double shapeArea = getTotalShapeArea();
        double totalArea = getWidth() * getHeight();
        int channelColor = Math.max(0,  255 - (int)(shapeArea * 255.0 / totalArea));
        setBackground(new Color(channelColor, channelColor, channelColor));
    }

    public void setColorSelection(Color color) { colorSelection = color; }
    public Color getColorSelection() { return colorSelection; }

    @Override public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Ask parent to paint background.
        renderShapes(g);
    }

    @Override public void processMouseMotionEvent(MouseEvent e) {
        switch (e.getID()) {
        case MouseEvent.MOUSE_MOVED:
            hitTestAllShapes(e.getPoint());
            break;
        case MouseEvent.MOUSE_DRAGGED:
            if (dragData != null) {
                dragData.handleMouseDrag(e);
                repaint();
            }
            break;
        }
    }

    @Override public void processMouseEvent(MouseEvent e) {
        Shape firstShape = null;
        switch (e.getID()) {
        case MouseEvent.MOUSE_CLICKED:
            firstShape = findFirstShapeHit(e.getPoint());
            if (firstShape != null) {
                handleShapeClicked(firstShape);
                firstShape.setColor(colorSelection);
                this.repaint();
            }						
            break;
        case MouseEvent.MOUSE_PRESSED:
            firstShape = findFirstShapeHit(e.getPoint());
            if (firstShape != null) {
                dragData = new DragData(e.getPoint(), firstShape);
            }
            break;
        case MouseEvent.MOUSE_RELEASED:
            dragData = null;
            break;
        }
    }

    public void OnHitTestChange() {
        repaint();
    }
}

public class ShapePanel extends JFrame {

    private DrawingArea panel;
    private ShapeFactory factory;

    public ShapePanel() {
        panel = new DrawingArea();
        panel.setBackground(Color.white);

        factory = new ShapeFactory();

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // for all supported shapes, add a button to add a new instance of that shape
        JPanel buttons = new JPanel();
        content.add(buttons);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));       
        for (final String shapeType : factory.getSupportedShapes()) {        	
            JButton button = new JButton("New " + shapeType.toLowerCase());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Shape s = factory.createShape(shapeType, panel.getWidth(), panel.getHeight());
                    if (s.getArea() > panel.getWidth() * panel.getHeight() * 0.25) {
                        throw new RuntimeException("Shape should not be larger than 25% of panel");
                    }
                    s.setColor(panel.getColorSelection());
                    panel.addShape(s);
                    panel.refreshBackgroundColorByShapeArea();
                }
            });
            buttons.add(button);
        }

        JButton buttonClear = new JButton("Clear");
        buttonClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {                	
                panel.removeAllShapes();
                panel.refreshBackgroundColorByShapeArea();
            }
        });
        buttons.add(buttonClear);

        // TODO (Bonus) - create a color toolbar that will allow the user to change the color of the next clicked on shape
       /* Color[] colors = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE, Color.YELLOW, Color.PINK};
        JPanel Colors = new JPanel();
        //Colors.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JPanel ColorBut = new JPanel();
        content.add(ColorBut);
        ColorBut.setLayout(new BoxLayout(ColorBut, BoxLayout.X_AXIS));        
        for (int x = 0; x < colors.length; x ++){
        	
        	JButton button = new JButton(colors[x].toString() + "");
        	buttons.add(button);
        }*/
        content.add(panel);

        //... Set window characteristics
        setContentPane(content);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Shape Panel");
        setLocationRelativeTo(null);  // Center window.		
        pack();
    }

    public static void main(String[] args) {
        JFrame window = new ShapePanel();
        window.setVisible(true);
    }
}


