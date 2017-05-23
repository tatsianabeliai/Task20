import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataProviderHelper {

    protected Object[][] DataProviderData;
    private String path;

    public DataProviderHelper(String path) {
        this.path = path;
    }

    @DataProvider(name = "dataProvider")
    public Object[][] testData() throws IOException {

        BufferedReader readDate = new BufferedReader(new FileReader("src/main/resources/testData.txt"));
        ArrayList<String> data = new ArrayList<String>();
        String row;
        while ((row = readDate.readLine()) != null) {
            data.add(row);
        }

        String[] rowDataSet = data.toArray(new String[data.size()]);
        int colNumber = rowDataSet[0].split(",").length;
        DataProviderData = new Object[rowDataSet.length][colNumber];

        int i;
        for (i = 0; i < rowDataSet.length; i++) {
            String rowValue = rowDataSet[i];
            String[] string = rowValue.split(",");
            for (int j = 0; j < colNumber; j++) {
                DataProviderData[i][j] = Integer.parseInt(string[j]);
            }
        }
        return DataProviderData;
    }
}

