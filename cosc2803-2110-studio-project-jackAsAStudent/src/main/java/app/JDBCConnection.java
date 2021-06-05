package app;

import java.util.*;

import javax.lang.model.util.ElementScanner14;

// import org.graalvm.compiler.core.common.type.ArithmeticOpTable.BinaryOp.Add;

// import jdk.internal.jshell.tool.resources.version;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database. Allows SQL
 * queries to be used with the SQLLite Databse in Java.
 * 
 * This is an example JDBC Connection that has a single query for the Movies
 * Database This is similar to the project workshop JDBC examples.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/projectBase.db";

    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /*
     * Input is what gender, datatype (homeless / atRisk) and age groups the user
     * wants to be calculated in the total. Input in form of a ArrayList of length k
     * 12. In order the indexes of the arraylist correspond to: M, F, Homeless,
     * AtRisk, age groups in ascending order (Uknown are at the end). Returns a
     * three length String array where the indexes are the following: 0: All of the
     * columns that the user has selected, with a + between each one 1: The male
     * columns of these columns ^, in the same format 2: A string that will be
     * placed above the table showing the user what they have entered.
     */
    public String[] columnMaker(ArrayList<String> inputs) {
        String[] results = new String[3];
        // The string results[2] will be shown at the top of the table after the user
        // submits
        results[2] = "Showing results for ";
        ArrayList<String> columns = new ArrayList<String>();
        // populate columns by iterating through query that returns column names.
        Connection connection = null;
        String columnsQuery = "PRAGMA table_info(HomelessAtRisk)"; // This is the query.
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet columnNames = statement.executeQuery(columnsQuery);

            while (columnNames.next()) {
                String name = columnNames.getString("name");
                if (name.equals("Name") || name.equals("Code") || name.equals("Year")) {
                    continue;
                } else {
                    columns.add(name);
                }
            }
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        if (inputs.get(0) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("M")) {
                    i.remove();
                }
            }
        } else {
            results[2] += "men ";
        }
        if (inputs.get(1) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("F")) {
                    i.remove();
                }
            }
        } else {
            // Only females
            if (inputs.get(0) == null) {
                results[2] += "women ";
                // Both genders
            } else {
                results[2] += ", women ";
            }
        }
        if (inputs.get(2) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("H")) {
                    i.remove();
                }
            }
        } else {
            results[2] += ", homeless ";
        }
        if (inputs.get(3) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("A")) {
                    i.remove();
                }
            }
        } else {
            results[2] += ", at risk of being homeless ";
        }
        results[2] += "between the ages of: ";
        if (inputs.get(4) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("09")) {
                    i.remove();
                }
            }
        } else {
            results[2] += "0 and 9";
        }
        if (inputs.get(5) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("19")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 1) != ',') {
                results[2] += ", ";
            }
            results[2] += "10 and 19";
        }
        if (inputs.get(6) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("29")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 1) != ',') {
                results[2] += ", ";
            }
            results[2] += "20 and 29";
        }
        if (inputs.get(7) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("39")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 1) != ',') {
                results[2] += ", ";
            }
            results[2] += "30 and 39";
        }
        if (inputs.get(8) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("49")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 1) != ',') {
                results[2] += ", ";
            }
            results[2] += "40 and 49";
        }
        if (inputs.get(9) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("59")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 1) != ',') {
                results[2] += ", ";
            }
            results[2] += "50 and 59";
        }
        if (inputs.get(10) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("60")) {
                    i.remove();
                }
            }
        } else {
            if (results[2].charAt(results[2].length() - 2) != ',') {
                results[2] += ", ";
            }
            results[2] += "60+ ";
        }
        if (inputs.get(11) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("U")) {
                    i.remove();
                }
            }
        } else {
            results[2] += "Unknown ages ";
        }
        // build column total from columns
        for (String column : columns) {
            results[0] += column + "+";
        }
        // make the male columns
        for (String column : columns) {
            if (column.contains("M")) {
                results[1] += column + "+";
            }
        }
        // remove last +
        for (int i = 0; i < 2; ++i) {
            if (results[i] != null) {
                if (results[i].length() > 0) {
                    results[i] = results[i].substring(4, results[i].length() - 1);
                }
            }
        }

        return results;
    }


    /*
     * Input is a string of columns made by columnMaker, an LGA name (if the user
     * did not input anything will be null) and whether the user selected
     * 'ascending'. If they did not select ascending, this will be null. Returns the
     * html of a table in a String.
     */

    public String lgaTableMaker(String[] columns, String lgaName, String asc) {
    // Make the query string with columnInfo, lgaName and asc
    String query = "SELECT name, " + columns[0] + " as Total FROM HomelessAtRisk WHERE year = '2018'";
    // If they have provided a name
    if (lgaName != null) {
        query += "AND name LIKE '%" + lgaName + "%'";
    }
    query += " ORDER BY Total ";
    if (asc == null) {
        query += "DESC";
    } else {
        query += "ASC";
    }
    String table = "<div class = 'tableFixHead'><table id = 'lgaTable'> <thead> <tr> <th>Ranking</th> <th>LGA name</th> <th>Total</th> </tr> </thead>";
    Connection connection = null;
    try {
        connection = DriverManager.getConnection(DATABASE);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        ResultSet results = statement.executeQuery(query);
        int ranking = 1;
        while (results.next()) {
            table += "<tr> <td>" + ranking + "</td><td>" + results.getString("name") + "</td><td>"
                    + results.getString("total") + "</td></tr>";
            ++ranking;
        }
        table += "</table></div>";

    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }
    return table;
}

    public String stateTableMaker(String[] columns) {
        String table = "<table id = 'stateTable' class = 'tableFixHead'> <thead> <tr> <th>Ranking</th> <th>State name</th> <th>Total</th> </tr> </thead>";
        // Use state calculator to calculate total for each state.
        int nswNumber = stateCalculator(columns, 1);
        int vicNumber = stateCalculator(columns, 2);
        int qldNumber = stateCalculator(columns, 3);
        int saNumber = stateCalculator(columns, 4);
        int waNumber = stateCalculator(columns, 5);
        int tasNumber = stateCalculator(columns, 6);
        int ntNumber = stateCalculator(columns, 7);
        int actNumber = stateCalculator(columns, 8);
        int otherNumber = stateCalculator(columns, 9);
        // Make an int 2D array with first column state identifier and second column
        // total.
        // First column is stateID, second column is total for each state.
        int[][] ranker = { { 1, nswNumber }, { 2, vicNumber }, { 3, qldNumber }, { 4, saNumber }, { 5, waNumber },
                { 6, tasNumber }, { 7, ntNumber }, { 8, actNumber }, { 9, otherNumber } };
        // Sort the ranker table by the total number.
        Arrays.sort(ranker, (a, b) -> Integer.compare(a[0], b[0]));
        // Build the table.
        for (int i = 1; i < 9; ++i) {
            String stateName = stateName(ranker[i - 1][0]);
            table += "<tr> <td> " + i + "</td> <td>" + stateName + "</td><td>" + ranker[i - 1][1] + "</td></tr>";
        }
        table += "</table>";
        return table;
    }

    public int stateCalculator(String[] columns, int stateID) {
        int total = 0;
        Connection connection = null;
        // This is the query.
        String columnsQuery = "SELECT SUM(" + columns[0] + ") AS total FROM HomelessAtRisk WHERE code LIKE '" + stateID
                + "%'";
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet columnNames = statement.executeQuery(columnsQuery);
            String result = columnNames.getString("total");
            total = Integer.parseInt(result);

        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return total;
    }

    public String stateName(int stateID) {
        String name;
        switch (stateID) {
            case 1:
                name = "New South Wales";
                break;
            case 2:
                name = "Victoria";
                break;
            case 3:
                name = "Queensland";
                break;
            case 4:
                name = "South Australia";
                break;
            case 5:
                name = "Western Australia";
                break;
            case 6:
                name = "Tasmania";
                break;
            case 7:
                name = "Northern Territory";
                break;
            case 8:
                name = "Australian Capital Territory";
                break;
            case 9:
                name = "All others";
                break;
            default:
                name = "error";
                break;
        }
        return name;
    }

    public String tableMakerAttributes(String[] queryAndCaption) {
        String table = "<p>" + queryAndCaption[1] + "</p>"
                + "<div class = 'tableFixHead'><table id = 'tableAttributes'> <thead> <th>Ranking</th> <th>LGA name</th> <th>Homeless people per 100,000 population</th> <th>% Males</th> <th>Median age</th> <th>Median Income</th> <th>Median mortgage</th> <th>Median rent</th> </thead>";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(queryAndCaption[0]);

            int i = 1;
            while (results.next()) {
                String lgaName = results.getString("Name");
                String totalRatio = results.getString("Homeless people per 100,000 population");
                String males = results.getString("%Males");
                String age = results.getString("Median age");
                String income = results.getString("Median income");
                String mortgage = results.getString("Median mortgage repayments");
                String rent = results.getString("Median rent");
                table += "<tr> <td>" + i + "</td> <td>" + lgaName + "</td> <td>" + totalRatio + "</td> <td>" + males
                        + "</td> <td>" + age + "</td> <td>" + income + "</td> <td>" + mortgage + "</td> <td>" + rent
                        + "</td> <tr>";
                ++i;
            }
            table += "</table> </div>";
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return table;
    }

    public String[] queryMaker1(String lgaName, String year, String columns[], ArrayList<String> lgaFilters, String asc,
            String sorted, String showing) {
        String[] queryAndCaption = new String[2];
        queryAndCaption[1] = showing;
        String query = "SELECT ha.Name, (((" + columns[0]
                + ")* 100000) / p.twentyeighteen) AS 'Homeless people per 100,000 population', ((" + columns[1]
                + ")*100 / (" + columns[0] + ")) AS '%Males'"
                + ", MedianIncome AS 'Median Income', Age AS 'Median age', Mortgage AS 'Median mortgage repayments', Rent AS 'Median rent' FROM HomelessAtRisk ha JOIN Income i on ha.code = i.code JOIN Population p on ha.code = p.code ";
        // make where clauses from user inputs into LGA attribute fields
        String whereClauses = " WHERE year = '" + year + "' AND (Age BETWEEN " + lgaFilters.get(0) + " and "
                + lgaFilters.get(1) + ") AND (MedianIncome BETWEEN " + lgaFilters.get(2) + " and " + lgaFilters.get(3)
                + ") AND (Mortgage BETWEEN " + lgaFilters.get(4) + " and " + lgaFilters.get(5) + ") AND (Rent BETWEEN "
                + lgaFilters.get(6) + " and " + lgaFilters.get(7) + ")";
        query += whereClauses;
        queryAndCaption[1] += whereClauses + " ";
        String states = "";
        // iterate through the users state selections and add where clauses
        int start = 8;
        if (lgaFilters.get(start) == null) {
            query += " AND ha.code NOT LIKE '1%'";
            states += "NSW, ";
        }
        if (lgaFilters.get(start + 1) == null) {
            query += " AND ha.code NOT LIKE '2%'";
            states += "Vic, ";
        }
        if (lgaFilters.get(start + 2) == null) {
            query += " AND ha.code NOT LIKE '3%'";
            states += "QLD, ";
        }
        if (lgaFilters.get(start + 3) == null) {
            query += " AND ha.code NOT LIKE '4%'";
            states += "SA, ";
        }
        if (lgaFilters.get(start + 4) == null) {
            query += " AND ha.code NOT LIKE '5%'";
            states += "WA, ";
        }
        if (lgaFilters.get(start + 5) == null) {
            query += " AND ha.code NOT LIKE '6%'";
            states += "Tas, ";
        }
        if (lgaFilters.get(start + 6) == null) {
            query += " AND ha.code NOT LIKE '7%'";
            states += "NT, ";
        }
        if (lgaFilters.get(start + 7) == null) {
            query += " AND ha.code NOT LIKE '8%'";
            states += "ACT, ";
        }
        if (lgaFilters.get(start + 8) == null) {
            query += " AND ha.code NOT LIKE '9%'";
            states += "All others ";
        }
        if (!states.equals("")) {
            queryAndCaption[1] += "In all states apart from:" + states;
        } else {
            queryAndCaption[1] += "in all states";
        }
        if (lgaName != null) {
            query += " AND ha.name LIKE '%" + lgaName + "%'";
        }
        // ordered by ?
        query += " ORDER BY \"" + sorted + "\"";
        // Ascending descending?
        if (asc == null) {
            query += " DESC";
        } else {
            query += " ASC";
        }
        queryAndCaption[0] = query;
        return queryAndCaption;
    }

    public String totalPop(String input, String radio, String order) {
        Connection connection = null;

        String add = "";
        if (radio.equals("i")) {
            add += " AND homeless > 0";
        } else if (radio.equals("d")) { 
            add += " AND homeless < 0";
        }

        if (order.equals("alp")) {
            add += " ORDER BY h8.name ASC";
        } else if (order.equals("desc")) { 
            add += " ORDER BY total DESC";
        } else if (order.equals("asc")) { 
            add += " ORDER BY total ASC";
        }

        String html = "<table class = 'tableFixHead'><thead><tr><th> Rank </th><th> LGA </th><th> Population Change </th><th> Homeless Change </th></tr></thead>";

        String query = "SELECT h8.name, twentyeighteen-twentysixteen AS total, round((((((" + input + ")*1.0))-1)*100), 2) AS homeless ";
        query += "FROM population p JOIN homelessatrisk h8 ON p.code = h8.code ";
        query += "JOIN homelessatrisk h6 ON h6.code = h8.code WHERE h6.year = '2016' AND h8.year = '2018'" + add;

        System.out.println(query);

        

        /*String columnString2018 ="";
        for (String column : columns) {
            if (column.charAt(0) == 'U') {
                if (columnString2016.charAt(3) != 'H')
                break;
            }
            columnString2018 += "h8." + column + "+";
        } if (columnString2018.length() > 0) {
            columnString2018 = columnString2018.substring(0, columnString2018.length() - 1);
        }


        String queryString = columnString2018 + ")-(" + columnString2016;

        return queryString;*/

        

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet results = statement.executeQuery(query);

            int ranking = 1;
            while (results.next()) {
                html += "<tr><td> " + ranking + "</td><td> " + results.getString("name") + "</td><td>";
                html += results.getString("total") + "</td><td>" + results.getString("homeless") + "%</td></tr>";
                ++ranking;
            }
            html += "</table></div>";

        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return html;
    }

    public ArrayList<String> columnMakerEdited(ArrayList<String> inputs) {
        ArrayList<String> columns = new ArrayList<String>();
        // populate columns by iterating through query that returns column names.
        Connection connection = null;
        String columnsQuery = "PRAGMA table_info(HomelessAtRisk)"; // This is the query.
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet columnNames = statement.executeQuery(columnsQuery);

            while (columnNames.next()) {
                String name = columnNames.getString("name");
                if (name.equals("Name") || name.equals("Code") || name.equals("Year")) {
                    continue;
                } else {
                    columns.add(name);
                }
            }
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        if (inputs.get(0) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("M")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(1) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("F")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(2) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("H")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(3) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("A")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(4) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("09")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(5) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("19")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(6) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("29")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(7) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("39")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(8) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("49")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(9) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("59")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(10) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("60")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(11) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("U")) {
                    i.remove();
                }
            }
        }

        return columns;
    }

    public String columnMakerChange(ArrayList<String> select, String arithmetic) {
        System.out.println(select);
        ArrayList<String> columnMaker = columnMakerEdited(select);
        String queryString ="";

        for (String test : columnMaker) {
            System.out.println(test);
        }
        String columnString2016 ="";

        for (String column : columnMaker) {
            if (column.length() > 2) {
                if (column.charAt(0) == 'U') {
                    if (columnString2016.charAt(3) == 'H') {
                        break;
                    }
                }
            }
            columnString2016 += "h6." + column + "+";
        }
        
        if (columnString2016.length() > 0) {
            columnString2016 = columnString2016.substring(0, columnString2016.length() - 1);
        }

        String columnString2018 ="";
        for (String column : columnMaker) {
            if (column.charAt(0) == 'U') {
                if (columnString2016.charAt(3) == 'H') {
                    break;
                }
            }
            columnString2018 += "h8." + column + "+";
        }
        
        if (columnString2018.length() > 0) {
            columnString2018 = columnString2018.substring(0, columnString2018.length() - 1);
        }


        if (arithmetic.equals("percentage")) {
            queryString = columnString2018 + ")*1.0)/((" + columnString2016;
        }

        if (arithmetic.equals("difference")) {
            queryString = columnString2018 + ")-(" + columnString2016;
        }
        return queryString;
    }

    public String changeTableMaker(String query, String totalName) {
        Connection connection = null;
        String html = "<table class = 'tableFixHead'><thead> <tr> <th> Ranking </th> <th> LGA </th> <th> " + totalName + "</th> </tr></thead>";

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            ResultSet results = statement.executeQuery(query);
            

            int rank = 1;
            while (results.next()) {
                html += "<tr> <td>" + rank + "</td> <td>" + results.getString("name") + "</td><td>";
                if (totalName.equals("Homeless:At Risk")) {
                    html += results.getString("total") + ":1</td> </tr>";
                } 
                
                else {
                    html += results.getString("total") + "</td> </tr>";
                }
                ++ rank;
            }

            html += "</table></div>";

            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return html;
    }

    public String changeSelectComplete(String query, ArrayList<String> state, String radio, String order) {

        String selectQuery = "SELECT h8.name, (" + query + ") AS total ";
        selectQuery += "FROM homelessatrisk h6 JOIN homelessatrisk h8 ON h6.code = h8.code WHERE h6.year = '2016' AND h8.year = '2018' AND total IS NOT NULL";

        selectQuery += " AND (";
        if (state.get(0) != null) {
            selectQuery += "h8.code LIKE '2%' OR ";
        }
        if (state.get(1) != null) {
            selectQuery += "h8.code LIKE '4%' OR ";
        }
        if (state.get(2) != null) {
            selectQuery += "h8.code LIKE '7%' OR ";
        }
        if (state.get(3) != null) {
            selectQuery += "h8.code LIKE '5%' OR ";
        }
        if (state.get(4) != null) {
            selectQuery += "h8.code LIKE '1%' OR ";
        }
        if (state.get(5) != null) {
            selectQuery += "h8.code LIKE '3%' OR ";
        }
        if (state.get(6) != null) {
            selectQuery += "h8.code LIKE '6%' OR ";
        }
        if (state.get(7) != null) {
            selectQuery += "h8.code LIKE '8%' OR ";
        }
        if (state.get(8) != null) {
            selectQuery += "h8.code LIKE '9%' OR ";
        }    
        
        if (selectQuery.length() > 0) {
            selectQuery = selectQuery.substring(0, selectQuery.length() - 4) + ")";        
        }

        if (radio.equals("i")) {
            selectQuery += " AND total > 0";
        } else if (radio.equals("d")) { 
            selectQuery += " AND total < 0";
        }

        if (order.equals("alp")) {
            selectQuery += " ORDER BY h8.name ASC";
        } else if (order.equals("desc")) { 
            selectQuery += " ORDER BY total DESC";
        } else if (order.equals("asc")) { 
            selectQuery += " ORDER BY total ASC";
        }

        return selectQuery;
    }

    public String stateFilter(ArrayList<String> inputs) {
        String selectedColumns = "";
        ArrayList<String> columns = new ArrayList<String>();
        // populate columns by iterating through query that returns column names.
        Connection connection = null;
        String columnsQuery = "PRAGMA table_info(HomelessAtRisk)"; // This is the query.
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet columnNames = statement.executeQuery(columnsQuery);

            while (columnNames.next()) {
                String name = columnNames.getString("name");
                if (name.equals("Name") || name.equals("Code") || name.equals("Year")) {
                    continue;
                } else {
                    columns.add(name);
                }
            }
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        if (inputs.get(0) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("M")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(1) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("F")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(2) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("H")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(3) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("A")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(4) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("09")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(5) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("19")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(6) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("29")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(7) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("39")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(8) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("49")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(9) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("59")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(10) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("60")) {
                    i.remove();
                }
            }
        }
        if (inputs.get(11) == null) {
            Iterator<String> i = columns.iterator();
            while (i.hasNext()) {
                String str = i.next();
                if (str.contains("U")) {
                    i.remove();
                }
            }
        }
        // build column total from columns
        for (String column : columns) {
            selectedColumns += column + "+";
        }
        // remove last +
        if (selectedColumns.length() > 0) {
            selectedColumns = selectedColumns.substring(0, selectedColumns.length() - 1);
        }

        return selectedColumns;
    }
    
    public String ratioMaker(ArrayList<String> select) {

        ArrayList<String> columns = columnMakerEdited(select);

        String columnString2016 ="";
        for (String column : columns) {
            if (column.charAt(0) == 'U') {
                if (columnString2016.charAt(3) != 'H')
                break;
            }
            columnString2016 += "h6." + column + "+";
        } if (columnString2016.length() > 0) {
            columnString2016 = columnString2016.substring(0, columnString2016.length() - 1);
        } 

        String columnString2018 ="";
        for (String column : columns) {
            if (column.charAt(0) == 'U') {
                if (columnString2016.charAt(3) != 'H')
                break;
            }
            columnString2018 += "h8." + column + "+";
        } if (columnString2018.length() > 0) {
            columnString2018 = columnString2018.substring(0, columnString2018.length() - 1);
        }

        String queryString = columnString2018 + ")-(" + columnString2016;

        return queryString;
    }

    public String showingResults(ArrayList<String> demographic, ArrayList<String> state, String changeDrop, String idb, String order) {

        String html = "Showing results for <b>";

        if (changeDrop.equals("p")) {
            html += "Total Population</b>";
        } else if (changeDrop.equals("h")) {
            html += "Homeless Population</b>";
        } else if (changeDrop.equals("a")) {
            html += "At Risk of Homeless Population</b>";
        } else if (changeDrop.equals("r")) {
            html += "Ratio of Change of Homeless to Change At Risk of Homeless</b>";
        } /*else if (changeDrop.equals("ah")) { // path tbc
            html += "Population"*/

        html += " in <b>";

        if (order.equals("alp")) {
            html += " Alphabetical Order</b>";
        } else if (order.equals("desc")) { 
            html += " Descending Order</b>";
        } else if (order.equals("asc")) { 
            html += " Ascending Order</b>";
        }

        html += " showing <b>";

        if (idb.equals("i")) {
            html += " Increasing Change only</b>";
        } else if (idb.equals("d")) { 
            html += " Decreasing Change only</b>";
        } else {
            html += " both Increasing and Decreasing change</b>";
        }

        html += " in ";
        int i = 0;
        int j = 0;

        for (String count : state) {
            if (count != null) {
                ++j;
            }
        }

        if (j > 8) {
            html += "<b>All States</b> ";
        } 
        
        else {
            if (state.get(0) != null) {
                html += "<b>VIC</b>, ";
                ++i;
                if (i==j-1) {
                    html = html.substring(0, html.length() - 2);        
                    html += " and ";
                }
                
            }
            if (state.get(1) != null) {
                html += "<b>SA</b>, ";
                ++i;
                if (i==j-1) {
                    html = html.substring(0, html.length() - 2);        
                    html += " and ";
                }
            }
            if (state.get(2) != null) {
                html += "<b>NT </b>, ";
                ++i;
                if (i==j-1) {
                    html = html.substring(0, html.length() - 2);        
                    html += " and ";
                }
            }
            if (state.get(3) != null) {
                html += "<b>WA</b>, ";
                ++i;
                if (i==j-1) {
                    html = html.substring(0, html.length() - 2);        
                    html += " and ";
                }
            }
            if (state.get(4) != null) {
                html += "<b>NSW</b>, ";
                ++i;
                if (i==j-1) {
                    html = html.substring(0, html.length() - 2);        
                    html += " and ";
                }
            }
            if (state.get(5) != null) {
                html += "<b>QLD</b>, ";
                ++i;
                if (i==j-1) {
                    html = html.substring(0, html.length() - 2);        
                    html += " and ";
                }
            }
            if (state.get(6) != null) {
                html += "<b>TAS</b>, ";
                ++i;
                if (i==j-1) {
                    html = html.substring(0, html.length() - 2);        
                    html += " and ";
                }
            }
            if (state.get(7) != null) {
                html += "<b>ACT</b>, ";
                ++i;
                if (i==j-1) {
                    html = html.substring(0, html.length() - 2);        
                    html += " and ";
                }
            }
            if (state.get(8) != null) {
                html += "<b>Other</b>, ";
                ++i;
                if (i==j-1) {
                    html = html.substring(0, html.length() - 2);        
                    html += " and ";
                }
            }
            
            if (html.length() > 0) {
                html = html.substring(0, html.length() - 2);        
            }
        }

        html += " for ";

        if (demographic.get(0) != null && demographic.get(1) != null) {
            html += "<b>Males</b> and <b>Females</b> ";
        } else if (demographic.get(0) != null && demographic.get(1) == null) {
            html += "<b>Males</b> ";
        } else {
            html += "<b>Females</b>";
        }

        j = 0;
        for (i = 4; i < demographic.size() - 1; ++i) {
            if (demographic.get(i) != null)
            ++j;
        }

        html += " for ages ";

        if (j == 7) {
            html += "<b>All Ages</b> ";
        }

        else if (j == 6 && demographic.get(4) == null) {
            html += "<b>10 & Above</b> ";
        }
        else if (j == 6 && demographic.get(10) == null) {
            html += "<b>59 & Below</b> ";
        }

        else if (j == 5 && demographic.get(4) == null && demographic.get(5) == null) {
            html += "<b>20 & Above</b> ";
        }

        else if (j == 5 && demographic.get(9) == null && demographic.get(10) == null) {
            html += "<b>49 & Below</b> ";
        }

        else if (j == 4 && demographic.get(4) == null && demographic.get(5) == null && demographic.get(6) == null) {
            html += "<b>30 & Above</b> ";
        }

        else if (j == 4 && demographic.get(8) == null && demographic.get(9) == null && demographic.get(10) == null) {
            html += "<b>39 & Below</b> ";
        }

        else if (j == 3 && demographic.get(4) == null && demographic.get(5) == null && demographic.get(6) == null && demographic.get(7) == null) {
            html += "<b>40 & Above</b> ";
        }

        else if (j == 3 && demographic.get(7) == null && demographic.get(8) == null && demographic.get(9) == null && demographic.get(10) == null) {
            html += "<b>29 & Below</b> ";
        }

        else if (j == 2 && demographic.get(4) == null && demographic.get(5) == null && demographic.get(6) == null && demographic.get(7) == null && demographic.get(8) == null) {
            html += "<b>50 & Above</b> ";
        }

        else if (j == 2 && demographic.get(6) == null && demographic.get(7) == null && demographic.get(8) == null && demographic.get(9) == null && demographic.get(10) == null) {
            html += "<b>19 & Below</b> ";
        }
        
        else {
            if (demographic.get(4) != null) {
                html += "<b>0 to 9</b>, ";
            }

            if (demographic.get(5) != null) {
                html += "<b>10 to 19</b>, ";
            }

            if (demographic.get(6) != null) {
                html += "<b>20 to 29</b>, ";
            }

            if (demographic.get(7) != null) {
                html += "<b>30 to 39</b>, ";
            }

            if (demographic.get(8) != null) {
                html += "<b>40 to 49</b>, ";
            }

            if (demographic.get(9) != null) {
                html += "<b>50 to 59</b>, ";
            }

            if (demographic.get(10) != null) {
                html += "<b>60+</b>, ";
            }
        }

        return html;
    }
    
}
