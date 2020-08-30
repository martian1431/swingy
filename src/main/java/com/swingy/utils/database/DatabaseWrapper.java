package com.swingy.utils.database;

import com.swingy.model.hero.Hero;
import com.swingy.model.hero.HeroEnum;
import com.swingy.utils.factory.HeroFactory;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TODO debug
import static com.swingy.utils.Colors.*;
import static com.swingy.utils.Log.log;

//TODO convert to wrapper
public class DatabaseWrapper {
    private static DatabaseWrapper instance;
    private static Connection conn = Conn.getConnection();
    private DatabaseWrapper() {}

    public static DatabaseWrapper getInstance() {
        if (instance == null) {
            instance = new DatabaseWrapper();
        }
        return instance;
    }

    public void setupDatabase() {
//        if (!dbExists("Swingy.db")) {
//            System.out.println("creating database");
//            createDatabase();
//            createTable();
//        } else {
//            System.out.println("database exists");
//
//        }
        createTable();
    }

    private void createTable() {
        String sql = String.format("CREATE TABLE IF NOT EXISTS heroes (\nheroID INTEGER PRIMARY KEY," +
                "\nheroName TEXT NOT NULL UNIQUE ,\nheroClass TEXT NOT NULL ,\nheroLevel INTEGER NOT NULL ," +
                "\nheroExperience INTEGER NOT NULL ,\nheroHP INTEGER NOT NULL ,\nheroAttack INTEGER NOT NULL ," +
                "\nheroDefense INTEGER NOT NULL \n );");

        try {
            //create table
            Statement stmt = conn.createStatement();
//            TODO check to see if table was created
            stmt.executeUpdate(sql);
            System.out.println("table added");
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nError: cannot create table");
            System.exit(0);
        }
    }

    private void createDatabase() {
        try {
            if (conn != null){
                DatabaseMetaData dbMeta = conn.getMetaData();
                System.out.println("meta is " + dbMeta);
                System.out.println("A new database has been created: Swingy.db");
            }
        } catch(SQLException e){
            System.out.println(e.getMessage() + "\nError: cannot create database");
            System.exit(0);
        }
    }

    private boolean dbExists(String dbName) {
        File dbFile = new File (dbName);
        return dbFile.exists();
    }

    public boolean heroExists(String name) throws SQLException {
        boolean exists = false;
//        System.out.println("Does hero exists??");
//        System.out.println(line);
        String sql = "SELECT * FROM heroes WHERE heroName='" + name + "'";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        if (resultSet.next()) {
            exists = true;
        }
        System.out.println(exists);
//        return resultSet.next();
        return exists;
    }

    public void insertHero(Hero newHero) throws SQLException {
        System.out.println("Let's insert a hero to database...");
//        System.out.println(newHero.getType());
//        System.out.println(newHero.getName());
//        System.out.println(newHero.getLevel());
//        System.out.println(newHero.getExperience());
//        System.out.println(newHero.getHitPoints());
//        System.out.println(newHero.getAttack());
//        System.out.println(newHero.getDefense());
        String sql = "INSERT INTO heroes (" +
                "heroName, heroClass, " +
                "heroLevel, heroExperience, " +
                "heroHP, heroAttack, heroDefense) " +
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, newHero.getName());
        pstmt.setString(2, newHero.getType());
        pstmt.setInt(3, newHero.getLevel());
        pstmt.setInt(4, newHero.getExperience());
        pstmt.setInt(5, newHero.getHitPoints());
        pstmt.setInt(6, newHero.getAttack());
        pstmt.setInt(7, newHero.getDefense());
        System.out.println(pstmt.execute());
//        if (pstmt.execute()) {
//            System.out.println("Yeboooo");
//        } else {
//            System.out.println("Hero not added to database");
//        }
    }

    public int numberOfHeroes() throws SQLException {
        int rowCount = 0;
        String sql = "SELECT * FROM heroes";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next())
            rowCount++;
//        System.out.println("hshsh" + rowCount);
        return rowCount;
    }

    public void retrieveAllHeroes() throws SQLException {
        String sql = "SELECT * FROM heroes";
        Statement stmt = conn.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        log(ANSI_YELLOW + "::: SELECT YOUR HERO");
        while (resultSet.next()) {
            log(ANSI_CYAN + resultSet.getString("heroName") + ANSI_RESET);
        }
    }

    public Hero retrieveHeroData(String trim) {
        return HeroFactory.newHero("test", HeroEnum.DEADPOOL);
    }

    public void updateHero(Hero hero) {
    }

    public List<Hero> retrieveDatabase() {
        return new ArrayList<Hero>();
    }
}
