package repositories;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DatabaseRepo<T,O> extends PrincipleRepo<T,O>{
    private static final String JDBC_URL = "jdbc:sqlite:src/data/hotel.db";
    protected static Connection conn = null;

    public DatabaseRepo(String url) {
        //JDBC_URL=url;
        getConnection();
        readFromDataBase();
    }

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    protected static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public abstract void addToDatabase(T item, O id);
    public abstract void removeFromDatabase(O id);

    protected abstract void readFromDataBase();

    @Override
    public void addToRepo(T item, O id) {
        super.addToRepo(item, id);
        addToDatabase(item,id);
    }

    @Override
    public T remFromRepo(O id) {
        T item = super.remFromRepo(id);
        removeFromDatabase(id);
        return item;
    }

}
