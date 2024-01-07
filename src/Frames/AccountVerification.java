package Frames;

import Dimens.Dimens;
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

public class AccountVerification extends Dimens {
    String accountNumberLabel;
    String accountNumberVerificationSuccess;
    String accountNumberVerificationFailed;
    String enterPinLabel;
    String pinVerificationFailed;
    String pinVerificationSuccess;
    String otpLabel;
    String otpVerificationSuccess;
    String otpVerificationFailed;
    String back;
    String kill;
    Map<String, Object> res1;
    Map<String, Object> res2;
    Connection connection = new Connection();

    public AccountVerification(String language) {
        setTitle(Constants.APP_NAME);

        switch (language) {
            case Constants.ENGLISH: {
                accountNumberLabel = English.ENTER_ACCOUNT_NUMBER;
                enterPinLabel = English.ENTER_PIN;
                otpLabel = English.ENTER_OTP;
                back = English.BACK;
                kill = English.KILL;
                accountNumberVerificationSuccess = English.ACCOUNT_VERIFICATION_SUCCESS_MESSAGE;
                accountNumberVerificationFailed = English.ACCOUNT_VERIFICATION_FAILED_MESSAGE;
                pinVerificationFailed = English.PIN_VERIFICATION_FAILED_MESSAGE;
                pinVerificationSuccess = English.PIN_VERIFICATION_SUCCESS_MESSAGE;
                otpVerificationSuccess = English.OTP_VERIFICATION_SUCCESS_MESSAGE;
                otpVerificationFailed = English.OTP_VERIFICATION_FAILED_MESSAGE;
                break;
            }
            case Constants.HINDI: {
                accountNumberLabel = Hindi.ENTER_ACCOUNT_NUMBER;
                enterPinLabel = Hindi.ENTER_PIN;
                otpLabel = Hindi.ENTER_OTP;
                back = Hindi.BACK;
                kill = Hindi.KILL;
                accountNumberVerificationSuccess = Hindi.ACCOUNT_VERIFICATION_SUCCESS_MESSAGE;
                accountNumberVerificationFailed = Hindi.ACCOUNT_VERIFICATION_FAILED_MESSAGE;
                pinVerificationFailed = Hindi.PIN_VERIFICATION_FAILED_MESSAGE;
                pinVerificationSuccess = Hindi.PIN_VERIFICATION_SUCCESS_MESSAGE;
                otpVerificationSuccess = Hindi.OTP_VERIFICATION_SUCCESS_MESSAGE;
                otpVerificationFailed = Hindi.OTP_VERIFICATION_FAILED_MESSAGE;
                break;
            }
            case Constants.GUJARATI: {
                accountNumberLabel = Gujarati.ENTER_ACCOUNT_NUMBER;
                enterPinLabel = Gujarati.ENTER_PIN;
                otpLabel = Gujarati.ENTER_OTP;
                back = Gujarati.BACK;
                kill = Gujarati.KILL;
                accountNumberVerificationSuccess = Gujarati.ACCOUNT_VERIFICATION_SUCCESS_MESSAGE;
                accountNumberVerificationFailed = Gujarati.ACCOUNT_VERIFICATION_FAILED_MESSAGE;
                pinVerificationFailed = Gujarati.PIN_VERIFICATION_FAILED_MESSAGE;
                pinVerificationSuccess = Gujarati.PIN_VERIFICATION_SUCCESS_MESSAGE;
                otpVerificationSuccess = Gujarati.OTP_VERIFICATION_SUCCESS_MESSAGE;
                otpVerificationFailed = Gujarati.OTP_VERIFICATION_FAILED_MESSAGE;
                break;
            }
            default: {
                System.out.println("AccountVerification: Invalid language");
                break;
            }
        }
        JLabel accountNumber = new JLabel(accountNumberLabel);
        JLabel enterPin = new JLabel(enterPinLabel);
        JLabel enterOtp = new JLabel(otpLabel);
        JTextField accountNumberField = new JTextField();
        JPasswordField enterPinField = new JPasswordField();
        JPasswordField enterOtpField = new JPasswordField();
        Panel panel = new Panel(new GridLayout(1, 2));
        accountNumber.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        accountNumber.setHorizontalAlignment(SwingConstants.CENTER);
        enterPin.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        enterPin.setHorizontalAlignment(SwingConstants.CENTER);
        enterOtp.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        enterOtp.setHorizontalAlignment(SwingConstants.CENTER);
        accountNumberField.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        accountNumberField.setHorizontalAlignment(SwingConstants.CENTER);
        enterPinField.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        enterPinField.setHorizontalAlignment(SwingConstants.CENTER);
        enterOtpField.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        enterOtpField.setHorizontalAlignment(SwingConstants.CENTER);
        Label backspace = new Label(back);
        Label delete = new Label(kill);
        backspace.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        delete.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        backspace.setAlignment(Label.CENTER);
        delete.setAlignment(Label.CENTER);
        Label message = new Label("");
        message.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        message.setAlignment(Label.CENTER);
        panel.add(backspace);
        panel.add(delete);
        add(accountNumber);
        add(accountNumberField);
        add(enterPin);
        add(enterPinField);
        add(enterOtp);
        add(enterOtpField);
        add(message);
        add(panel);
        accountNumberField.requestFocus();
        enterPinField.setEditable(false);
        enterPinField.setEchoChar('X');
        enterOtpField.setEditable(false);
        enterOtpField.setEchoChar('X');
        setSize(width(), height());
        setLayout(new GridLayout(10, 2));
        setVisible(true);
        accountNumberField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }

            private void formKeyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == Constants.ENTER) {
                    if (accountNumberField.getText().length() == 14 && accountNumberField.getText().matches("[0-9]+")) {

                        res1 = connection.checkAccountNumber(Long.parseLong(accountNumberField.getText()));
                        if (res1.isEmpty()) {
                            showAccountVerificationFailedMessage(language);
                            return;
                        }
                        LocalStore.userId = (int) res1.get("userId");
                        LocalStore.fullName = (String) res1.get("name");
                        LocalStore.accountNumber = (Long) res1.get("accountNumber");
                        accountNumberField.setEditable(false);
                        showAccountVerificationSuccessMessage(language);
                        enterPinField.setEditable(true);
                        enterPinField.requestFocus();
                    } else {
                        showAccountVerificationFailedMessage(language);
                    }

                }
                if (evt.getKeyCode() == Constants.BACK) {
                    new AuthFrame(language);
                    dispose();
                } else if (evt.getKeyCode() == Constants.KILL) {
                    new Home(true);
                    dispose();
                }
            }
        });
        enterPinField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }

            private void formKeyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == Constants.ENTER) {
                    if (enterPinField.getText().length() == 4 && enterPinField.getText().matches("[0-9]+")) {
                        res2 = connection.checkPinNumber(LocalStore.userId, Integer.parseInt(enterPinField.getText()));
                        if (res2.isEmpty()) {
                            pinVerificationFailedMessage(language);
                            return;
                        }
                        LocalStore.firstName = (String) res2.get("fName");
                        LocalStore.lastName = (String) res2.get("lName");
                        LocalStore.middleName = (String) res2.get("mName");
                        LocalStore.mobileNumber = (Long) res2.get("phone");
                        boolean updateOtp = connection.updateOTP(LocalStore.userId);
                        if (!updateOtp) {
                            switch (language) {
                                case Constants.ENGLISH: {
                                    JOptionPane.showMessageDialog(null, "Internal Server Error", "Error", JOptionPane.ERROR_MESSAGE);
                                    new Home(true);
                                    break;
                                }
                                case Constants.HINDI: {
                                    JOptionPane.showMessageDialog(null, "आंतरिक सर्वर त्रुटि", "त्रुटि", JOptionPane.ERROR_MESSAGE);
                                    new Home(true);
                                    break;
                                }
                                case Constants.GUJARATI: {
                                    JOptionPane.showMessageDialog(null, "આંતરિક સર્વર ભૂલ", "ભૂલ", JOptionPane.ERROR_MESSAGE);
                                    new Home(true);
                                    break;
                                }
                            }
                        }

                        enterPinField.setEditable(false);
                        pinVerificationSuccessMessage(language);
                        enterOtpField.setEditable(true);
                        enterOtpField.requestFocus();
                    } else {
                        pinVerificationFailedMessage(language);
                    }
                }
                if (evt.getKeyCode() == Constants.BACK) {
                    new AuthFrame(language);
                    dispose();
                } else if (evt.getKeyCode() == Constants.KILL) {
                    new Home(true);
                    dispose();
                }
            }
        });
        enterOtpField.addKeyListener(new KeyAdapter() {
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
                if (e.getKeyCode() == Constants.ENTER) {
                    if (enterOtpField.getText().length() == 6 && enterOtpField.getText().matches("[0-9]+")) {
                        res2 = connection.checkOTP(LocalStore.userId, Integer.parseInt(enterOtpField.getText()));
                        if (res2.isEmpty()) {
                            otpVerificationFailedMessage(language);
                            return;
                        }
                        enterOtpField.setEditable(false);
                        showOtpSuccessMessage(language);
                        new OptionsFrame(language);
                        dispose();
                    } else {
                        otpVerificationFailedMessage(language);

                    }
                }
                if (e.getKeyCode() == Constants.BACK) {
                    new AuthFrame(language);
                    dispose();
                } else if (e.getKeyCode() == Constants.KILL) {
                    new Home(true);
                    dispose();
                }
            }


        });
    }

    private void showOtpSuccessMessage(String language) {
        SwingUtilities.invokeLater(() -> {
            if (language.equalsIgnoreCase("English")) {
                JOptionPane.showMessageDialog(this, otpVerificationSuccess, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (language.equalsIgnoreCase("Hindi")) {
                JOptionPane.showMessageDialog(this, otpVerificationSuccess, "सफलता", JOptionPane.INFORMATION_MESSAGE);
            } else if (language.equalsIgnoreCase("Gujarati")) {
                JOptionPane.showMessageDialog(this, otpVerificationSuccess, "સફળતા", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, otpVerificationSuccess, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void showAccountVerificationSuccessMessage(String language) {
        SwingUtilities.invokeLater(() -> {
            if (language.equalsIgnoreCase("English")) {
                JOptionPane.showMessageDialog(this, accountNumberVerificationSuccess, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (language.equalsIgnoreCase("Hindi")) {
                JOptionPane.showMessageDialog(this, accountNumberVerificationSuccess, "सफलता", JOptionPane.INFORMATION_MESSAGE);
            } else if (language.equalsIgnoreCase("Gujarati")) {
                JOptionPane.showMessageDialog(this, accountNumberVerificationSuccess, "સફળતા", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, accountNumberVerificationSuccess, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void showAccountVerificationFailedMessage(String language) {
        SwingUtilities.invokeLater(() -> {
            if (language.equalsIgnoreCase("English")) {
                JOptionPane.showMessageDialog(this, "Invalid Account Number", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (language.equalsIgnoreCase("Hindi")) {
                JOptionPane.showMessageDialog(this, "अमान्य खाता संख्या", "त्रुटि", JOptionPane.ERROR_MESSAGE);
            } else if (language.equalsIgnoreCase("Gujarati")) {
                JOptionPane.showMessageDialog(this, "અમાન્ય ખાતા નંબર", "ભૂલ", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Account Number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void pinVerificationSuccessMessage(String language) {
        SwingUtilities.invokeLater(() -> {
            if (language.equalsIgnoreCase("English")) {
                JOptionPane.showMessageDialog(this, pinVerificationSuccess, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (language.equalsIgnoreCase("Hindi")) {
                JOptionPane.showMessageDialog(this, pinVerificationSuccess, "सफलता", JOptionPane.INFORMATION_MESSAGE);
            } else if (language.equalsIgnoreCase("Gujarati")) {
                JOptionPane.showMessageDialog(this, pinVerificationSuccess, "સફળતા", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, pinVerificationSuccess, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void pinVerificationFailedMessage(String language) {
        SwingUtilities.invokeLater(() -> {
            if (language.equalsIgnoreCase("English")) {
                JOptionPane.showMessageDialog(this, pinVerificationFailed, "Error", JOptionPane.ERROR_MESSAGE);
            } else if (language.equalsIgnoreCase("Hindi")) {
                JOptionPane.showMessageDialog(this, pinVerificationFailed, "त्रुटि", JOptionPane.ERROR_MESSAGE);
            } else if (language.equalsIgnoreCase("Gujarati")) {
                JOptionPane.showMessageDialog(this, pinVerificationFailed, "ભૂલ", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, pinVerificationFailed, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void otpVerificationFailedMessage(String language) {
        SwingUtilities.invokeLater(() -> {
            if (language.equalsIgnoreCase("English")) {
                JOptionPane.showMessageDialog(this, otpVerificationFailed, "Error", JOptionPane.ERROR_MESSAGE);
            } else if (language.equalsIgnoreCase("Hindi")) {
                JOptionPane.showMessageDialog(this, otpVerificationFailed, "त्रुटि", JOptionPane.ERROR_MESSAGE);
            } else if (language.equalsIgnoreCase("Gujarati")) {
                JOptionPane.showMessageDialog(this, otpVerificationFailed, "ભૂલ", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, otpVerificationFailed, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}