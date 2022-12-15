/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 thimothee lepetz
 * Tous droits r�serv�s.
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;

import java.util.Objects;

import fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovableFactory;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.IShotStrategy;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;

/**
 * Le type Alien
 *
 * @author thimothee lepetz
 *
 * @version 0.1.0
 */
public class Alien extends AbstractMovable implements IMovable {

    /**
     * L'attribut strategieDeplacement...
     */
    private IStrategieDeplacement strategieDeplacement;

    /**
     * L'attribut shotStrategy...
     */
    private IShotStrategy shotStrategy;

    /**
     * Donne l'attribut strategieDeplacement de cette instance de Alien.
     *
     * @return L'attribut strategieDeplacement de cette instance de Alien.
     */
    public IStrategieDeplacement getStrategieDeplacement() {
        return strategieDeplacement;
    }

    /**
     * Donne l'attribut shotStrategy de cette instance de Alien.
     *
     * @return L'attribut shotStrategy de cette instance de Alien.
     */
    public IShotStrategy getShotStrategy() {
        return shotStrategy;
    }

    /**
     * private IShotStrategy shotStrategy;
     * 
     * /**
     * La temporisation contraignant le temps entre deux tirs successifs (en
     * millisecondes).
     */
    private static final long SHOT_TEMPORIZATION = 500;

    /**
     * Le timestamp du dernier tir.
     * Il permet de s'assurer que le joueur ne tire pas trop souvent.
     */
    private long lastShot = 0;

    /**
     * L'instance de {@link IMovableFactory} permettant de créer les objets du jeu.
     */
    private IMovableFactory factory;

    /**
     * Cr�e une nouvelle instance de Alien.
     * 
     * @param game le jeu
     * @param xPosition la position x du vaisseau
     * @param yPosition la position y du vaisseau
     * @param sprite le sprite du vaisseau
     * @param shotStrategy shotStrategy 
     * @param strategieDeplacement strategieDeplacement
     */
    public Alien(SpaceInvadersGame game, double xPosition, double yPosition, Sprite sprite, IShotStrategy shotStrategy, IStrategieDeplacement strategieDeplacement) {
        super(game, xPosition, yPosition, sprite);
        this.shotStrategy = shotStrategy;
        setHorizontalSpeed(75);
        setVerticalSpeed(1);
        this.strategieDeplacement = strategieDeplacement;
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
        other.hitAlien();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#move(long)
     */
    @Override
    public boolean move(long delta) {
        shotStrategy.tryToShoot(this);
        boolean bool = super.move(delta);
        this.strategieDeplacement.strategieDeplacementAlien(this, delta);
        return bool;
    }

    @Override
    public void hitAlien() {
        //Ne rien faire
    }

    @Override
    public void receiveShot() {
        game.removeMovable(this);
        game.alienIsDead();
    }

    @Override
    public void receiveAlienShot() {
        //Ne rien faire
    }

    @Override
    public void tryToShoot() {
        boolean canShoot = true;

        for (IMovable item : game.getMovableObjects()) {
            if (item.getClass() == Alien.class || item.getClass()==AlienVieDecorator.class && item.getY() > this.getY()) {
                canShoot = false;
                break;
            }
        }

        if ((System.currentTimeMillis() - lastShot) > SHOT_TEMPORIZATION && canShoot) {
            lastShot = System.currentTimeMillis();
            IMovable shot = game.getFactory().createShotAlien(this.getX(), this.getY());
            shot.setX(shot.getX() + ((this.getWidth() - shot.getWidth()) / 2));
            shot.setY(shot.getY() + shot.getHeight() + 15);
            shot.setVerticalSpeed(-shot.getVerticalSpeed());
            game.addMovable(shot);
        }
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

    /**
     * Donne l'attribut factory de cette instance de Alien.
     *
     * @return L'attribut factory de cette instance de Alien.
     */
    public IMovableFactory getFactory() {
        return factory;
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
        result = prime * result
                 + Objects.hash(factory, lastShot, shotStrategy, strategieDeplacement);
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
        Alien other = (Alien) obj;
        return Objects.equals(factory, other.factory) && lastShot == other.lastShot
               && Objects.equals(shotStrategy, other.shotStrategy)
               && Objects.equals(strategieDeplacement, other.strategieDeplacement);
    }
    
    

}

