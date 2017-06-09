import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TxtDataProvider {

    protected Object[][] dataProviderData;
    private String path;

    @DataProvider(name = "txtDataProvider")
    public Object[][] testData() throws IOException {

        BufferedReader readDate = new BufferedReader(new FileReader("src/main/resources/testData.txt"));
        ArrayList<String> data = new ArrayList<String>();
        String row;
        while ((row = readDate.readLine()) != null) {
            data.add(row);
        }

        String[] rowDataSet = data.toArray(new String[data.size()]);
        int colNumber = rowDataSet[0].split(",").length;
        dataProviderData = new Object[rowDataSet.length][colNumber];

        int i;
        for (i = 0; i < rowDataSet.length; i++) {
            String rowValue = rowDataSet[i];
            String[] string = rowValue.split(",");
            for (int j = 0; j < colNumber; j++) {
                dataProviderData[i][j] = Integer.parseInt(string[j]);
            }
        }
        return dataProviderData;
    }
}

