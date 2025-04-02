package components;

import utils.ColorScheme;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    public Label(String text) {
        super(text);
        this.setFont(new Font("SansSerif", Font.BOLD, 12));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setForeground(ColorScheme.TEXT_COLOR);
    }
}
