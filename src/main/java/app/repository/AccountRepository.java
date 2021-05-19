package app.repository;

import app.config.SqlConfig;
import app.model.Account;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    private static AccountRepository single_instance = null;
    private AccountRepository() {}
    public static AccountRepository getInstance() {
        if (single_instance == null)
            single_instance = new AccountRepository();

        return single_instance;
    }


    public int insertAccount(Account account) throws SQLException {
        String insert = "INSERT INTO `account` (email, password, name, surname, phoneNo, address)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1, account.getEmail());
        statement.setString(2, account.getPassword());
        statement.setString(3, account.getName());
        statement.setString(4, account.getSurname());
        statement.setString(5, account.getPhoneNo());
        statement.setString(6, account.getAddress());

        return statement.executeUpdate();
    }

    public Account getAccountByEmail(String email) throws SQLException {
        String select = "SELECT * FROM `account` WHERE email = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(select);
        statement.setString(1, email);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return new Account(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6));
        else
            return null;
    }

    public int removeAccountByEmail(String email) throws SQLException {
        String remove = "DELETE FROM `account` WHERE email = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(remove);
        statement.setString(1, email);

        return statement.executeUpdate();
    }

    public Map<String, Account> getAllAccounts() throws SQLException {
        String getAccounts = "SELECT * FROM account";

        Connection connection = SqlConfig.getDataBaseConnection();

        Map<String, Account> accounts = new HashMap<>();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getAccounts);

        while (result.next()) {
            Account currentAccount = new Account(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6));
            accounts.put(currentAccount.getEmail(), currentAccount);
        }

        return accounts;
    }

    public int updateAccountPassword(String newPassword, String email) throws SQLException {
        String update = "UPDATE account SET password=? WHERE email=?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newPassword);
        preparedStatement.setString(2, email);

        return preparedStatement.executeUpdate();
    }

    public int updateAccountName(String newName, String email) throws SQLException {
        String update = "UPDATE account SET name=? WHERE email=?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newName);
        preparedStatement.setString(2, email);

        return preparedStatement.executeUpdate();
    }

    public int updateAccountSurname(String newSurname, String email) throws SQLException {
        String update = "UPDATE account SET surname=? WHERE email=?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newSurname);
        preparedStatement.setString(2, email);

        return preparedStatement.executeUpdate();
    }

    public int updateAccountPhoneNo(String newPhoneNo, String email) throws SQLException {
        String update = "UPDATE account SET phoneNo=? WHERE email=?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newPhoneNo);
        preparedStatement.setString(2, email);

        return preparedStatement.executeUpdate();
    }

    public int updateAccountAddress(String newAddress, String email) throws SQLException {
        String update = "UPDATE account SET address=? WHERE email=?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newAddress);
        preparedStatement.setString(2, email);

        return preparedStatement.executeUpdate();
    }

}
