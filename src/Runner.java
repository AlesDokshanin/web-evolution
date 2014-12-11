import web.Web;
import web.WebPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Runner {
    private JFrame frame;
    private JPanel controlsPanel = new JPanel();
    private WebPanel webPanel = new WebPanel();
    private JButton btnGenerate = new JButton("Generate");
    private JTextField tfEfficiency = new JTextField();
    private JCheckBox cbDrawFlies = new JCheckBox("Draw flies?", false);
    private JSpinner sidesCountSpinner = new JSpinner();
    private JLabel sidesCountLabel = new JLabel("Sides count:");
    private JLabel efficiencyLabel = new JLabel("Efficiency:");


    public Runner() {
        createAndShowUI();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    System.err.print(e.toString());
                }
                new Runner();

            }
        });
    }

    private void createAndShowUI() {
        setUpFrame();

        setUpControlsPanel();
        addListeners();
        addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void setUpControlsPanel() {
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.X_AXIS));
        controlsPanel.add(btnGenerate);
        controlsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        controlsPanel.add(sidesCountLabel);
        controlsPanel.add(sidesCountSpinner);
        controlsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        controlsPanel.add(efficiencyLabel);
        controlsPanel.add(tfEfficiency);
        controlsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        controlsPanel.add(cbDrawFlies);

        tfEfficiency.setEditable(false);
        tfEfficiency.setText(Double.toString(webPanel.getWebEfficiency()));
        sidesCountSpinner.setValue(Web.getSidesCount());
        sidesCountSpinner.setToolTipText("Sides count:");
    }

    private void setUpFrame() {
        frame = new JFrame("Web evolution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    private void addListeners() {
        btnGenerate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Web.setSidesCount((Integer) sidesCountSpinner.getValue());
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(frame, e.getMessage());
                }
                webPanel.resetWeb();
                tfEfficiency.setText(Double.toString(webPanel.getWebEfficiency()));
                frame.repaint();
            }
        });
        cbDrawFlies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                webPanel.toggleDrawFlies();
                frame.repaint();
            }
        });
        sidesCountSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                Integer value = (Integer) sidesCountSpinner.getValue();
                try {
                    Web.setSidesCount(value);
                } catch (IllegalArgumentException e) {
                    sidesCountSpinner.setValue(Web.getSidesCount());
                }
            }
        });
    }

    private void addComponentsToPane(Container pane) {
        pane.add(controlsPanel, BorderLayout.PAGE_START);
        pane.add(webPanel, BorderLayout.CENTER);
    }
}