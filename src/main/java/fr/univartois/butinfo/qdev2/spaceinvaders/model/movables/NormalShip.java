/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 Vincent Da Silva
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;

import fr.univartois.butinfo.qdev2.spaceinvaders.view.ISpriteStore;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.SpriteStore;

/**
 * Le type NormalShip
 *
 * @author Vincent Da Silva
 *
 * @version 0.1.0
 */
public class NormalShip implements IEtatShip{

    /**
     * L'attribut spriteStore...
     */
    protected static ISpriteStore spriteStore = SpriteStore.getInstance();
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.IEtatShip#handle()
     */
    @Override
    public void handle() {
        //Ne rien faire
        
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.IEtatShip#nextState()
     */
    @Override
    public IEtatShip nextState() {
        return new InvinsibleShip();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.IEtatShip#sprite()
     */
    @Override
    public Sprite sprite() {
        return spriteStore.getSprite("vaisseau");
    }

}

