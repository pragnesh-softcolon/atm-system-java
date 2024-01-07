package Frames.Options.AccountStatement.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Transaction {
    private String date;
    private BigDecimal amount;
    private int id;
    private String time;
    private long accountNumber;
    private String transaction;
    private String transactionDetail;

    public Transaction(String date, BigDecimal amount, int id, String time, long accountNumber, String transaction, String transactionDetail) {
        this.date = date;
        this.amount = amount;
        this.id = id;
        this.time = time;
        this.accountNumber = accountNumber;
        this.transaction = transaction;
        this.transactionDetail = transactionDetail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public void setTransactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public String getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getTransaction() {
        return transaction;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }
}
