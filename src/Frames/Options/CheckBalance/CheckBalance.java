package Frames.Options.CheckBalance;

import Dimens.Dimens;
import Frames.Home;
import Frames.OptionsFrame;
import Network.Database.Connection;
import Network.LocalStore;
import Utils.Constants;
import Utils.Language.English;
import Utils.Language.Gujarati;
import Utils.Language.Hindi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.Map;

public class CheckBalance  extends Dimens {
    String accountInformation;
    JLabel label;
    JLabel backLabel;
    JLabel killLabel;
    JPanel panel;
    String back;
    String kill;
    Connection connection = new Connection();
    public CheckBalance(String language) {
        setTitle(Constants.APP_NAME);
        switch (language) {
            case Constants.ENGLISH: {
                accountInformation = "Your Account Balance is: ";
                back = English.BACK;
                kill = English.KILL;
                break;
            }
            case Constants.HINDI: {
                accountInformation = "आपका खाता शेष है: ";
                back = Hindi.BACK;
                kill = Hindi.KILL;
                break;
            }
            case Constants.GUJARATI: {
                accountInformation = "તમારું ખાતું શેષ છે: ";
                back = Gujarati.BACK;
                kill = Gujarati.KILL;
                break;
            }
            default:{
                accountInformation = "Your Account Balance is: ";
                break;
            }
        }
        Map<String,Object> res = connection.checkBalance(LocalStore.userId);
        BigDecimal amount = (BigDecimal) res.get("balance");
        panel = new JPanel(new GridLayout(1, 2));
        label = new JLabel(accountInformation + amount);
        label.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);
        backLabel = new JLabel(back);
        backLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        backLabel.setHorizontalAlignment(JLabel.CENTER);
        killLabel = new JLabel(kill);
        killLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        killLabel.setHorizontalAlignment(JLabel.CENTER);
        add(label);
        panel.add(backLabel);
        panel.add(killLabel);
        add(panel);
        setLayout(new GridLayout(2, 1));
        setSize(width(), height());
        setVisible(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case Constants.BACK :{
                        new OptionsFrame(language);
                        dispose();
                        break;
                    }
                    case Constants.KILL :{
                        new Home(true);
                        break;
                    }
                    default:{
                        System.out.println(e.getKeyChar());
                        break;
                    }
                }
            }
        });
    }
}
