package Frames.Options.UPI;

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
import java.util.Map;

public class UPI extends Dimens {
    String heading;
    String enterUpiId;
    String enterAmount;
    String confirm;
    String back;
    String kill;
    JLabel headingLabel;
    JLabel enterUpiIdLabel;
    JLabel enterAmountLabel;
    JLabel backLabel;
    JLabel killLabel;
    JTextField enterUpiIdTextField;
    JTextField enterAmountTextField;
    JButton confirmButton;
    JPanel panel;
    Connection connection = new Connection();
    Map<String,Object> res;
    public UPI(String language) {
        setTitle(Constants.APP_NAME);
        switch (language) {
            case Constants.ENGLISH: {
                heading = "UPI Transfer";
                enterUpiId = "Enter UPI ID";
                enterAmount = "Enter Amount for UPI Transfer";
                confirm = "Transfer";
                back = English.BACK;
                kill = English.KILL;
                break;
            }
            case Constants.HINDI: {
                heading = "यूपीआई ट्रांसफर";
                enterUpiId = "यूपीआई आईडी दर्ज करें";
                enterAmount = "यूपीआई ट्रांसफर के लिए राशि दर्ज करें";
                confirm = "ट्रांसफर करें";
                back = Hindi.BACK;
                kill = Hindi.KILL;
                break;
            }
            case Constants.GUJARATI: {
                heading = "યુપીઆઈ ટ્રાન્સફર";
                enterUpiId = "યુપીઆઈ આઈડી દાખલ કરો";
                enterAmount = "યુપીઆઈ ટ્રાન્સફર માટે રકમ દાખલ કરો";
                confirm = "ટ્રાન્સફર કરો";
                back = Gujarati.BACK;
                kill = Gujarati.KILL;
                break;
            }
            default: {

                break;
            }
        }
        headingLabel = new JLabel(heading);
        headingLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 50));
        headingLabel.setHorizontalAlignment(JLabel.CENTER);
        headingLabel.setVerticalAlignment(JLabel.CENTER);
        enterUpiIdLabel = new JLabel(enterUpiId);
        enterUpiIdLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        enterUpiIdLabel.setHorizontalAlignment(JLabel.CENTER);
        enterUpiIdLabel.setVerticalAlignment(JLabel.CENTER);
        enterUpiIdTextField = new JTextField();
        enterUpiIdTextField.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        enterUpiIdTextField.setHorizontalAlignment(JLabel.CENTER);


        enterAmountLabel = new JLabel(enterAmount);
        enterAmountLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        enterAmountLabel.setHorizontalAlignment(JLabel.CENTER);
        enterAmountLabel.setVerticalAlignment(JLabel.CENTER);
        enterAmountTextField = new JTextField();
        enterAmountTextField.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        enterAmountTextField.setHorizontalAlignment(JLabel.CENTER);

        panel = new JPanel(new GridLayout(1, 3));
        confirmButton = new JButton(confirm);
        confirmButton.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        confirmButton.setHorizontalAlignment(JLabel.CENTER);
        confirmButton.setVerticalAlignment(JLabel.CENTER);
        confirmButton.setVisible(false);
        backLabel = new JLabel(back);
        backLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        backLabel.setHorizontalAlignment(JLabel.CENTER);
        backLabel.setVerticalAlignment(JLabel.CENTER);
        killLabel = new JLabel(kill);
        killLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        killLabel.setHorizontalAlignment(JLabel.CENTER);
        killLabel.setVerticalAlignment(JLabel.CENTER);
        panel.add(backLabel);
        panel.add(confirmButton);
        panel.add(killLabel);
        add(headingLabel);
        add(new JLabel(""));
        add(enterUpiIdLabel);
        add(enterUpiIdTextField);
        add(enterAmountLabel);
        add(enterAmountTextField);
        add(new JLabel(""));
        add(new JLabel(""));
        add(panel);
        setLayout(new GridLayout(10, 1));
        setSize(width(), height());
        setVisible(true);

        enterUpiIdTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case Constants.ENTER: {
                        checkUPI(enterUpiIdTextField.getText(), language);
                        break;
                    }
                    case Constants.BACK: {
                        new OptionsFrame(language);
                        dispose();
                        break;
                    }
                    case Constants.KILL: {
                        new Home(true);
                        break;
                    }
                    default: {
                        break;
                    }
                }

            }
        });

        enterAmountTextField.addKeyListener(new KeyListener() {
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
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enterAmountTextField.getText().isEmpty()) {
                    switch (language) {
                        case Constants.ENGLISH: {
                            JOptionPane.showMessageDialog(panel, "Enter valid amount to transfer", "Error", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        case Constants.HINDI: {
                            JOptionPane.showMessageDialog(panel, "ट्रांसफर करने के लिए वैध राशि दर्ज करें", "त्रुटि", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        case Constants.GUJARATI: {
                            JOptionPane.showMessageDialog(panel, "ટ્રાન્સફર કરવા માટે માન્ય રકમ દાખલ કરો", "ભૂલ", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                    return;
                }
                int amount = Integer.parseInt(enterAmountTextField.getText());
                if (amount > 0) {
                    Map<String, Object> response=   connection.debitAmount(amount, LocalStore.userId, LocalStore.accountNumber,"Transfer amount by UPI from ATM");
                    boolean result =(boolean) response.get("success");
                    String res = (String) response.get("message");

                    if (result) {
                        new SuccessUPI(language);
                        dispose();
                    }else{
                        switch (language) {
                            case Constants.ENGLISH: {
                                JOptionPane.showMessageDialog(panel, "Something went wrong", "Error", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            case Constants.HINDI: {
                                JOptionPane.showMessageDialog(panel, "कुछ गलत हो गया", "त्रुटि", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                            case Constants.GUJARATI: {
                                JOptionPane.showMessageDialog(panel, "કંઈક ખોટું થયું", "ભૂલ", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        }
//                        new Home(true);
//                        dispose();
                    }
                } else {
                    switch (language) {
                        case Constants.ENGLISH: {
                            JOptionPane.showMessageDialog(panel, "Amount must be grater than 0", "Error", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        case Constants.HINDI: {
                            JOptionPane.showMessageDialog(panel, "राशि 0 से अधिक होनी चाहिए", "त्रुटि", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        case Constants.GUJARATI: {
                            JOptionPane.showMessageDialog(panel, "રકમ 0 કરતાં વધુ હોવી જોઈએ", "ભૂલ", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    }
                }
            }
        });
    }

    void checkUPI(String upiId, String language) {
        String upiIdRegex = "^[a-z0-9.]+@[a-z0-9]+$";
        if (!enterUpiIdTextField.getText().isEmpty()) {
            if (upiId.matches(upiIdRegex)) {
                res = connection.verifyUPI(upiId);
                if(res.isEmpty()){
                    switch (language) {
                        case Constants.ENGLISH: {
                            JOptionPane.showMessageDialog(this, "UPI ID is not Valid", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        case Constants.HINDI: {
                            JOptionPane.showMessageDialog(this, "यूपीआई आईडी वैध नहीं है", "त्रुटि", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        case Constants.GUJARATI: {
                            JOptionPane.showMessageDialog(this, "યુપીઆઈ આઈડી માન્ય નથી", "ભૂલ", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                    return;
                }
                enterUpiIdTextField.setEditable(false);
                switch (language) {
                    case Constants.ENGLISH: {
                        JOptionPane.showMessageDialog(this, "UPI ID is Valid", "Success", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case Constants.HINDI: {
                        JOptionPane.showMessageDialog(this, "यूपीआई आईडी वैध", "सफलता", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    case Constants.GUJARATI: {
                        JOptionPane.showMessageDialog(this, "યુપીઆઈ આઈડી માન્ય", "સફળતા", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
                confirmButton.setVisible(true);
                enterAmountTextField.requestFocus();
            } else {
                switch (language) {
                    case Constants.ENGLISH: {
                        JOptionPane.showMessageDialog(this, "Invalid UPI ID", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    case Constants.HINDI: {
                        JOptionPane.showMessageDialog(this, "अमान्य यूपीआई आईडी", "त्रुटि", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    case Constants.GUJARATI: {
                        JOptionPane.showMessageDialog(this, "અમાન્ય યુપીઆઈ આઈડી", "ભૂલ", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
            }
        }
    }
}
