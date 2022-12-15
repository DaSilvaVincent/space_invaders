package fr.univartois.butinfo.qdev2.spaceinvaders.model;

/**
 * Le type AlwaysShoot
 *
 * @author Vincent Da Silva
 *
 * @version 0.1.0
 */
public class AlwaysShoot implements IShotStrategy {

	@Override
	public void tryToShoot(IMovable alien) {
		alien.tryToShoot();
	}

}
