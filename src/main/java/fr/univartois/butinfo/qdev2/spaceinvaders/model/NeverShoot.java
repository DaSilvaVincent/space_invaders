package fr.univartois.butinfo.qdev2.spaceinvaders.model;

/**
 * Le type NeverShoot
 *
 * @author Thomas
 *
 * @version 0.1.0
 */
public class NeverShoot implements IShotStrategy {

	@Override
	public void tryToShoot(IMovable alien) {
		// Always false
	}

}
