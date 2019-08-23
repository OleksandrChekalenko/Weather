import javafx.scene.text.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    //22.08 Четверг погода сегодня
    //22.08
    //\d{2}\.\d{2}
    private static Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}");

    private static Document getPage() throws IOException {
        //        String url = "http://pogoda.meta.ua/ua/Kyivska/Kyivskiy/Kyiv/";
        String url = "http://pogoda.spb.ru/";
        Document page = Jsoup.parse(new URL(url), 3000);
        return page;
    }

    private static String getDateFromString(String stringDate) throws Exception {
        Matcher matcher = pattern.matcher(stringDate);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Can`t extract date from string!");
    }

    public static void main1() {
        try {
//        System.out.println(getPage());
            Document page = getPage();

            //css query language
            Element tableWigth = page.select("table[class=wt]").first();
//        System.out.println(tableWigth);
            Elements names = tableWigth.select("tr[class=wth");
            Elements values = tableWigth.select("tr[valign=top]");

            int index = 0;
            for (Element name : names) {
                String dataString = name.select("th[id=dt").text();
                String date = getDateFromString(dataString);
                System.out.println(date + "  Явление     Температура     Давление     Влажность     Ветер");
                int iterationCount = print4Values(values, index);
                index += iterationCount;
                if (index >= 3) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int print4Values(Elements values, int index) {
        int iterationCount = 2;
        String textLabel = "";

        if (index == 0) {
            Element valueLn = values.get(3);
            boolean isMorning = valueLn.text().contains("Утро");
            if (isMorning) {
                iterationCount = 3;
            }
        }
        for (int i = 0; i < iterationCount; i++) {
            Element valueLine = values.get(index + i);
            for (Element td : valueLine.select("td")) {
                System.out.print(td.text() + "   ");
                textLabel += td.text() + "   ";
            }
            Text lab = new Text(textLabel);
            lab.setLayoutX(20);
            lab.setLayoutY(20 * (i + 1 + index));

            App.getRoot().getChildren().add(lab);
            textLabel = "";
            System.out.println();
        }
        System.out.println();
        return iterationCount;
    }
}
