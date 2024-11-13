package com.ordwen.odailyquests.quests.player.progression.storage.sql;

import com.ordwen.odailyquests.tools.PluginLogger;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class SQLManager {

    protected HikariDataSource hikariDataSource;

    protected LoadProgressionSQL loadProgressionSQL;
    protected SaveProgressionSQL saveProgressionSQL;

    /**
     * Check if a table exists in database.
     *
     * @param connection connection to check.
     * @param tableName  name of the table to check.
     * @return true if table exists.
     * @throws SQLException SQL errors.
     */
    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[]{"TABLE"});

        return resultSet.next();
    }

    public void setupTables() {
        final Connection connection = getConnection();
        try {
            if (!tableExists(connection, "PLAYER")) {

                String str = "create table PLAYER\n" +
                        "  (\n" +
                        "     PLAYERNAME char(32)  not null  ,\n" +
                        "     PLAYERTIMESTAMP bigint not null,  \n" +
                        "     ACHIEVEDQUESTS tinyint not null, \n" +
                        "     TOTALACHIEVEDQUESTS int not null, \n" +
                        "     constraint PK_PLAYER primary key (PLAYERNAME)\n" +
                        "  );";

                PreparedStatement preparedStatement = connection.prepareStatement(str);
                preparedStatement.execute();

                preparedStatement.close();
                PluginLogger.info("Table 'Player' created in database.");
            }
            if (!tableExists(connection, "PROGRESSION")) {

                String str = "create table PROGRESSION\n" +
                        "  (\n" +
                        "     PRIMARYKEY int auto_increment  ,\n" +
                        "     PLAYERNAME char(32)  not null  ,\n" +
                        "     PLAYERQUESTID smallint  not null  ,\n" +
                        "     QUESTINDEX int  not null  ,\n" +
                        "     ADVANCEMENT int  not null  ,\n" +
                        "     ISACHIEVED bit  not null  ,\n" +
                        "     primary key (PRIMARYKEY) ,\n" +
                        "     constraint UNIQUE_PLAYERNAME_PLAYERQUESTID unique (PLAYERNAME, PLAYERQUESTID)" +
                        "  ); ";

                PreparedStatement preparedStatement = connection.prepareStatement(str);
                preparedStatement.execute();

                preparedStatement.close();
                PluginLogger.info("Table 'Progression' created in database.");
            }
            connection.close();
        } catch (SQLException e) {
            PluginLogger.error(e.getMessage());
        }
    }

    /**
     * Close database connection.
     */
    public void close() {
        this.hikariDataSource.close();
    }

    /**
     * Get database connection.
     *
     * @return database Connection.
     */
    public Connection getConnection() {
        if (this.hikariDataSource != null && !this.hikariDataSource.isClosed()) {
            try {
                return this.hikariDataSource.getConnection();
            } catch (SQLException e) {
                PluginLogger.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Test database connection.
     *
     * @throws SQLException SQL errors.
     */
    protected void testConnection() throws SQLException {
        Connection con = getConnection();
        if (con.isValid(1)) {
            PluginLogger.info("Plugin successfully connected to database " + con.getCatalog() + ".");
            con.close();
        } else PluginLogger.error("IMPOSSIBLE TO CONNECT TO DATABASE");
    }

    /**
     * Get load progression SQL instance.
     *
     * @return load progression SQL instance.
     */
    public LoadProgressionSQL getLoadProgressionSQL() {
        return loadProgressionSQL;
    }

    /**
     * Get save progression SQL instance.
     *
     * @return save progression SQL instance.
     */
    public SaveProgressionSQL getSaveProgressionSQL() {
        return saveProgressionSQL;
    }
}
