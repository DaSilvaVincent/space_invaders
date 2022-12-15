/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 vin--
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;

/**
 * Le type Move
 *
 * @author vincent
 *
 * @version 0.1.0
 */
public interface IStrategieDeplacement {
    
    /**
     * @param alien alien
     * @param delta delta
     * @return alien
     */
    boolean strategieDeplacementAlien(AbstractMovable alien, long delta);
    
}

