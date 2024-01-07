package Frames.Options.UPI;

import Dimens.Dimens;
import Frames.Home;
import Frames.OptionsFrame;
import Utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FailUPI  extends Dimens {
    private String failMessage;

    private JLabel failLabel;
    public FailUPI(String language) {
        setTitle(Constants.APP_NAME);
        switch (language) {
            case Constants.ENGLISH: {
                failMessage = "UPI Transfer fail";
                break;
            }
            case Constants.HINDI: {
                failMessage = "यूपीआई ट्रांसफर असफल";
                break;
            }
            case Constants.GUJARATI: {
                failMessage = "યુપીઆઈ ટ્રાન્સફર અસફળ";
                break;
            }
            default: {
                failMessage = "UPI Transfer fail";
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
                System.out.println("UPI fail");
                new Home(true);
                dispose();
            } catch (Exception e) {
                System.out.println("UPI fail Error: " + e.getMessage());
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
