/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 marti
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev2.objets;

import fr.univartois.butinfo.qdev2.spaceinvaders.view.ISpriteStore;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.SpriteStore;

/**
 * Le type FullLife
 *
 * @author marti
 *
 * @version 0.1.0
 */
public class MiddleLife implements IEtatMurs {

    /**
     * L'attribut spriteStore...
     */
    protected static ISpriteStore spriteStore = SpriteStore.getInstance();

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.objets.IEtatMurs#nextState()
     */
    @Override
    public IEtatMurs nextState() {
        return new Empty();
    }

    @Override
    public Sprite sprite() {
        return spriteStore.getSprite("asteroide-cracked");
    }

}
