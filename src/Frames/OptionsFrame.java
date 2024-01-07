package Frames;

import Dimens.Dimens;
import Frames.Options.AccountStatement.AccountStatement;
import Frames.Options.CheckBalance.CheckBalance;
import Frames.Options.Credit.CreditFrame;
import Frames.Options.ResetPIN.ResetPIN;
import Frames.Options.UPI.UPI;
import Frames.Options.Withdrew.WithdrewFrame;
import Network.LocalStore;
import Utils.Constants;
import Utils.Language.English;
import Utils.Language.Gujarati;
import Utils.Language.Hindi;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OptionsFrame extends Dimens {
    Panel main = new Panel(new GridLayout(1, 3));
    Panel panel1 = new Panel(new GridLayout(5, 1));
    Panel panel2 = new Panel(new GridLayout(5, 1));
    Panel panel3 = new Panel(new GridLayout(5, 1));
    String accountInformation;
    String debit;
    String withdrew;
    String checkBalance;
    String upiTransfer;
    String accountStatement;
    String resetPin;
    String back;
    String kill;
    String welcome;

    public OptionsFrame(String language) {
        setTitle(Constants.APP_NAME);

        switch (language) {
            case Constants.ENGLISH: {
                accountInformation = "Account Information";
                debit = "Deposit (Press D)";
                withdrew = "Withdrew (Press W)";
                checkBalance = "Check Balance (Press B)";
                upiTransfer = "UPI Transfer (Press U)";
                accountStatement = "Account Statement (Press S)";
                resetPin = "Reset PIN (Press R)";
                back = English.BACK;
                kill = English.KILL;
                welcome = "WELCOME, ";
                break;
            }
            case Constants.HINDI: {
                accountInformation = "खाता संबंधी जानकारी देखें (A दबाएं)";
                debit = "राशि जमा करने के लिए (D दबाएं)";
                withdrew = "निकालना (W दबाएं)";
                checkBalance = "बैलेंस जांचें (B दबाएं)";
                upiTransfer = "यूपीआई ट्रांसफर (U दबाएं)";
                accountStatement = "खाता विवरण (S दबाएं)";
                resetPin = "पिन रीसेट करें (R दबाएं)";
                back = Hindi.BACK;
                kill = Hindi.KILL;
                welcome = "आपका स्वागत है, ";
                break;
            }
            case Constants.GUJARATI: {
                accountInformation = "ખાતાની માહિતી જુઓ (A દબાવો)";
                debit = "રકમ જમા કરવા (D દબાવો)";
                withdrew = "ઉપાડવા (W દબાવો)";
                checkBalance = "બેલેન્સ ચેક કરો (B દબાવો)";
                upiTransfer = "યુપીઆઈ ટ્રાન્સફર (U દબાવો)";
                accountStatement = "એકાઉન્ટ સ્ટેટમેન્ટ (S દબાવો)";
                resetPin = "પિન રીસેટ કરો (R દબાવો)";
                back = Gujarati.BACK;
                kill = Gujarati.KILL;
                welcome = "સ્વાગત છે, ";
                break;
            }
            default: {
                accountInformation = "Account Information (Press A)";
                debit = "Debit (Press D)";
                withdrew = "Withdrew (Press W)";
                checkBalance = "Check Balance (Press B)";
                upiTransfer = "UPI Transfer (Press U)";
                accountStatement = "Account Statement (Press S)";
                resetPin = "Reset PIN (Press R)";
                back = English.BACK;
                kill = English.KILL;
                welcome = "WELCOME, ";
                break;
            }
        }

        Label accountInfo = new Label(accountInformation);
        Label debitLabel = new Label(debit);
        Label withdrewLabel = new Label(withdrew);
        Label checkBalanceLabel = new Label(checkBalance);
        Label upiTransferLabel = new Label(upiTransfer);
        Label accountStatementLabel = new Label(accountStatement);
        Label resetPinLabel = new Label(resetPin);
        Label accountHolderName = new Label("<html>" + welcome + "<br>"+ LocalStore.lastName+" "+LocalStore.firstName+" "+LocalStore.middleName +"&nbsp &nbsp</html>");
        accountInfo.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 50));
        debitLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        withdrewLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        checkBalanceLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        upiTransferLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        accountStatementLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        resetPinLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        accountHolderName.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        accountInfo.setAlignment(Label.CENTER);
        debitLabel.setAlignment(Label.LEFT);
        withdrewLabel.setAlignment(Label.LEFT);
        checkBalanceLabel.setAlignment(Label.LEFT);
        upiTransferLabel.setAlignment(Label.RIGHT);
        accountStatementLabel.setAlignment(Label.RIGHT);
        resetPinLabel.setAlignment(Label.RIGHT);
        Label backspace = new Label(back);
        Label delete = new Label(kill);
        backspace.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        delete.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        backspace.setAlignment(Label.CENTER);
        delete.setAlignment(Label.CENTER);
        panel1.add(debitLabel);
        panel1.add(withdrewLabel);
        panel1.add(checkBalanceLabel);
        panel1.add(backspace);
        panel2.add(accountHolderName);
        panel3.add(upiTransferLabel);
        panel3.add(accountStatementLabel);
        panel3.add(resetPinLabel);
        panel3.add(delete);
        main.add(panel1);
        main.add(panel2);
        main.add(panel3);
        add(main);
        setLayout(new GridLayout(1, 1));
        setSize(width(), height());
        setVisible(true);
        addKeyListener(new KeyAdapter() {
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
                if (e.getKeyChar() == 'D' || e.getKeyChar() == 'd') {
//                    TODO : Debit
                    System.out.println("Credit");
                    new CreditFrame(language);
                    dispose();
                } else if (e.getKeyChar() == 'W' || e.getKeyChar() == 'w') {
//                    TODO : Withdrew
                    System.out.println("Withdrew");
                    new WithdrewFrame(language);
                    dispose();
                } else if (e.getKeyChar() == 'B' || e.getKeyChar() == 'b') {
//                    TODO : Check Balance
                    System.out.println("Check Balance");
                    new CheckBalance(language);
                    dispose();
                } else if (e.getKeyChar() == 'U' || e.getKeyChar() == 'u') {
//                    TODO : UPI Transfer
                    new UPI(language);
                    dispose();
                    System.out.println("UPI Transfer");
                } else if (e.getKeyChar() == 'S' || e.getKeyChar() == 's') {
//                    TODO : Account Statement
                    new AccountStatement(language);
                    dispose();
                    System.out.println("Account Statement");
                } else if (e.getKeyChar() == 'R' || e.getKeyChar() == 'r') {
//                    TODO : Reset PIN
                    new ResetPIN(language);
                    dispose();
                    System.out.println("Reset PIN");
                } else if (e.getKeyCode() == Constants.BACK) {
                    new Home(true);
                    dispose();
                } else if (e.getKeyCode() == Constants.KILL) {
                    new Home(true);
                    dispose();
                }
            }
        });
    }
}