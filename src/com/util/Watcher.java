package com.util;

/**
 * Implementación propia de la clase Observer del patrón Observer.
 * 
 * @author Borja
 */
public interface Watcher {
	public void update(Watchable o, Object arg);

}