package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Temporary HTML as an example page.
 * 
 * Based on the Project Workshop code examples. This page currently: - Provides
 * a link back to the index page - Displays the list of movies from the Movies
 * Database using the JDBCConnection
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Page1 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/Page1.html";

    @Override

    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html += "<head>";
        html += "    <meta charset=\"UTF-8\">";
        html += "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">";
        html += "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
        html += "    <title>Overview</title>";
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html += "</head>";

        html += "<div class=\"navbar\">";
        html += "<ul>";
        html += "<li class=\"navOption\"><a href=\"/\" class=\"nav\">Home</a></li>";
        html += "<li class=\"navOption\"><a href=\"Page1.html\" class=\"nav\">Overview</a></li>";
        html += "<li class=\"navOption\"><a href=\"Page2.html\" class=\"nav\">Other Factors</a></li>";
        html += "<li class=\"navOption\"><a href=\"Page3.html\" class=\"nav\">Over Time</a></li>";
        html += "</ul>";
        html += "</div><br>";

        html += "<body>" + "<h1>Shallow dive data</h1>";
        html += "<div class=page>" + "<h2>Filters</h2>";
        html += "   <form action = '/Page1.html' method = 'post'>";
        html += "          <input type = 'checkbox' name = 'state' id = 'state>";
        html += "          <label for = 'state'>Check this box if you want data for states instead of LGA</label>";
        html += "<br><br>";
        html += "          <label for = 'lgaName'>Input a LGA name if you have one in mind</label>";
        html += "          <input type = 'text' name = 'lgaName' id = 'lgaName' placeholder = 'Greater Geelong, Darrebin...'>";
        html += "<br>";
        html += "           <p>Gender</p>";
        html += "             <label for = 'female'>Female</label>";
        html += "             <input type = 'checkbox' name = 'female' id = 'female' checked>";
        html += "             <label for = 'male'>Male</label>";
        html += "             <input type = 'checkbox' name = 'male' id = 'male' checked>";
        html += "          <p>People group</p>";
        html += "             <label for 'homeless'>People who are homeless</label>";
        html += "             <input type = 'checkbox' name = 'homeless' id = 'homeless' checked>";
        html += "             <label for 'atRisk'>People who are at risk of being homeless<label>";
        html += "             <input type = 'checkbox' name = 'atRisk' id = 'atRisk'>";
        html += "          <p>Age brackets</p>";
        html += "             <input type = 'checkbox' name = '09' id = '09' checked>";
        html += "             <label for = '10'>0-10</label>";
        html += "             <input type = 'checkbox' name = '19' id = '19' checked>";
        html += "             <label for = '20'>10-19</label>";
        html += "             <input type = 'checkbox' name = '29' id = '29' checked>";
        html += "             <label for = '30'>20-29</label>";
        html += "             <input type = 'checkbox' name = '39' id = '39' checked>";
        html += "             <label for = '40'>30-39</label>";
        html += "             <input type = 'checkbox' name = '49' id = '49' checked>";
        html += "             <label for = '50'>40-49</label>";
        html += "             <input type = 'checkbox' name = '59' id = '59' checked>";
        html += "             <label for = '60'>50-59</label>";
        html += "             <input type = 'checkbox' name = '60' id = '60' checked>";
        html += "             <label for = 'plus'>60+</label>";
        html += "             <input type = 'checkbox' name = 'U' id = 'U' checked>";
        html += "             <label for = 'plus'>Unknown ages</label>";
        html += "             <br><input type = 'checkbox' name = 'ASC' id ='ASC'>";
        html += "             <label for = 'ASC'>Sort data ascending. (default descending)</label>";
        html += "          <br><button type='submit' class='btn btn-primary'>Show me the data!</button>";
        html += "   </form>";
        html += "</div>";

        // Assign all of the user inputs variables.
        String stateSelected = context.formParam("state");
        String lgaName = context.formParam("lgaName");
        String asc = context.formParam("ASC");
        ArrayList<String> columnInfo = new ArrayList<String>();
        // All of the user input needed for columnMaker
        columnInfo.add(context.formParam("male"));
        columnInfo.add(context.formParam("female"));
        columnInfo.add(context.formParam("homeless"));
        columnInfo.add(context.formParam("atRisk"));
        columnInfo.add(context.formParam("09"));
        columnInfo.add(context.formParam("19"));
        columnInfo.add(context.formParam("29"));
        columnInfo.add(context.formParam("39"));
        columnInfo.add(context.formParam("49"));
        columnInfo.add(context.formParam("59"));
        columnInfo.add(context.formParam("60"));
        columnInfo.add(context.formParam("U"));

        // to see columnMaker working in action
        JDBCConnection jdbc = new JDBCConnection();
        String[] columns = jdbc.columnMaker(columnInfo);
        html += columns[0] + "<br>";
        html += columns[1];

        String table;
        // If the user wants to see LGA's
        if (stateSelected == null) {
            // Make the table with columns, lgaName and asc
            table = jdbc.lgaTableMaker(columns, lgaName, asc);
        } else {
            // make the table with columns.
            table = jdbc.stateTableMaker(columns);
        }
        html += table;

        html += "</body></html>";

        context.html(html);

    }
}
