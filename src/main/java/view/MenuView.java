package view;

import interface_adapter.menu.MenuViewController;
import interface_adapter.team_compare.TeamCompareController;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**

 The MenuView class is responsible for creating the menu bar for the application.
 */
public class MenuView extends JPanel {
    private final String viewName = "menu view";
    private static final int TITLE_FONT_SIZE = 24;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private static final int IMAGE_WIDTH = 200; // Adjust the width as needed
    private static final int IMAGE_HEIGHT = 200; // Adjust the height as needed

    private final JPanel buttonPanel;
    private final JButton playerViewButton;
    private final JButton playerCompareViewButton;
    private final JButton teamViewButton;
    private final JButton teamCompareViewButton;
    private final JLabel titleLabel;
    private final JLabel imageLabel;

    private MenuViewController menuViewController;

    public MenuView() {
        buttonPanel = new JPanel(new BorderLayout());

        titleLabel = new JLabel("NBA Sports Statistics", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));

        // Load the image from memory
        BufferedImage nbaImage = null;
        try {
            nbaImage = ImageIO.read(new File("src/main/resources/nba_logo.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (nbaImage != null) {
            Image scaledImage = nbaImage.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
            ImageIcon nbaIcon = new ImageIcon(scaledImage);
            imageLabel = new JLabel(nbaIcon, SwingConstants.CENTER);
        } else {
            imageLabel = new JLabel("Image not found", SwingConstants.CENTER);
        }

        final JPanel buttonsPanel = new JPanel();
        playerViewButton = new JButton("Player");
        playerCompareViewButton = new JButton("Player Compare");
        teamViewButton = new JButton("Team");
        teamCompareViewButton = new JButton("Team Compare");

        buttonsPanel.add(playerViewButton);
        buttonsPanel.add(playerCompareViewButton);
        buttonsPanel.add(teamViewButton);
        buttonsPanel.add(teamCompareViewButton);

        buttonPanel.add(titleLabel, BorderLayout.NORTH);
        buttonPanel.add(imageLabel, BorderLayout.CENTER);
        buttonPanel.add(buttonsPanel, BorderLayout.SOUTH);

        teamCompareViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuViewController.switchToTeamCompareView();

            }
        });

        playerCompareViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuViewController.switchToPlayerCompareView();
            }
        });

        teamViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuViewController.switchToTeamView();
            }
        });

        this.add(buttonPanel);

        playerViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuViewController.switchToPlayerView();
            }
        });
    }

    /**
     Returns the button panel for the menu.
     @return the button panel
     */
    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    /**
     Adds an action listener to the player view button.
     @param listener the action listener
     */
    public void addPlayerViewListener(ActionListener listener) {
        playerViewButton.addActionListener(listener);
    }

    /**
     Adds an action listener to the player compare view button.
     @param listener the action listener
     */
    public void addPlayerCompareViewListener(ActionListener listener) {
        playerCompareViewButton.addActionListener(listener);
    }

    /**
     Adds an action listener to the team view button.
     @param listener the action listener
     */
    public void addTeamViewListener(ActionListener listener) {
        teamViewButton.addActionListener(listener);
    }

    /**
     Adds an action listener to the compare view button.
     @param listener the action listener
     */
    public void addCompareViewListener(ActionListener listener) {
        teamCompareViewButton.addActionListener(listener);
    }

    public String getViewName() {
        return viewName;
    }

    public void setMenuViewController(MenuViewController menuViewController) {
        this.menuViewController = menuViewController;
    }

    /**

     The main method to test the MenuView class.

     @param args the command line arguments
     */
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Menu View Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        final MenuView menuView = new MenuView();
        frame.add(menuView.getButtonPanel());

        frame.setVisible(true);

    }

}
