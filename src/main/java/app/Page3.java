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
public class Page3 implements Handler {

    public static final String URL = "/Page3.html";

    @Override
    public void handle(Context context) throws Exception {
        JDBCConnection jdbc = new JDBCConnection();

        String html = "";

        html += "<html>";

        html +=     "<head>";
        html +=         "<meta charset=\"UTF-8\">";
        html +=         "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">";
        html +=         "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
        html +=         "<title>Homeless</title>";
        html +=         "<link rel='stylesheet' type='text/css' href='common.css' />";
        html +=     "</head>";


        html +=     "<body>";
        html +=         "<form action = /Page3.html method = 'post' class=form>";
        html +=             "<div>";
        html +=                 "<label for='changeDrop'> What would you like to see change data for?* </label>";
        html +=                 "<select id='changeDrop' name='changeDrop'>";
        html +=                     "<option value=\"none\" selected disabled hidden> Select an Option </option>";
        html +=                     "<option value=\"p\"> Population (no demographic filters available) </option>";
        html +=                     "<option value=\"h\"> Homeless </option>";
        html +=                     "<option value=\"a\"> At Risk of Homeless </option>";
        html +=                     "<option value=\"r\"> Homeless:At Risk of Homeless </option>";
        html +=                 "</select>";

        html +=             "<p><b> Sort by: </b>";
        html +=                 "<label for='ALP'> Alphabetical Order </label>";
        html +=                     "<input type='radio' id='ALP' name='Order' value='alp' checked>";
        html +=                 "<label for='DESC'> Descending Order </label>";
        html +=                     "<input type='radio' id='DESC' name='Order' value='desc'>";
        html +=                 "<label for='ASC'> Ascending Order </label>";
        html +=                     "<input type='radio' id='ASC' name='Order' value='asc'>";
        html +=             "</p>";

        html +=             "<p><b> Show me change that is: </b>";
        html +=                 "<label for='INC'> Only Increasing </label>";
        html +=                     "<input type='radio' id='INC' name='IDB' value=i>";
        html +=                 "<label for='DEC'> Only Decreasing </label>";
        html +=                     "<input type='radio' id='DEC' name='IDB' value=d>";
        html +=                 "<label for='BOTH'> Both </label>";
        html +=                     "<input type='radio' id='BOTH' name='IDB' value=b checked>";
        html +=             "</p>";

        html +=             "<br><b> Demographic Filters (excluding population): </b>";

        html +=             "<p> Include the following states:";
        html +=                 "<label for='VIC'> VIC </label>";
        html +=                     "<input type='checkbox' id='VIC' name='VIC' checked>";
        html +=                 "<label for='SA'> SA </label>";
        html +=                     "<input type='checkbox' id='SA' name='SA' checked>";
        html +=                 "<label for='NT'> NT </label>";
        html +=                     "<input type='checkbox' id='NT' name='NT' checked>";
        html +=                 "<label for='WA'> WA </label>";
        html +=                     "<input type='checkbox' id='WA' name='WA' checked>";
        html +=                 "<label for='NSW'> NSW </label>";
        html +=                     "<input type='checkbox' id='NSW' name='NSW' checked>";
        html +=                 "<label for='QLD'> QLD </label>";
        html +=                     "<input type='checkbox' id='QLD' name='QLD' checked>";
        html +=                 "<label for='TAS'> TAS </label>";
        html +=                     "<input type='checkbox' id='TAS' name='TAS' checked>";
        html +=                 "<label for='ACT'> ACT </label>";
        html +=                     "<input type='checkbox' id='ACT' name='ACT' checked>";
        html +=                 "<label for='OTHER'> Other </label>";
        html +=                     "<input type='checkbox' id='OTHER' name='OTHER' checked>";
        html +=             "</p>";

        html +=             "<p> Include the following genders:";
        html +=                 "<label for='MALE'> Male </label>";
        html +=                     "<input type='checkbox' id='MALE' name='MALE' checked>";
        html +=                 "<label for='FEMALE'> Female </label>";
        html +=                     "<input type='checkbox' id='FEMALE' name='FEMALE' checked>";
        html +=             "</p>";

        html +=             "<p> Include the following age groups:";
        html +=                 "<label for='09'> Under 9 </label>";
        html +=                     "<input type='checkbox' id='09' name='09' checked>";
        html +=                 "<label for='19'> 10 to 19 </label>";
        html +=                     "<input type='checkbox' id='19' name='19' checked>";
        html +=                 "<label for='29'> 20 to 29 </label>";
        html +=                     "<input type='checkbox' id='29' name='29' checked>";
        html +=                 "<label for='39'> 30 to 39 </label>";
        html +=                     "<input type='checkbox' id='39' name='39' checked>";
        html +=                 "<label for='49'> 40 to 49 </label>";
        html +=                     "<input type='checkbox' id='49' name='49' checked>";
        html +=                 "<label for='59'> 50 to 59 </label>";
        html +=                     "<input type='checkbox' id='59' name='59' checked>";
        html +=                 "<label for='60'> Over 60 </label>";
        html +=                     "<input type='checkbox' id='60' name='60' checked>";
        html +=                 "<label for='U'> Unknown </label>";
        html +=                     "<input type='checkbox' id='U' name='U' checked>";
        html +=             "</p>";

        html +=             "<button type='submit' class='btn btn-primary'>Results</button>";

        //html +=             "<input type = 'submit' value = 'Results'>";
        
        html +=             "</div>";
        html +=         "</form>";

        ArrayList<String> select = new ArrayList<String>();
        select.add(context.formParam("MALE"));
        select.add(context.formParam("FEMALE"));

        String changeDrop = context.formParam("changeDrop");
        if (changeDrop == null) {
            html = html + "<div class=page><i>Please choose from the drop down menu.</i></div>";
        } else if (changeDrop.equals("p")) {
            select.add("on");
            select.add(null);
        } else if (changeDrop.equals("h")) {
            select.add("on");
            select.add(null);
        } else if (changeDrop.equals("a")) {
            select.add(null);
            select.add("on");
        } else if (changeDrop.equals("r")) {
            select.add("on");
            select.add(null);
        } else if (changeDrop.equals("t")) {
            select.add("on");
            select.add(null);
        }

        select.add(context.formParam("09"));
        select.add(context.formParam("19"));
        select.add(context.formParam("29"));
        select.add(context.formParam("39"));
        select.add(context.formParam("49"));
        select.add(context.formParam("59"));
        select.add(context.formParam("60"));
        select.add(context.formParam("U")); // ????

        ArrayList<String> state = new ArrayList<String>();
        state.add(context.formParam("VIC"));
        state.add(context.formParam("SA"));
        state.add(context.formParam("NT"));
        state.add(context.formParam("WA"));
        state.add(context.formParam("NSW"));
        state.add(context.formParam("QLD"));
        state.add(context.formParam("TAS"));
        state.add(context.formParam("ACT"));
        state.add(context.formParam("OTHER"));

        String idb = context.formParam("IDB");
        String order = context.formParam("Order");

        if (changeDrop == null) {
            System.out.println();

        } else if (changeDrop.equals("p")) {
            String arithmetic = "percentage";
            String query = jdbc.columnMakerChange(select, arithmetic);
            String popTable = jdbc.totalPop(query, idb, order);
            String results = jdbc.showingResults(select, state, changeDrop, idb, order);
            html += "<div class=page>" + results + "</div>" + popTable;

        } else if (changeDrop.equals("h")) {
            String arithmetic = "difference";
            String query = jdbc.columnMakerChange(select, arithmetic);
            String selectComplete = jdbc.changeSelectComplete(query, state, idb, order);
            String totalName = "Total Change";
            String htmlTable = jdbc.changeTableMaker(selectComplete, totalName);
            
            String results = jdbc.showingResults(select, state, changeDrop, idb, order);
            html += "<div class=page>" + results + "</div>" + htmlTable;

        } else if (changeDrop.equals("a")) {
            String arithmetic = "difference";
            String query = jdbc.columnMakerChange(select, arithmetic);
            String selectComplete = jdbc.changeSelectComplete(query, state, idb, order);
            String totalName = "Total Change";
            String htmlTable = jdbc.changeTableMaker(selectComplete, totalName);
            String results = jdbc.showingResults(select, state, changeDrop, idb, order);
            html += "<div class=page>" + results + "</div>" + htmlTable;

        } else if (changeDrop.equals("r")) {
            String queryHomeless = jdbc.ratioMaker(select);
            select.set(2, null);
            select.set(3, "on");
            String queryAtRisk = jdbc.ratioMaker(select);
            String query = "CAST((((((" + queryHomeless + "))*1.0)/(((" + queryAtRisk + "))*1.0))-1)*100 AS INT)";
            String ratioQuery = jdbc.changeSelectComplete(query, state, idb, order);
            String totalName = "Homeless:At Risk";
            String htmlTable = jdbc.changeTableMaker(ratioQuery, totalName);
            String results = jdbc.showingResults(select, state, changeDrop, idb, order);
            html += "<div class=page>" + results + "</div>" + htmlTable;

        } /* else if (changeDrop.equals("t")) {

            //jdbc.columnMakerChange(select, state);


            
            String queryHomeless = jdbc.ratioMaker(select);
            select.set(2, null);
            select.set(3, "on");
            String queryAtRisk = jdbc.ratioMaker(select);
            String query = "CAST((((((" + queryHomeless + "))*1.0)/(((" + queryAtRisk + "))*1.0))-1)*100 AS INT)";
            String ratioQuery = jdbc.changeSelectComplete(query, state, idb, order);
            String totalName = "Homeless:At Risk";
            String htmlTable = jdbc.changeTableMaker(ratioQuery, totalName);
            String results = jdbc.showingResults(select, state, changeDrop, idb, order);
            html += "<div class=page>" + results + "</div>" + htmlTable;
        }*/

        html +=     "</body>";
        html += "</html>";
        context.html(html);
    }
}
