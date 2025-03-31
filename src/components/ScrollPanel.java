package components;

import entities.Product;

import javax.swing.*;
import java.awt.*;

public class ScrollPanel extends JScrollPane {
    private JPanel contentPanel;

    public ScrollPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        setViewportView(contentPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        getViewport().setBackground(Color.GRAY);
    }

    public void addCard(Product product) {
        Card card = new Card(product);
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(card);
        contentPanel.add(Box.createVerticalStrut(10));
        revalidate();
        repaint();
    }
}