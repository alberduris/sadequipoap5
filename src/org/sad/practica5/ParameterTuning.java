package org.sad.practica5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;

public class ParameterTuning {

	private static LibSVM svm;
	private static FileWriter writer;

	/*
	 * brief Realiza un barrido sobre el parámetro cost
	 * 
	 * return void Imprime resultados en fichero
	 */
	public static void cost() {

		File file = null;
		double cost = 0.1;
		double weightedFMeasure = -1.0;

		DataHolder.getDatosTrain().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTrain()));
		Instances trainSet = DataHolder.getDatosTrain();
		DataHolder.getDatosTest().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTest()));
		Instances testSet = DataHolder.getDatosTest();

		System.out.println("** TUNING COST **");
		System.out.println("Cost,weightedFMeasure\n");
		svm = new LibSVM();

		try {
			file = new File("BarridoParametros\\svm_cost.csv");
			file.getParentFile().mkdir();
			file.createNewFile();
			writer = new FileWriter(file, true);
			writer.write("Cost,weightedFMeasure\n");
			writer.close();


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		for (int i = 0; i < 50; i++) {

			try {
				writer = new FileWriter(file, true);
				svm.buildClassifier(trainSet);
				Evaluation eval = new Evaluation(testSet);
				eval.evaluateModel(svm, testSet);
				weightedFMeasure = eval.weightedFMeasure();
				System.out.println(cost + "," + weightedFMeasure + "\n");
				writer.write(cost + "," + weightedFMeasure + "\n");
				writer.close();

				cost = cost + 0.5;
				svm.setCost(cost);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	/*
	 * brief Realiza un barrido sobre el parámetro eps
	 * 
	 * return void Imprime resultados en fichero
	 */
	public static void eps() {

		File file = null;
		double eps = 0.5;
		double weightedFMeasure = -1.0;

		DataHolder.getDatosTrain().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTrain()));
		Instances trainSet = DataHolder.getDatosTrain();
		DataHolder.getDatosTest().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTest()));
		Instances testSet = DataHolder.getDatosTest();

		System.out.println("** TUNING EPS **");
		System.out.println("eps,weightedFMeasure\n");
		svm = new LibSVM();

		try {
			file = new File("BarridoParametros\\svm_eps.csv");
			file.getParentFile().mkdir();
			file.createNewFile();
			writer = new FileWriter(file, true);
			writer.write("eps,weightedFMeasure\n");
			writer.close();


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		for (int i = 0; i < 50; i++) {

			try {
				writer = new FileWriter(file, true);
				svm.buildClassifier(trainSet);
				Evaluation eval = new Evaluation(testSet);
				eval.evaluateModel(svm, testSet);
				weightedFMeasure = eval.weightedFMeasure();
				System.out.println(eps + "," + weightedFMeasure + "\n");
				writer.write(eps + "," + weightedFMeasure + "\n");
				writer.close();

				eps = eps + 0.5;
				svm.setEps(eps);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	/*
	 * brief Realiza un barrido sobre el parámetro gamma
	 * 
	 * return void Imprime resultados en fichero
	 */
	public static void gamma() {

		File file = null;
		double gamma = 0.0;
		double weightedFMeasure = -1.0;

		DataHolder.getDatosTrain().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTrain()));
		Instances trainSet = DataHolder.getDatosTrain();
		DataHolder.getDatosTest().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTest()));
		Instances testSet = DataHolder.getDatosTest();

		System.out.println("** TUNING GAMMA **");
		System.out.println("Gamma,weightedFMeasure\n");
		svm = new LibSVM();

		try {
			file = new File("BarridoParametros\\svm_gamma.csv");
			file.getParentFile().mkdir();
			file.createNewFile();
			writer = new FileWriter(file, true);
			writer.write("gamma,weightedFMeasure\n");
			writer.close();


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		for (int i = 0; i < 50; i++) {

			try {
				writer = new FileWriter(file, true);
				svm.buildClassifier(trainSet);
				Evaluation eval = new Evaluation(testSet);
				eval.evaluateModel(svm, testSet);
				weightedFMeasure = eval.weightedFMeasure();
				System.out.println(gamma + "," + weightedFMeasure + "\n");
				writer.write(gamma + "," + weightedFMeasure + "\n");
				writer.close();

				gamma = gamma + 0.1;
				svm.setGamma(gamma);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		System.out.println("Cargando datos...");
		// Cargar datos
		DataHolder.loadTrainData(args[0]);
		DataHolder.loadTestData(args[1]);
		System.out.println("Datos cargados");

		//cost();
		//eps();
		gamma();

	}

}
