package com.example.demo;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class ModelToScrap {
    private static String html;

    public static String getResults(String scrap){
        html = "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>VCard for Panorama dla Firm</title>" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "</head>\n" +
                "<body>\n";
        html += "<h2><center>Result for phrase <b>" + scrap + "</b>:</center></h2>";

        Document doc;

        try {
            doc = Jsoup.connect("https://panoramafirm.pl/" + scrap).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements elements = doc.select("script[type=\"application/ld+json\"]");

        for (Element specialistElement : elements) {

            Specialist specialist = getSpecialist(specialistElement);

            html += String.format("<div>\n" +
                            "    <p><center><b>%s</b></center></p>\n" +
                            "    <p><center><img src=\"%s\" alt=\"logo firmy\"></img></center>\n" +
                            "    <p><center>Phone: %s</center></p>\n" +
                            "    <p><center>E-mail: %s</center></p>\n" +
                            "    <p><center>Address: %s %s %s</center></p>\n" +
                            "    <p><center><button>Generate VCard</button></center></p>\n" +
                            "  </div><br/><br/>",
                    specialist.getName(), specialist.getImage(), specialist.getPhone(), specialist.getEmail(), specialist.getStreet(), specialist.getCity(), specialist.getPostalCode());
        }
        html += "</body></html>";
        return html;
    }

    private static Specialist getSpecialist(Element specialistElement) {
        Specialist specialist = new Specialist();
        String jsonString = specialistElement.html();
        JSONObject jsonObject = new JSONObject(jsonString);

        try {
            specialist.setName(jsonObject.getString("name"));
            specialist.setImage(jsonObject.getString("image"));
            specialist.setPhone(jsonObject.getString("telephone"));
            specialist.setEmail(jsonObject.getString("email"));

            JSONObject address = jsonObject.getJSONObject("address");
            specialist.setStreet(address.getString("streetAddress"));
            specialist.setCity(address.getString("addressLocality"));
            specialist.setPostalCode(address.getString("postalCode"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialist;
    }

}
