package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin by writing the raw HTML into a Java
 * String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Index implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        final String questionThree = "How many homeless per 10,000 people were there in NT?";
        final String questionTwo = "How many homeless women aged 55+ were there?";
        final String questionOne = "How many total homeless were there?";
        final String answerOne = "116,000";
        final String answerTwo = "13,500";
        final String answerThree = "600";
        final String answerPlaceholder = "<p>Answer the questions to see the results!</p>";

        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + "<title>Homepage</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        // Add the body
        /*
         * Navigation Bar html += "<ul>" + "<li><a href=Index.html>Home</a></li>" +
         * "</ul>";
         */

        // NAVIGATION BAR - copy and paste to each page when complete.
        html += "<div class=\"navbar\">";
        html += "<ul>";
        html += "<li class=\"navOption\"><a href=\"/\" class=\"nav\">Home Page</a></li>";
        html += "<li class=\"navOption\"><a href=\"Page1.html\" class=\"nav\">Shallow Dive</a></li>";
        html += "<li class=\"navOption\"><a href=\"Page2.html\" class=\"nav\">Deep Dive</a></li>";
        html += "</ul>";
        html += "</div>";

        // Box 1: Knowledge Test
        html += "<div class=\"knowledgeTestBox\">";
        html += "<img class=\"knowledgeTestBG\" src=/pexels-pixabay-220365.jpg>";

        html += "<div class=\"knowledgeTestContent\">";
        html += "   <h1>Test Your Knowledge</h1>";

        html += "       <form action = / method = 'post'>";
        html += "           <div class = \"form-group\">";
        html += "               <p><label for = 'firstQ'>" + questionOne + "</label>";
        html += "                   <input id = 'firstQ' name = 'firstQ'>";
        html += "               <p><label for = 'secondQ'>" + questionTwo + "</label>";
        html += "                   <input id = 'secondQ' name = 'secondQ'>";
        html += "               <p><label for = 'thirdQ'>" + questionThree + "</label>";
        html += "                   <input id = 'thirdQ' name = 'thirdQ'>";
        html += "               <input type = 'checkbox' id = 'test'>";
        html += "           </div>";

        html += "   <button type='submit' class='btn btn-primary'>Submit</button>";
        html += "</form> <br> <br>";

        // Show the answer of the user.
        String firstAnswer = context.formParam("firstQ");
        if (firstAnswer == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html += answerPlaceholder;
        } else {
            html += "<p> You answered: " + firstAnswer + "</p>" + "<p>But the real answer is: " + answerOne + "</p>";
        }
        html += "<br>";
        String secondAnswer = context.formParam("secondQ");
        if (secondAnswer == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html += answerPlaceholder;
        } else {
            html += "<p> You answered: " + secondAnswer + "</p>" + "<p>But the real answer is: " + answerTwo + "</p>";
        }
        html += "<br>";
        String thirdAnswer = context.formParam("thirdQ");
        if (thirdAnswer == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html += answerPlaceholder;
        } else {
            html += "<p> You answered: " + thirdAnswer + "</p>" + "<p>But the real answer is: " + answerThree + "</p>";
        }
        html += "</div>";
        html += "</div>";

        // Box 2: LGA
        html += "<div class=\"seeYourLGA\">";
        html += "<h1>Overview of Homelessness </h1>";
        html += "<p>There are plenty of people who do not have a place to call home, or at risk of losing their homes.<br>";
        html += "Here, you will be able to see how many people in your <b>Local Government Area</b> or <b>State</b> are impacted.</p>";
        html += "<p> You will be able to filter by:";
        html += "<ul> <li>Gender</li>";
        html += "<li>Age</li>";
        html += "<li>Local Government Area or State</li></p>";
        html += "<a href = Page1.html>Insert Arrow</a>";
        html += "</div>";

        // Box 3: Indepth Data
        html += "<div class=\"deeperDive\">";
        html += "<h1>Further Look into Homelessness</h1>";
        html += "<p>Homelessness is closely related to finances<br>";
        html += "Here, you will be able to see how many people in your <b>Local Government Area</b> or <b>State</b> are impacted.</p>";
        html += "<a href = Page2.html>Homelessness and Other Factors</a><br><br>";
        html += "<a href = Page3.html>Homelessness Over Time</a>";
        html += "</div>";

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
