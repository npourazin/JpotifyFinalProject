package GUI;

import Controllers.ClickListenerForSearchButton;
import Controllers.ClickListenerForShowingSearchResults;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SearcBarPanel extends JPanel {
    private static JTextField searched;

    public SearcBarPanel(){
        this.setVisible(true);
        this.setLayout(new GridLayout(1, 3));

        JPanel tmp1 = new JPanel();
        tmp1.setVisible(true);
        tmp1.setLayout(new BorderLayout());

        JTextArea name = new JTextArea("Search: ");
        name.setEditable(false);
        name.setVisible(true);
        name.setBackground(Color.cyan);
        tmp1.add(name, BorderLayout.WEST);

        searched = new JTextField("     ");
        searched.setEditable(true);
        searched.setVisible(true);
        searched.setBackground(Color.white);
//        searched.setSize(200, 30);

//        this.setBorder(new EmptyBorder(2, 5, 2, 5));
        searched.setBorder(new EmptyBorder(10, 3,10,3));
        tmp1.add(searched);

        JPanel tmp2 = new JPanel();
        tmp2.setVisible(true);
        tmp2.setLayout(new FlowLayout());

        JButton confirmSearch = new JButton("Search!");
        confirmSearch.setVisible(true);
        confirmSearch.addActionListener(new ClickListenerForSearchButton());
        tmp2.add(confirmSearch);

        JButton showResults = new JButton("Show search results");
        //flag
        showResults.setVisible(true);
        showResults.addActionListener(new ClickListenerForShowingSearchResults());
        tmp2.add(showResults);

        this.add(tmp1);
        this.add(tmp2);



    }

    public static JTextField getSearched() {
        return searched;
    }

    public static void setSearched(JTextField searched) {
        SearcBarPanel.searched = searched;
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Graphics2D g2 = (Graphics2D) g.create();
        int w = this.getWidth();
        int h = this.getHeight();
        //this line
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
        g2.setPaint(new GradientPaint(0, 0, Color.cyan, 0, h, Color.pink));
        g2.fillRect(0, 0, w, h);
        g2.dispose();
    }
}
