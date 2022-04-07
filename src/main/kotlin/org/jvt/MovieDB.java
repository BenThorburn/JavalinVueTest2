package org.jvt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONObject;


public class MovieDB implements AutoCloseable {

    //allows us to easily change the database used
    private static final String JDBC_CONNECTION_STRING = "jdbc:sqlite:./data/dbmovies.db";

    //allows us to re-use the connection between queries if desired
    private Connection connection = null;

    /**
     * Creates an instance of the DB object and connects to the database
     */
    public MovieDB() {
        try {
            connection = DriverManager.getConnection(JDBC_CONNECTION_STRING);
        } catch (SQLException sqle) { error(sqle); }
    }

    public int getNumberOfEntries() {
        int result = -1;

        try {
            Statement s = connection.createStatement();
            ResultSet results = s.executeQuery("SELECT COUNT(*) AS count FROM movies_metadata");

            while(results.next()) //will only execute once, because SELECT COUNT(*) returns just 1 number
                result = results.getInt(results.findColumn("count"));

        } catch (SQLException sqle) { error(sqle); }

        return result;
    }




    public String getTitles() {
        String result = "";
        JSONArray ja = new JSONArray();
        try {
            Statement s = connection.createStatement();
            ResultSet results = s.executeQuery("SELECT title FROM movies_metadata");
            while(results.next()) {
                JSONObject jo = new JSONObject();
                /*
                JSONObject jo = new JSONObject();
                jo.put("Title: ", results.getString("title"));
                ja.put(jo);
                //result += " \n" + results.getString("title");
                 */
            }
        }
        catch (SQLException sqle) {
            error(sqle);
        }
        return result;
    }






    public String getGenres(String title) {
        String result = "";
        try {
            PreparedStatement s = connection.prepareStatement("SELECT genres FROM movies_metadata "
                    + "WHERE title = ?");
            s.setString(1,title);
            ResultSet results = s.executeQuery();

            while(results.next()) {
                result = results.getString("genres") ;
            }
            JSONArray jsonArray = new JSONArray(result);
            result="";
            for(int i=0; i<jsonArray.length();i++){
                JSONObject genre = jsonArray.getJSONObject(i);
                result += "\n|" + genre.getString("name");
            }
        }
        catch (SQLException sqle) {
            error(sqle);
        }
        return result;
    }

    public String getFilmsByGenres(String genre) {
        String result = "";
        try {
            PreparedStatement s = connection.prepareStatement("SELECT title FROM movies_metadata "
                    + "WHERE genres LIKE ?");
            s.setString(1, "%" + genre + "%");
            ResultSet results = s.executeQuery();
            while(results.next()) {
                result += "\n" + results.getString("title") ;
            }
            //JSONArray jsonArray = new JSONArray(result);
            //result="";
            //for(int i=0; i<jsonArray.length();i++){
            //	JSONObject genre = jsonArray.getJSONObject(i);
            //	result += "\n|" +genre.getString("name");
            //}
        }
        catch (SQLException sqle) {
            error(sqle);
        }
        return result;
    }

    public String getSselectedYearFilms(String year) {
        String result = "";
        try {
            PreparedStatement s = connection.prepareStatement("SELECT title FROM movies_metadata "
                    + "WHERE release_date Like ?");
            s.setString(1, year + "%");
            ResultSet results = s.executeQuery();

            while(results.next()) {
                result += " \n|" + results.getString("title") ;
            }
        }
        catch (SQLException sqle) {
            error(sqle);
        }
        return result;
    }

    public String getFilmsInBudgetRange(String min, String max) {
        String result = "";
        try {
            PreparedStatement s = connection.prepareStatement("SELECT title FROM movies_metadata "
                    + "WHERE budget > ? AND budget < ?");
            s.setString(1, min);
            s.setString(2, max);

            ResultSet results = s.executeQuery();

            while(results.next()) {
                result += " \n|" + results.getString("title") ;
            }
        }
        catch (SQLException sqle) {
            error(sqle);
        }
        return result;
    }

    public String getFilmsInRevenueRange(String min, String max) {
        String result = "";
        try {
            PreparedStatement s = connection.prepareStatement("SELECT title FROM movies_metadata "
                    + "WHERE revenue > ? AND revenue < ?");
            s.setString(1, min);
            s.setString(2, max);

            ResultSet results = s.executeQuery();

            while(results.next()) {
                result += " \n|" + results.getString("title") ;
            }
        }
        catch (SQLException sqle) {
            error(sqle);
        }
        return result;
    }

    //cast column renamed casta.
    public String getFilmsStarringActor(String actor) {
        String result = "";
        try {
            PreparedStatement s = connection.prepareStatement("SELECT title FROM movies_metadata "
                    + "JOIN credits ON movies_metadata.id = credits.id "
                    + "WHERE casta LIKE ?");
            s.setString(1, "%" + actor + "%");
            ResultSet results = s.executeQuery();
            while(results.next()) {
                result += " \n|" + results.getString("title") ;
            }
        }
        catch (SQLException sqle) {
            error(sqle);
        }
        return result;
    }

    public String getFilmsByDirector(String director) {
        String result = "";
        try {
            PreparedStatement s = connection.prepareStatement("SELECT title FROM movies_metadata "
                    + "JOIN credits ON movies_metadata.id = credits.id "
                    + "WHERE crew LIKE ?");
            s.setString(1, "%" + director + "%");
            ResultSet results = s.executeQuery();
            while(results.next()) {
                result += " \n|" + results.getString("title") ;
            }
        }
        catch (SQLException sqle) {
            error(sqle);
        }
        return result;
    }


    /**
     * Closes the connection to the database, required by AutoCloseable interface.
     */
    @Override
    public void close() {
        try {
            if ( !connection.isClosed() ) {
                connection.close();
            }
        }
        catch(SQLException sqle) {
            error(sqle);
        }
    }

    /**
     * Prints out the details of the SQL error that has occurred, and exits the programme
     * @param sqle Exception representing the error that occurred
     */
    private void error(SQLException sqle) {
        System.err.println("Problem Opening Database! " + sqle.getClass().getName());
        sqle.printStackTrace();
        System.exit(1);
    }

}
