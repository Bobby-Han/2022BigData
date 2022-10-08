package nju.swi.Bobbyhan.ClusterDemo;

import nju.swi.Bobbyhan.Utils.IOHelper;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

public class Cluster {
    private static final String DATASET_PATH = "src/main/resources/cluster/dataset/bmw-browsers.arff";

    public static void main(String[] args) {
        try {
            Instances data = IOHelper.loadARFF(DATASET_PATH);
            SimpleKMeans kMeans = new SimpleKMeans();
            kMeans.setNumClusters(4);
            kMeans.buildClusterer(data);
            ClusterEvaluation eval = new ClusterEvaluation();
            eval.setClusterer(kMeans);
            eval.evaluateClusterer(data);
            System.out.println(eval.clusterResultsToString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
