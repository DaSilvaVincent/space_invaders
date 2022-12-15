package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;

import fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;

/**
 * Le type Shot
 *
 * @author marti
 *
 * @version 0.1.0
 */
public class Shot extends AbstractMovable {
    
    /**
     * L'attribut SPEED...
     */
    private static final double SPEED = -300;
	
	/**
	 * Cr�e une nouvelle instance de Shot.
	 * @param game Game
	 * @param xPosition double 
	 * @param yPosition double
	 * @param sprite Sprite
	 */
	public Shot(SpaceInvadersGame game, double xPosition, double yPosition, Sprite sprite) {
		super(game, xPosition, yPosition, sprite);
		verticalSpeed = SPEED;
	}

	@Override
	public void collidedWith(IMovable other) {
		other.receiveShot();
		game.removeMovable(this);
	}

	 /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#move(long)
     */
    @Override
    public boolean move(long delta) {
        // On met à jour la position de l'objet sur l'axe x.
        int limitMaxX = game.getRightLimit() - getWidth();
        double newX = updatePosition(xPosition.get(), horizontalSpeed, delta, game.getLeftLimit(), limitMaxX);
        xPosition.set(newX);

        // On met à jour la position de l'objet sur l'axe y.
        int limitMaxY = game.getBottomLimit() - getHeight();
        double newY = updatePosition(yPosition.get(), verticalSpeed, delta, game.getTopLimit(), limitMaxY);
        yPosition.set(newY);

        if ((newX == game.getLeftLimit()) || (newX == limitMaxX)) {
            // L'objet a atteint la limite sur l'axe x.
        	game.removeMovable(this);
            return false;
        }

        if ((newY == game.getTopLimit()) || (newY == limitMaxY)) {
            // L'objet a atteint la limite sur l'axe y.
        	game.removeMovable(this);
        	return false;
        }

        // L'objet n'a atteint aucune limite
        return true;
    }

	@Override
	public void hitAlien() {
		game.removeMovable(this);
	}

	@Override
	public void receiveShot() {
		game.removeMovable(this);
	}
	
	@Override
	public void tryToShoot() {
		// Always true
	}
    
}
