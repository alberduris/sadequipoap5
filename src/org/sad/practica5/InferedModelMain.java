package org.sad.practica5;

import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.net.search.local.SimulatedAnnealing;
import weka.classifiers.functions.LibSVM;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.SerializationHelper;

public class InferedModelMain {

	private static FileWriter writer;
	private static LibSVM svm;

	

	/*
	 * brief Realiza evaluación mediante HoldOut aplicando BayesNet 
	 * 
	 * note Se aplica con las configuraciones del clasificador tuneado
	 *  
	 * return void Imprime resultados "DetailedAccuracyByClass"
	 */
	public static void svmTunedHoldOut() {
		
		
		
		DataHolder.getDatosTrain().setClassIndex(DataHolder.getDatosTrain().numAttributes()-1);
		Instances trainSet = DataHolder.getDatosTrain();
		
		DataHolder.getDatosTest().setClassIndex(DataHolder.getDatosTest().numAttributes()-1);
		Instances testSet = DataHolder.getDatosTest();
		

		try {

			System.out.println("** SVM - TUNED - HOLD OUT**");

			svm = new LibSVM();
			svm = (LibSVM) SerializationHelper.read("modeloBinarioSVM");
			
			
			svm.buildClassifier(trainSet);

			Evaluation eval = new Evaluation(testSet);
			eval.evaluateModel(svm, testSet);			

			printDetailedAccuracyByClass(eval,"** SVM - TUNED - HOLD OUT**");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * brief Imprime resultados "DetailedAccuracyByClass"
	 *  
	 * params pEvaluator El evaluador a imprimir. pTitle El titulo para formatear el archivo y que sea inteligible
	 *  
	 * return void 
	 */
	public static void printDetailedAccuracyByClass(Evaluation pEval,String pTitle) {

		try {
			File file = new File("Resultados\\Infered_Model_Results.csv");
			file.getParentFile().mkdir();
			file.createNewFile();
			writer = new FileWriter(file);
			
			writer.write(pTitle+"\n");			
			System.out.println(pEval.toClassDetailsString());
			writer.write(pEval.toClassDetailsString());

			
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		
		System.out.println("Cargando datos...");
		//Cargar datos
		DataHolder.loadTrainData(args[0]);
		DataHolder.loadTestData(args[1]);
		System.out.println("Datos cargados");

		svmTunedHoldOut();
		
		System.out.println("Preprocesando datos...");
		//Preprocesado
		Preprocess.standardize(DataHolder.getDatosTrain(), DataHolder.getDatosTest());
		System.out.println("Datos preprocesados");
		
		//HoldOut
//		Evaluate.evaluarNaiveHoldOut();
		svmTunedHoldOut();

	}

}
