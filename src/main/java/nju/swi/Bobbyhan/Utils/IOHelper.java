package nju.swi.Bobbyhan.Utils;

import weka.attributeSelection.AttributeSelection;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class IOHelper {
    public static Instances loadARFF(String dataSetPath) throws IOException {
        ArffLoader arffLoader = new ArffLoader();
        arffLoader.setSource(new File(dataSetPath));
        return arffLoader.getDataSet();
    }

    public static void saveTransformedFile(AttributeSelection selector, String resultPath) {
        try {
            PrintStream o = new PrintStream(new File(resultPath + "PCAResults" + ".txt"));
            System.setOut(o);
            System.out.println(Arrays.toString(selector.rankedAttributes()));
            System.out.println(Arrays.toString(selector.selectedAttributes()));
            System.out.println(selector.toResultsString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
