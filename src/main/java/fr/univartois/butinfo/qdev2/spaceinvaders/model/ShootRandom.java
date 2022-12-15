package fr.univartois.butinfo.qdev2.spaceinvaders.model;

import java.util.Random;

/**
 * Le type ShootRandom
 *
 * @author Thomas
 *
 * @version 0.1.0
 */
public class ShootRandom implements IShotStrategy {

    /**
     * L'attribut random...
     */
    Random random = new Random();
    
	@Override
	public void tryToShoot(IMovable alien) {
		int variable = random.nextInt(400);
		if (variable == 0) {
			alien.tryToShoot();
		}
	}

}
