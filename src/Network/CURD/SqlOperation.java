package Network.CURD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlOperation {
    protected static void insertData(Connection connection, int id, int cId, String itemName, int itemQty) throws SQLException {
        String insertQuery = "INSERT INTO shopping (id, c_id, item_name, item_qty) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

        try {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, cId);
            preparedStatement.setString(3, itemName);
            preparedStatement.setInt(4, itemQty);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully!");
            }
        } catch (Throwable var10) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var9) {
                    var10.addSuppressed(var9);
                }
            }
            throw var10;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }

    protected static void updateData(Connection connection, int id, String newItemName, int newItemQty) throws SQLException {
        String updateQuery = "UPDATE shopping SET item_name = ?, item_qty = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

        try {
            preparedStatement.setString(1, newItemName);
            preparedStatement.setInt(2, newItemQty);
            preparedStatement.setInt(3, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data updated successfully!");
            }
        } catch (Throwable var9) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var8) {
                    var9.addSuppressed(var8);
                }
            }

            throw var9;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }

    protected static void deleteData(Connection connection, int id) throws SQLException {
        String deleteQuery = "DELETE FROM shopping WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

        try {
            preparedStatement.setInt(1, id);
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data deleted successfully!");
            }
        } catch (Throwable var7) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }
            }

            throw var7;
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

    }

    protected static void retrieveData(Connection connection) throws SQLException {
        String query = "SELECT * FROM shopping";
        Statement statement = connection.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            try {
                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int cId = resultSet.getInt("c_id");
                    String itemName = resultSet.getString("item_name");
                    int itemQty = resultSet.getInt("item_qty");
                    System.out.println("ID: " + id + ", c_id: " + cId + ", Name: " + itemName + ", Qty: " + itemQty);
                }
            } catch (Throwable var10) {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Throwable var11) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Throwable var8) {
                    var11.addSuppressed(var8);
                }
            }

            throw var11;
        }

        if (statement != null) {
            statement.close();
        }

    }
}
