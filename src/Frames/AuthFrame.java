package Frames;

import Dimens.Dimens;
import Utils.Constants;
import Utils.Language.English;
import Utils.Language.Gujarati;
import Utils.Language.Hindi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AuthFrame extends Dimens {
    private String byCard;
    private String byEnterAccount;
    private String _or;
    private String back;
    private String kill;

    public AuthFrame(String language) {
        setTitle(Constants.APP_NAME);
        setupLanguageStrings(language);

        JLabel card = new JLabel(byCard);
        JLabel or = new JLabel(_or);
        JLabel accountNo = new JLabel(byEnterAccount);
        JLabel backspace = new JLabel(back);
        JLabel delete = new JLabel(kill);

        setupLabels(card, or, accountNo, backspace, delete);

        addListeners(language);
    }

    private void setupLanguageStrings(String language) {

        switch (language) {
            case Constants.ENGLISH: {
                this.byCard = English.ENTER_CARD;
                this._or = English.OR;
                this.byEnterAccount = English.BY_ACCOUNT_NO;
                this.back = English.BACK;
                this.kill = English.KILL;
                break;
            }
            case Constants.HINDI: {
                this.byCard = Hindi.ENTER_CARD;
                this._or = Hindi.OR;
                this.byEnterAccount = Hindi.BY_ACCOUNT_NO;
                this.back = Hindi.BACK;
                this.kill = Hindi.KILL;
                break;
            }
            case Constants.GUJARATI: {
                this.byCard = Gujarati.ENTER_CARD;
                this._or = Gujarati.OR;
                this.byEnterAccount = Gujarati.BY_ACCOUNT_NO;
                this.back = Gujarati.BACK;
                this.kill = Gujarati.KILL;
                break;
            }
            default: {
                System.out.println("AuthFrame: Invalid language");
                break;
            }
        }

    }

    private void setupLabels(JLabel card, JLabel or, JLabel accountNo, JLabel backspace, JLabel delete) {
        card.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 50));
        or.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 50));
        accountNo.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 50));
        backspace.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        delete.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));

        card.setHorizontalAlignment(SwingConstants.CENTER);
        or.setHorizontalAlignment(SwingConstants.CENTER);
        accountNo.setHorizontalAlignment(SwingConstants.CENTER);
        backspace.setHorizontalAlignment(SwingConstants.CENTER);
        delete.setHorizontalAlignment(SwingConstants.CENTER);

        card.setBounds(0, 100, width(), 100);
        or.setBounds(0, 200, width(), 200);
        accountNo.setBounds(0, 400, width(), 100);
        backspace.setBounds(0, 700, width() / 2, 100);
        delete.setBounds(width() / 2, 700, width() / 2, 100);

        add(card);
        add(or);
        add(accountNo);
        add(backspace);
        add(delete);

        setLayout(null);
        setSize(width(), height());
        setVisible(true);
    }

    private void addListeners(String language) {
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt, language);
            }

            private void formKeyPressed(KeyEvent evt, String language) {
                if (evt.getKeyChar() == 'a' || evt.getKeyChar() == 'A') {
                    new AccountVerification(language);
                    dispose();
                }

                if (evt.getKeyCode() == Constants.BACK || evt.getKeyCode() == Constants.KILL) {
                    new Home(true);
                    dispose();
                }
            }
        });
    }

}
