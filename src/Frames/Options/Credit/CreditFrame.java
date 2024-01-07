package Frames.Options.Credit;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

public class CreditFrame extends Dimens {

    JLabel backSpace;
    JLabel enterAmount;
    JTextField amount;
    JLabel delete;
    JLabel pressEnterLabel;
    String enterAmountText;
    String back;
    String kill;
    String enterMinimumValidAmount;
    String pressEnter;
    JPanel panel;
    Connection connection = new Connection();

    public CreditFrame(String language) {
        setTitle(Constants.APP_NAME);

        switch (language) {
            case Constants.ENGLISH: {
                back = English.BACK;
                kill = English.KILL;
                enterAmountText = English.ENTER_AMOUNT_FOR_DEBIT;
                enterMinimumValidAmount = English.ENTER_MINIMUM_VALID_AMOUNT;
                pressEnter = English.PRESS_ENTER;
                break;
            }
            case Constants.HINDI: {
                back = Hindi.BACK;
                kill = Hindi.KILL;
                enterAmountText = Hindi.ENTER_AMOUNT_FOR_DEBIT;
                enterMinimumValidAmount = Hindi.ENTER_MINIMUM_VALID_AMOUNT;
                pressEnter = Hindi.PRESS_ENTER;
                break;
            }
            case Constants.GUJARATI: {
                back = Gujarati.BACK;
                kill = Gujarati.KILL;
                enterAmountText = Gujarati.ENTER_AMOUNT_FOR_DEBIT;
                enterMinimumValidAmount = Gujarati.ENTER_MINIMUM_VALID_AMOUNT;
                pressEnter = Gujarati.PRESS_ENTER;
                break;
            }
            default: {
                back = English.BACK;
                kill = English.KILL;
                enterAmountText = English.ENTER_AMOUNT_FOR_DEBIT;
                pressEnter = English.PRESS_ENTER;
                break;
            }
        }
        pressEnterLabel = new JLabel(pressEnter);
        panel = new JPanel(new GridLayout(1, 3));
        enterAmount = new JLabel(enterAmountText);
        enterAmount.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        enterAmount.setHorizontalAlignment(SwingConstants.CENTER);
        amount = new JTextField();
        amount.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        amount.setHorizontalAlignment(SwingConstants.CENTER);
        backSpace = new JLabel(back);
        delete = new JLabel(kill);
        backSpace.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        delete.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        backSpace.setHorizontalAlignment(SwingConstants.CENTER);
        delete.setHorizontalAlignment(SwingConstants.CENTER);
        pressEnterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pressEnterLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        add(new Label(""));
        add(new Label(""));
        add(new Label(""));
        add(enterAmount);
        add(amount);
        panel.add(backSpace);
        panel.add(pressEnterLabel);
        panel.add(delete);

        add(new Label(""));
        add(new Label(""));
        add(new Label("", Label.CENTER));
        add(panel);
        setVisible(true);
        setSize(width(), height());
        setLayout(new GridLayout(10, 1));
        amount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
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
                    case Constants.ENTER: {
                        validateAmount(amount.getText(), language);
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

    void validateAmount(String amount, String language) {
        if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, enterMinimumValidAmount, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int amountInt = Integer.parseInt(amount);
                if (amountInt > 199) {

                    Map<String, Object> response = connection.creditAmount(amountInt, LocalStore.userId, LocalStore.accountNumber);
                    System.out.println(response);
                    boolean result = (boolean) response.get("success");
                    String res = (String) response.get("message");

                    if (result) {
                        new CreditSuccess(language);
                        dispose();
                    } else {
                        new Throwable(res);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, enterMinimumValidAmount, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                new CreditFail(language);
                dispose();
            }
        }
    }
}
