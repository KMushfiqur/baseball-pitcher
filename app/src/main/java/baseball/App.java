package baseball;

import javax.swing.*;
/**
 * The App class serves as the main entry point for the Baseball application.
 * It initializes the GUI by creating an instance of AppLogic and invoking its createAndShowGUI method.
 */
public class App {
 /**
     * The main method of the application this calls GUI by means of creation of AppLogic.
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AppLogic appLogic = new AppLogic();
                appLogic.createAndShowGUI();
            }
        });
    }
}
