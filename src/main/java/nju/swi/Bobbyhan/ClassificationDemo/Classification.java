package nju.swi.Bobbyhan.ClassificationDemo;

import nju.swi.Bobbyhan.Utils.IOHelper;
import weka.classifiers.trees.J48;
import weka.core.Debug;
import weka.core.Instances;

public class Classification {
    private static final String DATASET_PATH = "src/main/resources/classification/dataset/car_data.arff";
    private static final String MODEL_PATH = "src/main/resources/classification/trainModel/model.bin";

    public static void main(String[] args) {
        ClassificationModelGenerator modelGenerator = new ClassificationModelGenerator();
        try {
            Instances data = IOHelper.loadARFF(DATASET_PATH);
            // 这里一定要设置预测属性
            data.setClassIndex(data.numAttributes() - 1);
            // 分出测试集和训练集
            int trainSize = (int) Math.round(data.numInstances() * 0.8);
            int testSize = data.numInstances() - trainSize;
            data.randomize(new Debug.Random(1));
            // 预处理
            data = modelGenerator.preProcess(data);
            Instances trainData = new Instances(data, 0, trainSize);
            Instances testData = new Instances(data, trainSize, testSize);
            // 使用训练集训练模型
            J48 model = (J48) modelGenerator.buildClassifier(trainData);
            // 模型评估
            String eval = modelGenerator.evaluateModel(model, trainData, testData);
            System.out.println(eval);
            modelGenerator.saveModel(model, MODEL_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}