package fr.univartois.butinfo.qdev2.spaceinvaders.model;

/**
 * Le type IShotStrategy
 *
 * @author Vincent Da Silva
 *
 * @version 0.1.0
 */
public interface IShotStrategy {

	/**
	 * @param alien alien
	 */
	public void tryToShoot(IMovable alien);
	
}
