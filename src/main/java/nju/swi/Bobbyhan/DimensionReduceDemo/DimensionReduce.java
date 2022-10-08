package nju.swi.Bobbyhan.DimensionReduceDemo;

import nju.swi.Bobbyhan.Utils.IOHelper;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.PrincipalComponents;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Standardize;
public class DimensionReduce {
    private static final String DATASET_PATH = "src/main/resources/dimension_reduce/dataset/cpu.arff";
    private static final String RESULT_PATH = "src/main/resources/dimension_reduce/result/";


    public Instances preProcess(Instances data) {
        try {
            Filter standardize = new Standardize();
            standardize.setInputFormat(data);
            data = Filter.useFilter(data, standardize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void pca(Instances data) {
        try {
            PrincipalComponents pca = new PrincipalComponents();
            pca.setCenterData(true);
            Ranker ranker = new Ranker();
            ranker.setNumToSelect(5);
            AttributeSelection selector = new AttributeSelection();
            selector.setSearch(ranker);
            selector.setEvaluator(pca);
            selector.SelectAttributes(data);
            selector.reduceDimensionality(data);
            IOHelper.saveTransformedFile(selector, RESULT_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        DimensionReduce dimensionReduce = new DimensionReduce();
        try {
            Instances data = IOHelper.loadARFF(DATASET_PATH);
            data = dimensionReduce.preProcess(data);
            dimensionReduce.pca(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
