/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 Thimothee Lepetz
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;

import fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;

/**
 * Le type BonusPV
 *
 * @author Thimothee Lepetz
 *
 * @version 0.1.0
 */
public class BonusPV extends AbstractBonus {

    /**
     * L'attribut SPEED...
     */
    private static final double SPEED = 100;

    /**
     * Crée une nouvelle instance de BonusPV.
     * 
     * @param game game
     * @param xPosition xPosition
     * @param yPosition yPosition
     * @param sprite sprite
     */
    public BonusPV(SpaceInvadersGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        verticalSpeed = SPEED;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#collidedWith(fr.univartois
     * .butinfo.qdev2.spaceinvaders.model.IMovable)
     */
    @Override
    public void collidedWith(IMovable other) {
        // r
    }


    @Override
    public void bonusTaked(IMovable other) {
        game.addPlayerLife();
        game.removeMovable(this);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#move(long)
     */
    @Override
    public boolean move(long delta) {
        // On met Ã  jour la position de l'objet sur l'axe x.
        int limitMaxX = game.getRightLimit() - getWidth();
        double newX = updatePosition(xPosition.get(), horizontalSpeed, delta, game.getLeftLimit(),
                limitMaxX);
        xPosition.set(newX);

        // On met Ã  jour la position de l'objet sur l'axe y.
        int limitMaxY = game.getBottomLimit() - getHeight();
        double newY = updatePosition(yPosition.get(), verticalSpeed, delta, game.getTopLimit(),
                limitMaxY);
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

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#hitAlien()
     */
    @Override
    public void hitAlien() {
        // Alien can't be buff
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#receiveShot()
     */
    @Override
    public void receiveShot() {
        // Can't be shot
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#tryToShoot()
     */
    @Override
    public void tryToShoot() {
        // Can't shoot
    }

}
