package org.sad.practica5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instance;
import weka.core.Instances;

public class DataHolder {

	private static BufferedReader reader;

	private static Instances datosTrain;
	private static Instances datosTest;
	private static Instances datosTrainTest;


	
	/*
	 * @brief Carga los datos de un archivo arff
	 * 
	 * @param String pPath El path completo del archivo arff
	 * 
	 * @return void Se guardan los datos en el objeto datosTrain de tipo Instances
	 */
	public static void loadTrainData(String pPath) {
		try {

			reader = new BufferedReader(new FileReader(pPath));
			datosTrain = new Instances(reader);
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * @brief Carga los datos de un archivo arff
	 * 
	 * @param String pPath El path completo del archivo arff
	 * 
	 * @return void Se guardan los datos en el objeto datosTest de tipo Instances
	 */
	public static void loadTestData(String pPath) {
		try {

			reader = new BufferedReader(new FileReader(pPath));
			datosTest = new Instances(reader);
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	/*
	 * @brief Carga los datos de un archivo arff
	 * 
	 * @param String pPath El path completo del archivo arff
	 * 
	 * @return void Se guardan los datos en el objeto datosTrainTest de tipo Instances
	 */
	public static void loadTrainTestData() {
		
		datosTrainTest = new Instances(datosTrain,0,datosTrain.numInstances());//Copiar objeto Instances

		Instance instancia;
		
		for(int i = 0; i < datosTest.numInstances(); i++){
			instancia = datosTest.instance(i);
			datosTrainTest.add(instancia);
		}
		

		
	}

	/*
	 * brief Devuelve los datosTrain almacenados
	 * 
	 * return datos Los datosTrain almacenados
	 */
	public static Instances getDatosTrain() {
		return datosTrain;
	}
	
	/*
	 * brief Devuelve los datosTest almacenados
	 * 
	 * return datos Los datosTest almacenados
	 */
	public static Instances getDatosTest() {
		return datosTest;
	}
	
	
	/*
	 * brief Devuelve los datosTrainTest almacenados
	 * 
	 * return datos Los datosTrainTest almacenados
	 */
	public static Instances getDatosTrainTest() {
		return datosTrainTest;
	}
	

	/*
	 * brief Asigna los datos pasados por par�metro a los datosTrain
	 * 
	 * param pData Los datos a asignar
	 * 
	 * return void Los datos estan asignados a datosTrain
	 */
	public static void setDatosTrain(Instances pData) {
		datosTrain = pData;
		String atributo = "";
		int attIndex = -1;
		boolean fin = false;
		for(int i = 0; i < datosTrain.numAttributes() && !fin; i++){
			atributo = datosTrain.instance(0).attribute(i).name();
			if(atributo.equals("classs")){
				attIndex = i;
				fin = true;
			}
		}
		datosTrain.setClassIndex(attIndex);
	
		}
	
	/*
	 * brief Asigna los datos pasados por par�metro a los datosTest
	 * 
	 * param pData Los datos a asignar
	 * 
	 * return void Los datos estan asignados a datosTest
	 */
	public static void setDatosTest(Instances pData) {
		datosTest = pData;
	}
	

	/*
	 * brief Asigna los datos pasados por par�metro a los datosTrainTest
	 * 
	 * param pData Los datos a asignar
	 * 
	 * return void Los datos estan asignados a datosTrainTest
	 */
	public static void setDatosTrainTest(Instances pData) {
		datosTrainTest = pData;
		String atributo = "";
		int attIndex = -1;
		boolean fin = false;
		for(int i = 0; i < datosTrainTest.numAttributes() && !fin; i++){
			atributo = datosTrainTest.instance(0).attribute(i).name();
			if(atributo.equals("classs")){
				attIndex = i;
				fin = true;
			}
		}
		datosTrainTest.setClassIndex(attIndex);
	
		}
	
	/*
	 * brief Imprime todas las instancias
	 * 
	 * param pData Las instancias a imprimir
	 * 
	 * return void 
	 */
	public static void printDatos(Instances pData){
		int cont = 0;
		for(int i = 0; i < pData.numInstances(); i++){
			System.out.println(pData.instance(i));
			cont++;
		}
		System.out.println("El set de datos tiene "+cont+" instancias.");
	}
	
	/*
	 * brief Imprime todos los atributos de las instancias 
	 * 
	 * param pData Las instancias de las que se imprimir�n sus atributos
	 * 
	 * return void 
	 */
	public static void printAttributos(Instances pData){
		int cont = 0;
		for(int i = 0; i < pData.numAttributes(); i++){
			System.out.println(pData.attribute(i));
			cont++;
		}
		System.out.println("El set de datos tiene "+cont+" atributos.");

		
	}

	/*
	 * brief Devuelve el indice del atributo clase
	 * 
	 * param pData Las instancias de las que se devolver� el atributo clase
	 * 
	 * return int attIndex El �ndice del atributo clase 
	 */
	public static int getClassIndex(Instances pData) {
		Instances datos = pData;
		String atributo = "";
		int attIndex = -1;
		boolean fin = false;
		for(int i = 0; i < datos.numAttributes() && !fin; i++){
			atributo = datos.instance(0).attribute(i).name();
			if(atributo.equals("classs")){
				attIndex = i;
				fin = true;
			}
		}
		return attIndex;
	
		}
		


	

	/*
	 * @brief Descripcion resumida del metodo Puedes seguir en otra linea
	 * 
	 * Descripcion detallada
	 * @param X
	 * 
	 * @return Lo que devuelve
	 * 
	 * @note Una anotacion
	 * 
	 * @see Referencia a otra funcion
	 */
}
