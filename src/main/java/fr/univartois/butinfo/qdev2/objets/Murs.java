/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 marti
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev2.objets;

import java.util.Objects;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.AbstractMovable;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.ISpriteStore;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.SpriteStore;

/**
 * Le type Murs
 *
 * @author marti
 *
 * @version 0.1.0
 */
public class Murs extends AbstractMovable {

    /**
     * L'attribut spriteStore...
     */
    protected static ISpriteStore spriteStore = SpriteStore.getInstance();

    /**
     * L'attribut etatMurs...
     */
    private IEtatMurs etatMurs;

    /**
     * L'attribut game...
     */
    private SpaceInvadersGame game1;

    /**
     * Crée une nouvelle instance de Murs.
     * 
     * @param game SpaceInvadersGame
     * @param xPosition double
     * @param yPosition double
     */
    public Murs(SpaceInvadersGame game, double xPosition, double yPosition) {
        super(game, xPosition, yPosition, spriteStore.getSprite("asteroide-full"));
        this.game1 = game;
        this.etatMurs = new FullLife();
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
        //
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#hitAlien()
     */
    @Override
    public void hitAlien() {
        // NULL
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#receiveShot()
     */
    @Override
    public void receiveShot() {
        if (etatMurs != null)
            this.etatMurs = etatMurs.nextState();
        if (etatMurs != null) {
            this.setSprite(getEtatMurs().sprite());
        } else {
            game1.removeMovable(this);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#tryToShoot()
     */
    @Override
    public void tryToShoot() {
        // NULL
    }

    @Override
    public boolean move(long delta) {
        return false;
    }

    /**
     * Donne l'attribut etatMurs de cette instance de Murs.
     *
     * @return L'attribut etatMurs de cette instance de Murs.
     */
    public IEtatMurs getEtatMurs() {
        return etatMurs;
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
        result = prime * result + Objects.hash(etatMurs, game1);
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
        Murs other = (Murs) obj;
        return Objects.equals(etatMurs, other.etatMurs) && Objects.equals(game1, other.game1);
    }

}
