package Frames.Options.AccountStatement;

import Dimens.Dimens;
import Frames.Home;
import Frames.Options.AccountStatement.model.Transaction;
import Frames.OptionsFrame;
import Network.Database.Connection;
import Network.LocalStore;
import Utils.Constants;
import Utils.Language.English;
import Utils.Language.Gujarati;
import Utils.Language.Hindi;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Map;

public class AccountStatement extends Dimens {
    private JTable table;
    private String back;
    private String kill;
    private JPanel panel;
    private JLabel killLabel;
    private JLabel backLabel;
    private ArrayList<Transaction> transactions;
    Connection connection = new Connection();

    public AccountStatement(String language) {
        transactions = connection.getTransactions(LocalStore.accountNumber);


//        System.out.println(res);
        setTitle(Constants.APP_NAME);

        switch (language) {
            case Constants.ENGLISH: {
                // English implementation
                back = English.BACK;
                kill = English.KILL;
                break;
            }
            case Constants.HINDI: {
                // Hindi implementation
                back = Hindi.BACK;
                kill = Hindi.KILL;
                break;
            }
            case Constants.GUJARATI: {
                // Gujarati implementation
                back = Gujarati.BACK;
                kill = Gujarati.KILL;
                break;
            }
            default: {
                // Default implementation if language not recognized
                back = English.BACK;
                kill = English.KILL;
                break;
            }
        }

        panel = new JPanel(new GridLayout(1, 2));

        // Create a table model with columns
        String[] columnNames = {"ID", "Date", "Time", "Transaction", "Transaction Detail", "Amount"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Populate the table model with data from the ArrayList
        for (Transaction transaction : transactions) {
            Object[] rowData = {
                    transaction.getId(),
                    transaction.getDate(), transaction.getTime(),
                    transaction.getTransaction(),
                    transaction.getTransactionDetail(),
                    transaction.getAmount(),
            };
            tableModel.addRow(rowData);
        }

        // Create a table and set its model
        JTable table = new JTable(tableModel);

        // Place the table in a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        backLabel = new JLabel(back);
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backLabel.setVerticalAlignment(SwingConstants.CENTER);
        backLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        panel.add(backLabel);

        killLabel = new JLabel(kill);
        killLabel.setHorizontalAlignment(SwingConstants.CENTER);
        killLabel.setVerticalAlignment(SwingConstants.CENTER);
        killLabel.setFont(new Font(Constants.Font_STYLE, Font.BOLD, 30));
        panel.add(killLabel);

        add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setSize(width(), height());
        setVisible(true);
        table.addKeyListener(new KeyListener() {
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
    }

}
