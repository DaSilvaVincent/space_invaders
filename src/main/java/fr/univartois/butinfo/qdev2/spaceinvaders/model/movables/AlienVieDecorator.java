/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 brigitte davault
<<<<<<< HEAD
 * Tous droits réservés.
=======
 * Tous droits rservs.
>>>>>>> aabd14eb76fcc2a3eded883edd52a6a1be7dd73f
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;

import fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;
import javafx.beans.property.ObjectProperty;

/**
 * Le type AlienVieDecorator
 *
 * @author Lepetz Thimothee
 *
 * @version 0.1.0
 */
public class AlienVieDecorator extends MovableDecorator{

    /**
     * L'attribut game...
     */
    private SpaceInvadersGame game;
    
    /**
     * Cre une nouvelle instance de AlienVieDecorator.
     * @param decorated decorated
     * @param pv pv
     */
    public AlienVieDecorator(IMovable decorated, int pv) {
        super(decorated, pv-1);
        this.game = decorated.getGame();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.MovableDecorator#receiveShot()
     */
    @Override
    public void receiveShot() {
        if(getPv() == 0) {
            game.removeMovable(this);
            game.alienIsDead();
        }
        setPv(getPv()-1);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#tryToShoot()
     */
    @Override
    public void tryToShoot() {
        // Nothing 
        
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getGame()
     */
    @Override
    public SpaceInvadersGame getGame() {
        return game;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#setSprite(fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite)
     */
    @Override
    public void setSprite(Sprite sprite) {
        decorated.setSprite(sprite);
        
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getSpriteProperty()
     */
    @Override
    public ObjectProperty<Sprite> getSpriteProperty() {
        return decorated.getSpriteProperty();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#self()
     */
    @Override
    public IMovable self() {
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#bonusTaked(fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable)
     */
    @Override
    public void bonusTaked(IMovable other) {
        // rien
        
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#invinsible()
     */
    @Override
    public void invinsible() {
        // rien
        
    }
}
