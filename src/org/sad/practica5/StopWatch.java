package org.sad.practica5;

public class StopWatch {

	private final long start;

	public StopWatch() {
		// Constructora, al ser invocada la clase mediante un new, almacena la
		// hora de su creaci�n.
		start = System.currentTimeMillis();
	}

	/**
	 * M�todo que devuelve el tiempo que ha pasado desde la creaci�n de el
	 * objeto de esta clase y la ejecuci�n de este m�todo.
	 * 
	 * @return
	 */
	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - start) / 1000.0;

	}
}
