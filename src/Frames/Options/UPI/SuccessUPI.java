package Frames.Options.UPI;

import Dimens.Dimens;
import Frames.Home;
import Frames.OptionsFrame;
import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SuccessUPI extends Dimens {
    private String successMessage;

    private JLabel successLabel;

    public SuccessUPI(String language) {
        setTitle(Constants.APP_NAME);
        switch (language) {
            case Constants.ENGLISH: {
                successMessage = "UPI Transfer Successful";
                break;
            }
            case Constants.HINDI: {
                successMessage = "यूपीआई ट्रांसफर सफल";
                break;
            }
            case Constants.GUJARATI: {
                successMessage = "યુપીઆઈ ટ્રાન્સફર સફળ";
                break;
            }
            default: {
                successMessage = "UPI Transfer Successful";
                break;
            }

        }
        successLabel = new JLabel(successMessage);
        successLabel.setHorizontalAlignment(SwingConstants.CENTER);
        successLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        add(successLabel);
        setVisible(true);
        setSize(width(), height());
        setLayout(new GridLayout(1, 1));
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("UPI Success");
                new Home(true);
                dispose();
            } catch (Exception e) {
                System.out.println("UPI Success Error: " + e.getMessage());
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
