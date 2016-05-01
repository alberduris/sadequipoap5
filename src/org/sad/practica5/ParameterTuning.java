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
	 * brief Realiza evaluación mediante HoldOut aplicando BayesNet
	 * 
	 * note Se aplica con las configuraciones del clasificador tuneado
	 * 
	 * return void Imprime resultados "DetailedAccuracyByClass"
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

	public static void main(String[] args) {
		System.out.println("Cargando datos...");
		// Cargar datos
		DataHolder.loadTrainData(args[0]);
		DataHolder.loadTestData(args[1]);
		System.out.println("Datos cargados");

		cost();

	}

}
