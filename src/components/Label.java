package components;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    public Label(String text) {
        super(text);
        this.setFont(new Font("Arial", Font.PLAIN, 12));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setOpaque(true);
    }
}
