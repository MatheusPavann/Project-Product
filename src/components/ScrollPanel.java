package components;

import entities.Product;

import javax.swing.*;
import java.awt.*;

public class ScrollPanel extends JScrollPane {
    private JPanel contentPanel;

    public ScrollPanel() {
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);

        setViewportView(contentPanel);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        getViewport().setBackground(Color.GRAY);
    }

    public void addCard(Product product) {
        if (contentPanel.getComponentCount() > 0 &&
                contentPanel.getComponent(contentPanel.getComponentCount()-1) instanceof Box.Filler) {
            contentPanel.remove(contentPanel.getComponentCount()-1);
        }

        Card card = new Card(product);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = contentPanel.getComponentCount();
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(5, 15, 5, 15);

        contentPanel.add(card, gbc);

        GridBagConstraints glueGbc = new GridBagConstraints();
        glueGbc.gridx = 0;
        glueGbc.gridy = contentPanel.getComponentCount();
        glueGbc.weightx = 1;
        glueGbc.weighty = 1;
        glueGbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(Box.createVerticalGlue(), glueGbc);

        revalidate();
        repaint();
    }

}

