package app;

import java.io.FileWriter;
import java.io.IOException;

import java.util.*;

public class Playground {
    public static void main(String[] args) {
        JDBCConnection jdbc = new JDBCConnection();

        // tests columnMaker
        List<String> arrayBuilder = Arrays.asList("on", "on", "on", "on", "on", "on", "on", "on", "on", "on", "on",
                "on");

        ArrayList<String> mockArrayList = new ArrayList<String>();
        mockArrayList.addAll(arrayBuilder);

        String columns = jdbc.columnMaker(mockArrayList);
        System.out.println(columns);

        // tests tableMaker2
        // try {
        // FileWriter myWriter = new FileWriter("filename.txt");
        // myWriter.write(jdbc.tableMaker2(query));
        // myWriter.close();
        // System.out.println("Successfully wrote to the file.");
        // } catch (IOException e) {
        // System.out.println("An error occurred.");
        // e.printStackTrace();
        // }

    }

    public ArrayList<String> getMovies() {

        
        //ArrayList<String> movies = new ArrayList<String>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);



            String query = "SELECT * FROM movie";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) { 


            }

            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return movies;
    }

}
