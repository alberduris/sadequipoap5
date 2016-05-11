package org.sad.practica5;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
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

		DataHolder.getDatosTrain().setClassIndex(DataHolder.getDatosTrain().numAttributes() - 1);
		Instances trainSet = DataHolder.getDatosTrain();

		DataHolder.getDatosTest().setClassIndex(DataHolder.getDatosTest().numAttributes() - 1);
		Instances testSet = DataHolder.getDatosTest();

		try {

			System.out.println("** SVM - TUNED - HOLD OUT**");

			svm = new LibSVM();
			svm.setSVMType(new SelectedTag(LibSVM.SVMTYPE_C_SVC, LibSVM.TAGS_SVMTYPE));
			svm.setCost(3.2);
			svm.setEps(0.025);
			svm.setGamma(0.0042);
			svm.setKernelType(new SelectedTag(LibSVM.KERNELTYPE_RBF, LibSVM.TAGS_KERNELTYPE));
			svm.setDebug(false);

			svm.buildClassifier(trainSet);

			Evaluation eval = new Evaluation(testSet);
			eval.evaluateModel(svm, testSet);

			printDetailedAccuracyByClass(eval, "** SVM - TUNED - HOLD OUT**");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * brief Imprime resultados "DetailedAccuracyByClass"
	 * 
	 * params pEvaluator El evaluador a imprimir. pTitle El titulo para
	 * formatear el archivo y que sea inteligible
	 * 
	 * return void
	 */
	public static void printDetailedAccuracyByClass(Evaluation pEval, String pTitle) {

		double weightedFMeasure = pEval.weightedFMeasure();
		double acc = pEval.pctCorrect();
		
		
		
		try {
			File file = new File("Resultados\\Infered_Model_Results.csv");
			file.getParentFile().mkdir();
			file.createNewFile();
			writer = new FileWriter(file, true);

			writer.write(pTitle + "\n");
			
			//Accuracy
			System.out.println("Accuracy:    "+acc);
			writer.write("Accuracy:    "+acc+"\n");
			
			//DetailedAccuracyByClass
			System.out.println(pEval.toClassDetailsString());
			writer.write(pEval.toClassDetailsString());
			
			//ConfussionMatrix
			printConfussionMatrix(pEval.confusionMatrix(), DataHolder.getDatosTrain());
			writer.write(confussionMatrixToString(pEval.confusionMatrix(), DataHolder.getDatosTrain()));
			
			//WFM
			System.out.println("\nWeighted F-Measure:    "+weightedFMeasure+"\n");
			writer.write("\nWeighted F-Measure:    "+weightedFMeasure+"\n\n");

			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

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
	
	/*
	 * brief Devuelve un string con la matriz de confusión
	 *  
	 * params confMatrix[][] Matriz de double representando la matriz de confusión. pData Las instancias evaluadas
	 *  
	 * return void 
	 */
	private static String confussionMatrixToString(double confMatrix[][], Instances pData) {

		String conf = "";
		
		conf = "\n";
		conf = conf + "Confusion Matrix:\n";
		for (int i = 0; i < pData.numClasses(); i++) {
			for (int j = 0; j < pData.numClasses(); j++) {
				conf = conf + confMatrix[i][j] + " \t";
			}
			conf = conf + "\n";
		}
		
		return conf;

	}

	public static void main(String[] args) {
		
		double cargarDatos = -1;
		double preprocesado = -1;
		double evaluacion = -1;
		
		StopWatch crono = new StopWatch();

		// Cargar datos
		DataHolder.loadTrainData(args[0]);
		DataHolder.loadTestData(args[1]);
		
		cargarDatos = crono.elapsedTime();
		crono = new StopWatch();

		// Preprocesado
		Preprocess.standardize(DataHolder.getDatosTrain(), DataHolder.getDatosTest());
		preprocesado = crono.elapsedTime();
		crono = new StopWatch();
		
		// HoldOut
		svmTunedHoldOut();
		
		evaluacion = crono.elapsedTime();

		System.out.println("Tiempo ejecución:\n");
		System.out.println("Cargar datos: "+cargarDatos);
		System.out.println("Preprocesado: "+preprocesado);
		System.out.println("Evaluación: "+evaluacion);
		System.out.println("\nTiempo total: "+(cargarDatos+preprocesado+evaluacion));

	}

}
