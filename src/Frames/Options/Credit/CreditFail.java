package Frames.Options.Credit;

import Dimens.Dimens;
import Frames.Home;
import Frames.OptionsFrame;
import Utils.Constants;
import Utils.Language.English;
import Utils.Language.Gujarati;
import Utils.Language.Hindi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CreditFail extends Dimens {
    private String failMessage;
    private String back;
    private String kill;
    private JLabel failLabel;
    public CreditFail(String language) {
        setTitle(Constants.APP_NAME);
        switch (language) {
            case Constants.ENGLISH: {
                failMessage = "Debit Failed";
                back = English.BACK;
                kill = English.KILL;
                break;
            }
            case Constants.HINDI: {
                failMessage = "डेबिट विफल";
                back = Hindi.BACK;
                kill = Hindi.KILL;
                break;
            }
            case Constants.GUJARATI: {
                failMessage = "ડેબિટ નિષ્ફળ";
                back = Gujarati.BACK;
                kill = Gujarati.KILL;
                break;
            }
            default: {
                break;
            }
        }
        failLabel = new JLabel(failMessage);
        failLabel.setHorizontalAlignment(SwingConstants.CENTER);
        failLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        add(failLabel);
        setVisible(true);
        setSize(width(), height());
        setLayout(new GridLayout(1, 1));
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Debit Fail");
                new Home(true);
                dispose();
            } catch (Exception e) {
                System.out.println("Debit Fail Error: " + e.getMessage());
            }
        });
        thread.start();
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
                    case Constants.BACK: {
                        new OptionsFrame(language);
                        dispose();
                        break;
                    }
                    case Constants.KILL: {
                        new Home(true);
                        dispose();
                        break;
                    }
                    default: {
                        System.out.println("Key Pressed" + e.getKeyCode());
                        break;
                    }
                }
            }
        });

    }

}
