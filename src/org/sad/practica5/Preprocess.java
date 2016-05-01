package org.sad.practica5;

import java.util.Random;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.Standardize;
import weka.filters.unsupervised.instance.Randomize;

public class Preprocess {

	
	/*
	 * brief Aplica el filtro standardize de Weka mediante BatchFiltering y guarda los datos 
	 * 
	 * note Se aplica con las configuraciones del filtro decididas
	 * 
	 * params pData Las instancias a las que se aplica el filtro
	 * 
	 * return void 
	 */
	public static void standardize(Instances pDataTrain,Instances pDataTest) {

		Instances datosProcesadosTrain = null;
		Instances datosProcesadosTest = null;

		Standardize standardize = new Standardize();

		try {
			standardize.setInputFormat(pDataTrain);
			standardize.setInputFormat(pDataTest);


			datosProcesadosTrain = Filter.useFilter(pDataTrain, standardize);
			datosProcesadosTest = Filter.useFilter(pDataTest, standardize);

			DataHolder.setDatosTrain(datosProcesadosTrain); 
			DataHolder.setDatosTest(datosProcesadosTest);

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * brief Aplica el filtro randomize de Weka mediante BatchFiltering y guarda los datos 
	 * 
	 * note Se aplica con las configuraciones del filtro decididas
	 * 
	 * params pData Las instancias a las que se aplica el filtro
	 * 
	 * return void 
	 */
	public static void randomize(Instances pData) {

		Instances datosProcesados = null;
		Randomize randomizer = new Randomize();

		try {
			randomizer.setInputFormat(pData);

			Random rnd = new Random();
			int seed = rnd.nextInt(9999);
			randomizer.setRandomSeed(seed);

			datosProcesados = Filter.useFilter(pData, randomizer);
			DataHolder.setDatosTrainTest(datosProcesados); // los datos

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	/*
	 * brief Aplica el filtro Discretize de Weka mediante BatchFiltering y guarda los datos 
	 * 
	 * note Se aplica con las configuraciones del filtro decididas
	 * 
	 * params pData Las instancias a las que se aplica el filtro
	 * 
	 * return void 
	 */
	public static Instances discretize(Instances pData) {

		Instances datosProcesados = null;
		Discretize discretizer = new Discretize();

		try {
			discretizer.setInputFormat(pData);
			datosProcesados = Filter.useFilter(pData, discretizer);
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return datosProcesados;

	}

}
