/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 Vincent Da Silva
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;

import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;

/**
 * Le type IEtatShip
 *
 * @author Vincent Da Silva
 *
 * @version 0.1.0
 */
public interface IEtatShip {
    /**
     * 
     */
    void handle();
    
    /**
     * @return IEtatShip
     */
    IEtatShip nextState();
    
    /**
     * @return sprite
     */
    Sprite sprite();

}

