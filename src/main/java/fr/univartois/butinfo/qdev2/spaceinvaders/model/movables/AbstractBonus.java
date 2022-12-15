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
 * Le type IBonus
 *
 * @author Thimothee Lepetz
 *
 * @version 0.1.0
 */
public abstract class AbstractBonus extends AbstractMovable {

    /**
     * Crée une nouvelle instance de AbstractBonus.
     * @param game game
     * @param xPosition xPosition
     * @param yPosition yPosition
     * @param sprite sprite
     */
    protected AbstractBonus(SpaceInvadersGame game, double xPosition, double yPosition,
            Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
    }
    
    /**
     * @param other other 
     * 
     */
    @Override
    public abstract void bonusTaked(IMovable other);
}

