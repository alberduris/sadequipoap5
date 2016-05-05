package org.sad.practica5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.core.Instances;
import weka.core.SelectedTag;

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
	
	/*
	 * brief Realiza un barrido sobre el parámetro nu
	 * 
	 * return void Imprime resultados en fichero
	 */
	public static void nu() {

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
		//svm.setSVMType();

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

	
	
	

	
	
	
	/*
	 * brief Realiza un barrido sobre los parámetros que afectan al tipo de
	 * kernel: Polynomial
	 * 
	 * note Los parámetros son: gamma, coef0 y degree
	 * 
	 * return void Imprime resultados en fichero
	 */
	public static void sigmoidKernel() {

	
		File file = null;
		double gamma = 0.0;
		double coef0 = 0.0;
		double weightedFMeasure = -1.0;
		
		HashMap<Double,Double> listaGamma = new HashMap<>();
		HashMap<Double,Double> listaCoef0 = new HashMap<>();

		

		DataHolder.getDatosTrain().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTrain()));
		Instances trainSet = DataHolder.getDatosTrain();
		DataHolder.getDatosTest().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTest()));
		Instances testSet = DataHolder.getDatosTest();

		svm = new LibSVM();
		svm.setKernelType(new SelectedTag(LibSVM.KERNELTYPE_SIGMOID, LibSVM.TAGS_KERNELTYPE));
		svm.setGamma(0.0);
		svm.setCoef0(0.0);

		try {
			file = new File("BarridoParametros\\svm_sigmoidKernel.csv");
			file.getParentFile().mkdir();
			file.createNewFile();
			writer = new FileWriter(file, true);
			writer.write("gamma,coef0,weightedFMeasure\n");
			writer.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
				
		for(int i = 0; i < 20 ; i++){
			
			try {
				writer = new FileWriter(file, true);
				svm.buildClassifier(trainSet);
				Evaluation eval = new Evaluation(testSet);
				eval.evaluateModel(svm, testSet);
				weightedFMeasure = eval.weightedFMeasure();
				listaGamma.put(gamma, weightedFMeasure);
				writer.write(gamma + "," + coef0 + "," + weightedFMeasure + "\n");
				writer.close();

				gamma = gamma + 0.1;
				svm.setGamma(gamma);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		listaGamma = (HashMap<Double, Double>) MapUtil.sortByValue(listaGamma);
		Set<Double> setDouble = listaGamma.keySet();
		Iterator<Double> itDouble = setDouble.iterator();
		gamma = itDouble.next().doubleValue();
		svm.setGamma(gamma);


		weightedFMeasure = -1.0;
		for(int i = 0; i < 20 ; i++){
			
			try {
				writer = new FileWriter(file, true);
				svm.buildClassifier(trainSet);
				Evaluation eval = new Evaluation(testSet);
				eval.evaluateModel(svm, testSet);
				weightedFMeasure = eval.weightedFMeasure();
				listaCoef0.put(coef0, weightedFMeasure);
				writer.write(gamma + "," + coef0 + "," + weightedFMeasure + "\n");
				writer.close();

				coef0 = coef0 + 0.1;
				svm.setCoef0(coef0);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		listaCoef0 = (HashMap<Double, Double>) MapUtil.sortByValue(listaCoef0);
		setDouble = listaCoef0.keySet();
		itDouble = setDouble.iterator();
		coef0 = itDouble.next().doubleValue();
		svm.setCoef0(coef0);

		

		try {
			writer = new FileWriter(file, true);
			writer.write("VALORES OPTIMOS:" + "\n");
			writer.write("gamma,coef0\n");
			writer.write(svm.getGamma() + "," + svm.getCoef0() + "," + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public static void cost_adv() {

		File file = null;
		double cost = 7.1;
		double weightedFMeasure = -1.0;

		DataHolder.getDatosTrain().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTrain()));
		Instances trainSet = DataHolder.getDatosTrain();
		DataHolder.getDatosTest().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTest()));
		Instances testSet = DataHolder.getDatosTest();

		System.out.println("** TUNING COST **");
		System.out.println("Cost,weightedFMeasure\n");
		svm = new LibSVM();

		try {
			file = new File("BarridoParametros\\svm_cost_adv.csv");
			file.getParentFile().mkdir();
			file.createNewFile();
			writer = new FileWriter(file, true);
			writer.write("Cost,weightedFMeasure\n");
			writer.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < 19; i++) {

			try {
				writer = new FileWriter(file, true);
				svm.buildClassifier(trainSet);
				Evaluation eval = new Evaluation(testSet);
				eval.evaluateModel(svm, testSet);
				weightedFMeasure = eval.weightedFMeasure();
				System.out.println(cost + "," + weightedFMeasure + "\n");
				writer.write(cost + "," + weightedFMeasure + "\n");
				writer.close();

				cost = cost + 0.1;
				svm.setCost(cost);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void gamma_adv() {

		File file = null;
		double gamma = 0.0261;
		double weightedFMeasure = -1.0;

		DataHolder.getDatosTrain().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTrain()));
		Instances trainSet = DataHolder.getDatosTrain();
		DataHolder.getDatosTest().setClassIndex(DataHolder.getClassIndex(DataHolder.getDatosTest()));
		Instances testSet = DataHolder.getDatosTest();

		System.out.println("** TUNING GAMMA **");
		System.out.println("Gamma,weightedFMeasure\n");
		svm = new LibSVM();
		svm.setCost(8.1);

		try {
			file = new File("BarridoParametros\\svm_gamma_adv.csv");
			file.getParentFile().mkdir();
			file.createNewFile();
			writer = new FileWriter(file, true);
			writer.write("gamma,weightedFMeasure\n");
			writer.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int i = 0; i < 9; i++) {

			try {
				writer = new FileWriter(file, true);
				svm.buildClassifier(trainSet);
				Evaluation eval = new Evaluation(testSet);
				eval.evaluateModel(svm, testSet);
				weightedFMeasure = eval.weightedFMeasure();
				System.out.println(gamma + "," + weightedFMeasure + "\n");
				writer.write(gamma + "," + weightedFMeasure + "\n");
				writer.close();

				gamma = gamma + 0.0001;
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
		//gamma();
		//polynomialKernel();
		//sigmoidKernel();
		//cost_adv();
		//gamma_adv();
		

	}

}
