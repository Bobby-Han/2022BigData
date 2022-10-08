package nju.swi.Bobbyhan.ClassificationDemo;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.Standardize;
// 生成训练模型
public class ClassificationModelGenerator {

    // 预处理
    public Instances preProcess(Instances data) {
        try {
            Filter replaceMissingValues = new ReplaceMissingValues();
            replaceMissingValues.setInputFormat(data);
            data = Filter.useFilter(data, replaceMissingValues);
            Filter standardize = new Standardize();
            standardize.setInputFormat(data);
            data = Filter.useFilter(data, standardize);
            Filter normalize = new Normalize();
            normalize.setInputFormat(data);
            data = Filter.useFilter(data, normalize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    // 构建分类器
    public Classifier buildClassifier(Instances data) {
        J48 j48 = new J48();
        try {
            j48.buildClassifier(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j48;
    }

    // 模型评估
    public String evaluateModel(Classifier model, Instances trainData, Instances testData) {
        Evaluation evaluation = null;
        try {
            evaluation = new Evaluation(trainData);
            // 使用测试集评估
            evaluation.evaluateModel(model, testData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (evaluation != null) {
            return evaluation.toSummaryString("", true);
        } else {
            return "";
        }
    }

    // 保存模型
    public void saveModel(Classifier model, String savePath) {
        try {
            SerializationHelper.write(savePath, model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}