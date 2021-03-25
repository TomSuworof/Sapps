package WindowApps;

import java.awt.*;
import java.awt.event.*;

public class ResizeMe extends Frame {
    private final int increment = 25;
    private final int max = 500;
    private final int min = 200;
    private Dimension dimension;

    public ResizeMe() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                int width = (dimension.width + increment) > max ? min : (dimension.width + increment);
                int height = (dimension.height + increment) > max ? min : (dimension.height + increment);
                setSize(new Dimension(width, height));
            }
        });

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public void paint(Graphics graphics) {
        Insets insets = getInsets();
        dimension = getSize();

        graphics.drawLine(insets.left, insets.top, dimension.width - insets.right, dimension.height - insets.bottom);
        graphics.drawLine(insets.left, dimension.height - insets.bottom, dimension.width - insets.right, insets.top);
    }

    public static void main(String[] args) {
        ResizeMe resizeMe = new ResizeMe();
        resizeMe.setSize(new Dimension(200, 200));
        resizeMe.setTitle("ResizeMe");
        resizeMe.setVisible(true);
    }
}

