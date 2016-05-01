package org.sad.practica5;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class Evaluate {

	static NaiveBayes naiveBayes;
	static LibSVM svm;



	

	/*
	 * brief Realiza evaluación mediante HoldOut aplicando NaiveBayes 
	 * 
	 * note Se aplica con las configuraciones del clasificador standar
	 *  
	 * return void Imprime resultados "DetailedAccuracyByClass"
	 */
	public static void evaluarNaiveHoldOut() {

		Instances trainInstances = new Instances(DataHolder.getDatosTrainTest());

		Instances testInstances = new Instances(DataHolder.getDatosTrainTest());

		try {

			System.out.println("** NAÏVE BAYES - HoldOut**");

			naiveBayes = new NaiveBayes();
			naiveBayes.buildClassifier(trainInstances);

			Evaluation eval = new Evaluation(testInstances);
			eval.evaluateModel(naiveBayes, testInstances);

			GetModel.printDetailedAccuracyByClass(eval,"** NAÏVE BAYES - HoldOut**");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * brief Realiza evaluación mediante HoldOut aplicando BayesNet 
	 * 
	 * note Se aplica con las configuraciones del clasificador standar
	 *  
	 * return void Imprime resultados figuras de mérito principales
	 */
	public static void evaluarBayesNetHoldOut() {

		Instances trainInstances = new Instances(DataHolder.getDatosTrainTest());
		Instances testInstances = new Instances(DataHolder.getDatosTrainTest());

		try {

			System.out.println("** BAYES-NET - HoldOut**");

			svm = new LibSVM();
			svm.buildClassifier(trainInstances);

			Evaluation eval = new Evaluation(testInstances);
			eval.evaluateModel(svm, testInstances);

			SerializationHelper.write("modelPath", svm);//Modelo resultante en fichero binario

			printResultSet(eval, DataHolder.getDatosTrainTest());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * brief Realiza barrido de parámetros principales de K2 para BayesNet
	 *  
	 * return void Genera un fichero con los resultados
	 */
	
	/*
	 * brief Imprime resultados con varias figuras de merito
	 *  
	 * params pEvaluator El evaluador a imprimir. pData Las instancias evaluadas
	 * 
	 * use printConfussionMatrix
	 *  
	 * return void 
	 */
	public static void printResultSet(Evaluation pEvaluator, Instances pData) throws Exception {

		double acc = pEvaluator.pctCorrect();
		double inc = pEvaluator.pctIncorrect();
		double confMatrix[][] = pEvaluator.confusionMatrix();
		double fMea = pEvaluator.weightedFMeasure();
		double prec = pEvaluator.precision(0);
		double rec = pEvaluator.recall(0);

		System.out.println("*****************************************");
		System.out.println("Correctly Classified Instances  " + acc);
		System.out.println("Incorrectly Classified Instances  " + inc);
		System.out.println("F-Measure  " + fMea);
		System.out.println("Precision  " + prec);
		System.out.println("Recall  " + rec);

		System.out.println("************CONFUSSION MATRIX**************");
		printConfussionMatrix(confMatrix, pData);

	}

	/*
	 * brief Imprime la matriz de confusión
	 *  
	 * params confMatrix[][] Matriz de double representando la matriz de confusión. pData Las instancias evaluadas
	 *  
	 * return void 
	 */
	private static void printConfussionMatrix(double confMatrix[][], Instances pData) {

		System.out.print("\n");
		System.out.println("Confusion Matrix");
		for (int i = 0; i < pData.numClasses(); i++) {
			for (int j = 0; j < pData.numClasses(); j++) {
				System.out.print(confMatrix[i][j] + " \t");
			}
			System.out.print("\n");
		}

	}

}
