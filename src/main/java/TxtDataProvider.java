import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtDataProvider {

    private static final String PATH = "src/main/resources/testData.txt";

    protected boolean isStringValue(String elem) {
        if (elem.startsWith("\"") && elem.endsWith("\"")) {
            return true;
        }
        return false;
    }

    protected String removeQuates(String str) {
        return str.replaceAll("\"", "");
    }

    @DataProvider(name = "txtDataProvider")
    public Object[][] testData() throws IOException {
        BufferedReader readDate = new BufferedReader(new FileReader(PATH));
        ArrayList<String> data = new ArrayList<String>();
        String row;
        while ((row = readDate.readLine()) != null) {
            data.add(row);
        }

        if (!data.isEmpty()) {
            Object[][] dataProviderData;
            int colNumber = data.get(0).split(",").length;
            dataProviderData = new Object[data.size()][colNumber];

            int i = 0;
            for (String ln : data) {
                String[] parsedLine = ln.split(",");
                for (int j = 0; j < colNumber; j++) {
                    String elem = parsedLine[j];
                    if (isStringValue(elem)) {
                        dataProviderData[i][j] = removeQuates(elem);
                    } else {
                        dataProviderData[i][j] = Integer.parseInt(elem);
                    }
                }
                i++;
            }
            return dataProviderData;
        } else {
            return new Object[0][0];
        }


    }
}

