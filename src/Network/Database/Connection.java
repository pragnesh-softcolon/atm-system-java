package Network.Database;

import Frames.Options.AccountStatement.model.Transaction;

import io.github.cdimascio.dotenv.Dotenv;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class Connection {
    private static final Logger LOGGER = Logger.getLogger(Connection.class.getName());
    Dotenv dotenv = Dotenv.load();
    public String url = dotenv.get("URL");
    public String database = dotenv.get("DATABASE");
    public String user = dotenv.get("ROOT_USER");
    public String password = dotenv.get("PASSWORD");
    private final String accountTable = dotenv.get("ACCOUNT_TABLE");
    private final String userTable = dotenv.get("USER_TABLE");

    private final String transactionTable = dotenv.get("TRANSACTION_TABLE");
    private final String upiTable = dotenv.get("UPI_TABLE");
    public java.sql.Connection connection;

    public Connection() {
        try {
            connection = DriverManager.getConnection((url + database), user, password);
            LOGGER.info("Connected to the database!");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            System.exit(0);
        }
    }

    public Map<String, Object> checkAccountNumber(Long accountNumber) {
        Map<String, Object> response = new HashMap<>();

        try {
            String query = "SELECT * FROM " + accountTable + " WHERE accountNumber = " + accountNumber;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    response.put(columnName, columnValue);
                }
            }
            LOGGER.info("Account number verified : \n" + response);
            return response;
        } catch (Exception e) {
            LOGGER.info("Exception : \n" + e.getMessage());
        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                LOGGER.info("Exception : \n" + e.getMessage());
//                throw new RuntimeException(e);
//            }
        }
        LOGGER.warning("Account number not verified : \n" + response);
        return response;
    }

    public Map<String, Object> checkPinNumber(int userId, int pinNo) {
        Map<String, Object> response = new HashMap<>();
        try {
            String query = "SELECT * FROM " + userTable + " WHERE id = " + userId + " AND pin = " + pinNo;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            LOGGER.info("MetaData \n : " + metaData);
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    response.put(columnName, columnValue);
                }
            }
            LOGGER.info("PIN Verified : \n" + response);
            return response;
        } catch (Exception e) {
            LOGGER.info("Exception : \n" + e.getMessage());
        }
        LOGGER.info("PIN Not Verified : \n" + response);
        return response;
    }

    public boolean updateOTP(int userId) {
        try {
            int otp = generateOTP();
            String query = "UPDATE " + userTable + " SET otp = " + otp + " WHERE id = " + userId;
            Statement statement = connection.createStatement();
            int rowsUpdated = statement.executeUpdate(query);
            LOGGER.info("OTP : " + otp);
            return rowsUpdated > 0;
        } catch (Exception e) {
            LOGGER.info("Exception : \n" + e.getMessage());
        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                LOGGER.info("Exception : \n" + e.getMessage());
//                throw new RuntimeException(e);
//            }
        }
        LOGGER.info("OTP Not Updated");
        return false;
    }


    public int generateOTP() {
        return (int) (Math.random() * 900000) + 100000;
    }

    public Map<String, Object> checkOTP(int userId, int otp) {
        Map<String, Object> response = new HashMap<>();
        try {
            String query = "SELECT * FROM " + userTable + " WHERE id = " + userId + " AND otp = " + otp;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    response.put(columnName, columnValue);
                }
            }
            LOGGER.info("OTP Verified : \n" + response);
            return response;
        } catch (Exception e) {
            LOGGER.info("Exception : \n" + e.getMessage());
        }
        LOGGER.info("OTP Not Verified : \n" + response);
        return response;
    }

    public Map<String, Object> debitAmount(int amount, int userId, Long accountNumber, String msg) {
        Map<String, Object> response = new HashMap<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            // Set auto-commit to false to start a transaction
            connection.setAutoCommit(false);

            // Check if the user exists
            String checkUserQuery = "SELECT * FROM user WHERE id = ?";
            preparedStatement = connection.prepareStatement(checkUserQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                response.put("success", false);
                response.put("message", "User does not exist");
                return response;
            }

            // Get the current balance of the user
            String getBalanceQuery = "SELECT balance FROM account WHERE userId = ?";
            preparedStatement = connection.prepareStatement(getBalanceQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double currentBalance = resultSet.getDouble("balance");

                // Check if the user has enough balance to debit
                if (currentBalance >= amount) {
                    // Debit the amount
                    String debitQuery = "UPDATE account SET balance = balance - ? WHERE userId = ?";
                    preparedStatement = connection.prepareStatement(debitQuery);
                    preparedStatement.setDouble(1, amount);
                    preparedStatement.setInt(2, userId);
                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        // Insert transaction record
                        String insertTransactionQuery = "INSERT INTO " + transactionTable + "(accountNumber, transaction,transactionDetail, amount, date, time) VALUES (?, ?,?, ?, CURDATE(), CURTIME())";
                        LOGGER.info("insertTransactionQuery : \n" + insertTransactionQuery);
                        preparedStatement = connection.prepareStatement(insertTransactionQuery);
                        preparedStatement.setLong(1, accountNumber); // Assuming you have the accountNumber available
                        preparedStatement.setString(2, "withdrawal");
                        preparedStatement.setString(3, msg);
                        preparedStatement.setDouble(4, amount);
                        int rowsInserted = preparedStatement.executeUpdate();

                        if (rowsInserted > 0) {
                            response.put("success", true);
                            response.put("message", "Amount debited successfully");
                            // Commit the transaction
                            connection.commit();
                        } else {
                            response.put("success", false);
                            response.put("message", "Failed to insert transaction record");
                        }
                    } else {
                        response.put("success", false);
                        response.put("message", "Failed to debit amount");
                    }
                } else {
                    response.put("success", false);
                    response.put("message", "Insufficient balance");
                }
            } else {
                response.put("success", false);
                response.put("message", "Error retrieving balance");
            }


        } catch (SQLException e) {
            response.put("success", false);
            response.put("message", "SQL Exception: " + e.getLocalizedMessage());

            // Rollback the transaction if any exception occurs
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                response.put("message", "Rollback Exception: " + ex.getMessage());
            }
        } finally {
            // Reset auto-commit to true and close resources

            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
//                    connection.close();
                }
            } catch (SQLException ex) {
                response.put("success", false);
                response.put("message", "Auto-commit Exception: " + ex.getMessage());
            }
        }
        LOGGER.info("response : \n" + response);

        return response;
    }


    public Map<String, Object> creditAmount(int amount, int userId, Long accountNumber) {
        Map<String, Object> response = new HashMap<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            // Set auto-commit too false to start a transaction
            connection.setAutoCommit(false);

            // Check if the user exists
            String checkUserQuery = "SELECT * FROM user WHERE id = ?";
            preparedStatement = connection.prepareStatement(checkUserQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                response.put("success", false);
                response.put("message", "User does not exist");
                return response;
            }

            // Get the current balance of the user
            String getBalanceQuery = "SELECT balance FROM account WHERE userId = ?";
            preparedStatement = connection.prepareStatement(getBalanceQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                // Credit the amount
                String debitQuery = "UPDATE account SET balance = balance + ? WHERE userId = ?";
                preparedStatement = connection.prepareStatement(debitQuery);
                preparedStatement.setDouble(1, amount);
                preparedStatement.setInt(2, userId);
                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    // Insert transaction record
                    String insertTransactionQuery = "INSERT INTO " + transactionTable + "(accountNumber, transaction,transactionDetail, amount, date, time) VALUES (?, ?,?, ?, CURDATE(), CURTIME())";
                    preparedStatement = connection.prepareStatement(insertTransactionQuery);
                    preparedStatement.setLong(1, accountNumber); // Assuming you have the accountNumber available
                    preparedStatement.setString(2, "deposit");
                    preparedStatement.setString(3, "deposit amount by ATM");
                    preparedStatement.setDouble(4, amount);
                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        response.put("success", true);
                        response.put("message", "Amount credited successfully");
                        connection.commit();
                    } else {
                        response.put("success", false);
                        response.put("message", "Failed to insert transaction record");
                    }
                } else {
                    response.put("success", false);
                    response.put("message", "Failed to credit amount");
                }
            } else {
                response.put("success", false);
                response.put("message", "Error retrieving balance");
            }

        } catch (SQLException e) {
            response.put("success", false);
            response.put("message", "SQL Exception: " + e.getMessage());

            // Rollback the transaction if any exception occurs
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                response.put("success", false);
                response.put("message", "Rollback Exception: " + ex.getMessage());
            }
        } finally {
            // Reset auto-commit to true and close resources
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                response.put("success", false);
                response.put("message", "Auto-commit Exception: " + ex.getMessage());
            }
        }
        LOGGER.info("response : \n" + response);

        return response;
    }


    public Map<String, Object> checkBalance(int userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            String query = "SELECT * FROM " + accountTable + " WHERE userId = " + userId;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    response.put(columnName, columnValue);
                }
            }
            LOGGER.info("Balance Verified : \n" + response);
            return response;
        } catch (Exception e) {
            LOGGER.info("Exception : \n" + e.getMessage());
        }
        LOGGER.info("Balance Not Verified : \n" + response);
        return response;
    }

    public ArrayList<Transaction> getTransactions(Long accountNumber) {
        ArrayList<Transaction> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + transactionTable + " WHERE accountNumber = " + accountNumber;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            LOGGER.info("MetaData \n : " + metaData);

            while (resultSet.next()) {
                Map<String, Object> response = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    response.put(columnName, columnValue);
                }
                list.add(
                        new Transaction(
                                (String) response.get("date"),
                                (BigDecimal) response.get("amount"),
                                (int) response.get("id"),
                                (String) response.get("time"),
                                (Long) response.get("accountNumber"),
                                (String) response.get("transaction"),
                                (String) response.get("transactionDetail"))
                );
            }
            LOGGER.info("Balance Verified : \n" + list);
            return list;
        } catch (Exception e) {
            LOGGER.info("Exception : \n" + e.getMessage());
        }
        LOGGER.info("Balance Not Verified : \n" + list);
        return list;
    }

    public boolean changePin(int newPin, int id) {
        try {
            String query = "UPDATE " + userTable + " SET pin = " + newPin + " WHERE id = " + id;
            Statement statement = connection.createStatement();
            int rowsUpdated = statement.executeUpdate(query);
            LOGGER.info("Pin Updated : \n" + rowsUpdated);
            return true;
        } catch (Exception e) {
            LOGGER.info("Exception : \n" + e.getMessage());
            return false;
        }

    }

    public Map<String, Object> verifyUPI(String upiId) {
        Map<String, Object> response = new HashMap<>();
        try {
            String query = "SELECT * FROM " + upiTable + " WHERE upiId = '" + upiId + "'";
            LOGGER.info("query : \n" + query);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    response.put(columnName, columnValue);
                }
            }
            if (columnCount > 0) {
                LOGGER.info("UPI Verified : \n" + response);
                return response;
            }
        } catch (Exception e) {
            LOGGER.info("Exception : \n" + e.getMessage());
        }
        LOGGER.info("UPI Not Verified : \n" + response);
        return response;
    }

}


