/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 marti
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev2.objets;

import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;

/**
 * Le type IEtatMurs
 *
 * @author marti
 *
 * @version 0.1.0
 */
public interface IEtatMurs {

    /**
     * @return IEtatMurs
     */
    IEtatMurs nextState();

    /**
     * @return Sprite
     */
    Sprite sprite();

}
