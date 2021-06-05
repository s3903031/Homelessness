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
public class Page2 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/Page2.html";

    @Override
    public void handle(Context context) throws Exception {
        JDBCConnection jdbc = new JDBCConnection();
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html += "<head>";
        html += "    <meta charset=\"UTF-8\">";
        html += "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">";
        html += "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
        html += "    <title>Other Factors</title>";
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

        html += "<body>";
        html += "    <form action = /Page2.html method = 'post'>";
        html += "       <div class=page>";
        html += "           <p>Input an LGA name that you would like data on</p> "
                + "         <input name = 'userLGA' id = 'userLGA' placeholder = 'Geelong, Melbourne...'>";
        html += "           <p>Or, change values of attributes to filter LGA's (Defaults are min and max)</p>";
        html += "           <p>Input minimum and maximum median ages between 21 and 60</p>";
        html += "           <input type = 'number' id = 'minAge' name = 'minAge' min = '21' max = '60' value = '21'><span> years old </span>";
        html += "           <input type = 'number' id = 'maxAge' name = 'maxAge' min = '21' max = '60' value = '60'><span> years old</span>";
        html += "           <p>Input a minimum and maximum median income between $700 and $3481</p>";
        html += "           <Input type = 'number' id = 'minIncome' name = 'minIncome' min = '700' max = '3250' value = '700'> <span> $AUD </span>";
        html += "           <Input type = 'number' id = 'maxIncome' name = 'maxIncome' min = '700' max = '3250' value = '3250'> <span> $AUD </span>";
        html += "           <p>Input minimum and maximum median mortgage repayments between $0 and $3250</p>";
        html += "           <Input type = 'number' id = 'minMortgage' name = 'minMortgage' min = '0' max = '3250' value = '0'> <span> $AUD </span>";
        html += "           <Input type = 'number' id = 'maxMortgage' name = 'maxMortgage' min = '0' max = '3250' value = '3250'> <span> $AUD </span>";
        html += "           <p>Input a minimum and maximum median weekly rental price between $0 and $650</p>";
        html += "           <Input type = 'number' id = 'minRent' name = 'minRent' min = '0' max = '650' value = '0'><span> $AUD </span>";
        html += "           <Input type = 'number' id = 'maxRent' name = 'maxRent' min = '0' max = '650' value = '650'><span> $AUD </span>";
        html += "        </div><div class=page>";
        html += "           <p>Select the states you are interested in</p>";
        html += "           <input type = 'checkbox' id = 'Vic' name = 'Vic' checked>";
        html += "           <label for = 'Vic'>Vic</label>";
        html += "           <input type = 'checkbox' id = 'SA' name = 'SA' checked>";
        html += "           <label for = 'SA'>SA</label>";
        html += "           <input type = 'checkbox' id = 'NT' name = 'NT' checked>";
        html += "           <label for = 'NT'>NT</label>";
        html += "           <input type = 'checkbox' id = 'WA' name = 'WA' checked>";
        html += "           <label for = 'WA'>WA</label>";
        html += "           <input type = 'checkbox' id = 'NSW' name = 'NSW' checked>";
        html += "           <label for = 'NSW'>NSW</label>";
        html += "           <input type = 'checkbox' id = 'QLD' name = 'QLD' checked>";
        html += "           <label for = 'QLD'>QLD</label>";
        html += "           <input type = 'checkbox' id = 'Tas' name = 'Tas' checked>";
        html += "           <label for = 'Tas'>Tas</label>";
        html += "           <input type = 'checkbox' id = 'ACT' name = 'ACT' checked>";
        html += "           <label for = 'ACT'>ACT</label>";
        html += "           <input type = 'checkbox' id = 'Other' name = 'Other' checked>";
        html += "           <label for = 'Other'>Other</label>";
        html += "        </div><div class=page>";
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
        html += "             <input type = 'checkbox' name = 'U' id = 'U'>";
        html += "             <label for = 'plus'>Unknown ages</label>";
        html += "      <br><br><label for='sortBy'>Select how you want the data to be sorted (Dropdown):</label>";
        html += "      <select id='sortBy' name='sortBy'>";
        html += "          <option selected>Homeless people per 100,000 population</option> <option>%Males</option> <option>Median age</option> <option>Median Income</option> <option>Median mortgage repayments</option> <option>Median rent</option>";
        html += "      </select>";
        html += "      <input type = 'checkbox' name = 'asc' id = 'asc'>";
        html += "      <label for = 'asc'>Select if you want table sorted lowest to highest</label>";
        html += "        </div>";
        html += "        <br><br> <input type = \"submit\" value = \"show me the data!\">";
        html += "        </form>";
        html += "        </body></html>";

        String lgaName = context.formParam("userLGA");
        if (lgaName == null) {
            lgaName = "";
        }
        ArrayList<String> columnInfo = new ArrayList<String>();
        String sortBy = context.formParam("sortBy");
        String asc = context.formParam("asc");
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

        ArrayList<String> lgaFilters = new ArrayList<String>();
        lgaFilters.add(context.formParam("minAge"));
        lgaFilters.add(context.formParam("maxAge"));
        lgaFilters.add(context.formParam("minIncome"));
        lgaFilters.add(context.formParam("maxIncome"));
        lgaFilters.add(context.formParam("minMortgage"));
        lgaFilters.add(context.formParam("maxMortgage"));
        lgaFilters.add(context.formParam("minRent"));
        lgaFilters.add(context.formParam("maxRent"));
        lgaFilters.add(context.formParam("NSW"));
        lgaFilters.add(context.formParam("Vic"));
        lgaFilters.add(context.formParam("QLD"));
        lgaFilters.add(context.formParam("SA"));
        lgaFilters.add(context.formParam("WA"));
        lgaFilters.add(context.formParam("Tas"));
        lgaFilters.add(context.formParam("NT"));
        lgaFilters.add(context.formParam("ACT"));
        lgaFilters.add(context.formParam("Other"));

        String columns[] = jdbc.columnMaker(columnInfo);

        String query[] = jdbc.queryMaker1(lgaName, "2018", columns, lgaFilters, asc, sortBy, columns[2]);
        String table = jdbc.tableMakerAttributes(query);
        html += query[0];
        html += table;
        html += "</body> </html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
