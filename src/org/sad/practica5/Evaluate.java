package org.sad.practica5;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.lazy.IB1;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class Evaluate {

	static IB1 ib1;
	static LibSVM svm;



	

	/*
	 * brief Realiza evaluación mediante HoldOut aplicando kNN 
	 * 
	 * note Se aplica con las configuraciones del clasificador standar
	 *  
	 * return void Imprime resultados "DetailedAccuracyByClass"
	 */
	public static void evaluarkNNHoldOut() {

		DataHolder.getDatosTrain().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTrain()));//Asignar clase
		DataHolder.getDatosTest().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTest()));//Asignar clase

		Instances trainInstances = new Instances(DataHolder.getDatosTrain());
		Instances testInstances = new Instances(DataHolder.getDatosTest());

		try {

			ib1 = new IB1();
			ib1.buildClassifier(trainInstances);

			Evaluation eval = new Evaluation(testInstances);
			eval.evaluateModel(ib1, testInstances);

			InferedModelMain.printDetailedAccuracyByClass(eval,"** IB1 - HoldOut**");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
