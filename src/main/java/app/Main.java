package app;


import javax.swing.JFrame;

/**
 * Main class to fetch and display player data.
 */
public class Main {

    /**
     * Main method to fetch and display player data based on user input.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addMenuView()
                .addTeamCompareView()
                .addTeamCompareSuccessView()
                .addTeamCompareUseCase()
                .addPlayerCompareView()
                .addPlayerCompareSuccessView()
                .addPlayerCompareUseCase()
                .addPlayerView()
                .addPlayerUseCase()                
                .addTeamView()
                .addMenuUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}