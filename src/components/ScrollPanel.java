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

        JPanel cardWrapper = new JPanel(new BorderLayout());
        cardWrapper.setBackground(Color.WHITE);
        cardWrapper.add(card, BorderLayout.CENTER);
        cardWrapper.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        contentPanel.add(cardWrapper);
        contentPanel.add(Box.createVerticalStrut(10));

        revalidate();
        repaint();
    }


}
