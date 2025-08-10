
abstract class ModelTrainer{
    public  final void trainPipeline(String dataPath) {
        loadData(dataPath) ;
        preProcessData();
        trainModel();
        evaluateModel();
        saveModel();
    }
    protected void loadData(String dataPath) {
        System.out.println("[Common] Loading data from " + dataPath);
    }
    protected void preProcessData() {
        System.out.println("[Common] Preprocessing data");
    }
    protected abstract void trainModel() ;
    protected abstract void evaluateModel() ;
    protected void saveModel() {
        System.out.println("[Common] Saving model to disk");
    }

}

class ClassificationModelTrainer extends ModelTrainer{

    @Override
    protected void trainModel() {
        System.out.println("[Classification] Training model");
    }

    @Override
    protected void evaluateModel() {
        System.out.println("[Classification] Evaluating model");
    }
}

class RegressionModelTrainer extends ModelTrainer{

    @Override
    protected void trainModel() {
        System.out.println("[Regression] Training model");
    }

    @Override
    protected void evaluateModel() {
        System.out.println("[Regression] Evaluating model");
    }

    @Override
    protected void saveModel() {
        System.out.println("[Regression] Saving model to disk");
    }
}


public class TemplateMethodPattern {
    public static void main(String[] args) {

        System.out.println("---Classification Model---") ;
        ModelTrainer modelTrainer1 = new ClassificationModelTrainer() ;
        modelTrainer1.trainPipeline("/path/data.csv") ;

        System.out.println("--------------------") ;

        System.out.println("---Regression Model---") ;
        ModelTrainer modelTrainer2 = new RegressionModelTrainer() ;
        modelTrainer2.trainPipeline("/path/file.csv"); ;
    }
}