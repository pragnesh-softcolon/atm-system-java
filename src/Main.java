import Frames.Home;
import Network.Database.Connection;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new Connection();
        SwingUtilities.invokeLater(() -> new Home(true));
    }
}