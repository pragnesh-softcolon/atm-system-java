package Frames;

import Dimens.Dimens;
import Utils.Constants;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.net.URL;

public class Home extends Dimens {
    private JPanel panel = new JPanel(new GridLayout(3, 2));
    private JLabel label = new JLabel(Constants.WELCOME_TEXT);
    private JLabel example = new JLabel("");
    private JLabel example2 = new JLabel("");
    private JLabel labelEnglish = new JLabel(Constants.FOR_ENGLISH);
    private JLabel labelHindi = new JLabel(Constants.FOR_HINDI);
    private JLabel labelGujarati = new JLabel(Constants.FOR_GUJARATI);

    public Home(boolean visible) {
        setTitle(Constants.APP_NAME);
        label.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 50));
        labelEnglish.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        labelHindi.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        labelGujarati.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        example.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        example2.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        labelEnglish.setHorizontalAlignment(SwingConstants.CENTER);
        labelHindi.setHorizontalAlignment(SwingConstants.CENTER);
        labelGujarati.setHorizontalAlignment(SwingConstants.CENTER);
        example.setHorizontalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        add(label);
        panel.add(example);
        panel.add(example2);
        panel.add(labelEnglish);
        panel.add(labelHindi);
        panel.add(labelGujarati);
        add(panel);
        setLayout(new GridLayout(3, 1));
        setSize(width(), height());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(visible);

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }

            private void formKeyPressed(KeyEvent evt) {
                char keyChar = Character.toLowerCase(evt.getKeyChar());
                switch (keyChar) {
                    case 'e':
                        openAuthFrame(Constants.ENGLISH);
                        break;
                    case 'h':
                        openAuthFrame(Constants.HINDI);
                        break;
                    case 'g':
                        openAuthFrame(Constants.GUJARATI);
                        break;
                }
            }

            private void openAuthFrame(String language) {
                new AuthFrame(language);
                dispose();
            }
        });
    }


}
