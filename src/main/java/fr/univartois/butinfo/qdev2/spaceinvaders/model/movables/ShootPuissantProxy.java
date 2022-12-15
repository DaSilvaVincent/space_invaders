/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 marti
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;

import java.util.Objects;

import fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;

/**
 * Le type ShootPuissantProxy
 *
 * @author marti
 *
 * @version 0.1.0
 */
public class ShootPuissantProxy extends Shot {

    /**
     * L'attribut shoot...
     */
    private Shot shoot;

    /**
     * L'attribut cpt...
     */
    private int cpt;

    /**
     * L'attribut nbAlien...
     */
    private int nbAlien;

    /**
     * Crée une nouvelle instance de ShootPuissantProxy.
     * @param game game
     * @param xPosition xPosition
     * @param yPosition yPosition
     * @param sprite sprite
     * @param nbAlien nbAlien
     */
    public ShootPuissantProxy(SpaceInvadersGame game, double xPosition, double yPosition, Sprite sprite, int nbAlien) {
        super(game, xPosition, yPosition, sprite);
        this.nbAlien = nbAlien;
        this.cpt = 0;
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
        if (cpt >= nbAlien) {
            other.receiveShot();
            game.removeMovable(this);
        }
        other.receiveShot();
        cpt++;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#hitAlien()
     */
    @Override
    public void hitAlien() {
        if (cpt >= nbAlien) {
            game.removeMovable(this);
        }
        cpt++;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#receiveShot()
     */
    @Override
    public void receiveShot() {
        hitAlien();
    }

    /**
     * Donne l'attribut shoot de cette instance de ShootPuissantProxy.
     *
     * @return L'attribut shoot de cette instance de ShootPuissantProxy.
     */
    public Shot getShoot() {
        return shoot;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(cpt, nbAlien, shoot);
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ShootPuissantProxy other = (ShootPuissantProxy) obj;
        return cpt == other.cpt && nbAlien == other.nbAlien && Objects.equals(shoot, other.shoot);
    }
    
    

}
