package Frames.Options.ResetPIN;

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ResetPIN extends Dimens {
    String title;
    String enterNewPin;
    String confirmNewPin;
    String changePin;
    String back;
    String kill;
    String pinNotMatch;
    String pinChanged;
    String pinNotChanged;
    String pinNotValid;
    JLabel titleLabel;
    JLabel enterNewPinLabel;
    JLabel confirmNewPinLabel;
    JLabel backLabel;
    JLabel killLabel;
    JTextField enterNewPinTextField;
    JTextField confirmNewPinTextField;
    JPanel panel;
    JButton submit;
    Connection connection = new Connection();

    public ResetPIN(String language) {
        setTitle(Constants.APP_NAME);
        switch (language) {
            case Constants.ENGLISH: {
                title = "Reset PIN";
                enterNewPin = "Enter New PIN";
                confirmNewPin = "Confirm New PIN";
                changePin = "Change PIN";
                back = English.BACK;
                kill = English.KILL;
                pinNotMatch = "PIN not match";
                pinChanged = "PIN changed";
                pinNotChanged = "PIN not changed";
                pinNotValid = "PIN not valid";
                break;
            }
            case Constants.HINDI: {
                title = "पिन रीसेट करें";
                enterNewPin = "नया पिन दर्ज करें";
                confirmNewPin = "नया पिन कन्फर्म करें";
                changePin = "पिन बदलें";
                back = Hindi.BACK;
                kill = Hindi.KILL;
                pinNotMatch = "पिन मेल नहीं खाता";
                pinChanged = "पिन बदल दिया गया";
                pinNotChanged = "पिन बदला नहीं गया";
                pinNotValid = "पिन वैध नहीं है";
                break;
            }
            case Constants.GUJARATI: {
                title = "પિન રીસેટ કરો";
                enterNewPin = "નવો પિન દાખલ કરો";
                confirmNewPin = "નવો પિન ખાતરી કરો";
                changePin = "પિન બદલો";
                back = Gujarati.BACK;
                kill = Gujarati.KILL;
                pinNotMatch = "પિન મેળ ખાતો નથી";
                pinChanged = "પિન બદલાઈ ગઈ";
                pinNotChanged = "પિન બદલાઈ નથી";
                pinNotValid = "પિન માન્ય નથી";
                break;
            }
            default: {
                title = "Reset PIN";
                enterNewPin = "Enter New PIN";
                confirmNewPin = "Confirm New PIN";
                changePin = "Change PIN";
                back = English.BACK;
                kill = English.KILL;
                pinNotMatch = "PIN not match";
                pinChanged = "PIN changed";
                pinNotChanged = "PIN not changed";
                pinNotValid = "PIN not valid";
                break;
            }

        }
        titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 50));
        add(titleLabel);
        add(new JLabel(""));
        enterNewPinLabel = new JLabel(enterNewPin);
        enterNewPinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enterNewPinLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        add(enterNewPinLabel);
        enterNewPinTextField = new JTextField();
        enterNewPinTextField.setHorizontalAlignment(SwingConstants.CENTER);
        enterNewPinTextField.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        add(enterNewPinTextField);
        confirmNewPinLabel = new JLabel(confirmNewPin);
        confirmNewPinLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmNewPinLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        add(confirmNewPinLabel);
        confirmNewPinTextField = new JTextField();
        confirmNewPinTextField.setHorizontalAlignment(SwingConstants.CENTER);
        confirmNewPinTextField.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        add(confirmNewPinTextField);
        panel = new JPanel(new GridLayout(1, 3));
        backLabel = new JLabel(back);
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        panel.add(backLabel);

        submit = new JButton(changePin);
        submit.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        panel.add(submit);


        killLabel = new JLabel(kill);
        killLabel.setHorizontalAlignment(SwingConstants.CENTER);
        killLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        panel.add(killLabel);
        add(new JLabel(""));
        add(new JLabel(""));
        add(panel);
        setVisible(true);
        setSize(width(), height());
        setLayout(new GridLayout(10, 1));
        enterNewPinTextField.requestFocus();
        confirmNewPinTextField.setEditable(false);

        enterNewPinTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (enterNewPinTextField.getText().length() > 3) {
                    confirmNewPinTextField.requestFocus();
                    confirmNewPinTextField.setEditable(true);
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (enterNewPinTextField.getText().length() > 3) {
                    confirmNewPinTextField.requestFocus();
                    confirmNewPinTextField.setEditable(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (enterNewPinTextField.getText().length() > 3) {
                    confirmNewPinTextField.requestFocus();
                    confirmNewPinTextField.setEditable(true);
                }
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
                        System.out.println(e.getKeyChar());
                        break;
                    }
                }
            }
        });


        confirmNewPinTextField.addKeyListener(new KeyListener() {
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
                        System.out.println(e.getKeyChar());
                        break;
                    }
                }
            }
        });


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enterNewPinTextField.getText().equals(confirmNewPinTextField.getText())) {
                    if (enterNewPinTextField.getText().length() == 4) {
                        if (enterNewPinTextField.getText().matches("[0-9]+")) {
                            boolean result = connection.changePin(Integer.parseInt(enterNewPinTextField.getText()), LocalStore.userId);
                            if (result) {
                                JOptionPane.showMessageDialog(null, pinChanged);
                                new Home(true);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, pinNotChanged);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, pinNotValid);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, pinNotValid);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, pinNotMatch);
                }
            }
        });
    }
}
