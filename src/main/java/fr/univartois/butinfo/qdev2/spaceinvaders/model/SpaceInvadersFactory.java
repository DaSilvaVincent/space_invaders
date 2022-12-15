/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 vin--
 * Tous droits r�serv�s.
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model;

import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.Alien;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.BonusInv;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.BonusPV;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.BonusShot;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.IStrategieDeplacement;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.Ship;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.ShootPuissantProxy;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.Shot;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.StrategieAleatoireDeplacementAlien;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.StrategieBaseDeplacementAlien;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.StrategieDifficileDeplacementAlien;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.ISpriteStore;

/**
 * Le type SpaceInvadersFactory
 *
 * @author vin--
 *
 * @version 0.1.0
 */
public class SpaceInvadersFactory implements IMovableFactory{

    /**
     * L'attribut spriteStore...
     */
    protected ISpriteStore spriteStore;
    
    /**
     * L'attribut game...
     */
    protected SpaceInvadersGame game;

    /**
     * L'attribut deplacementBase...
     */
    private IStrategieDeplacement deplacementBase = new StrategieBaseDeplacementAlien();
    
    /**
     * L'attribut deplacementAleatoire...
     */
    private IStrategieDeplacement deplacementAleatoire = new StrategieAleatoireDeplacementAlien();
    
    /**
     * L'attribut deplacementDifficile...
     */
    private IStrategieDeplacement deplacementDifficile = new StrategieDifficileDeplacementAlien();
        
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovableFactory#setSpriteStore(fr.univartois.butinfo.qdev2.spaceinvaders.view.ISpriteStore)
     */
    @Override
    public void setSpriteStore(ISpriteStore spriteStore) {
        this.spriteStore = spriteStore;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovableFactory#setGame(fr.univartois.butinfo.qdev2.spaceinvaders.model.SpaceInvadersGame)
     */
    @Override
    public void setGame(SpaceInvadersGame game) {
        this.game = game;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovableFactory#createAlien(int, int)
     */
    @Override
    public IMovable createAlien(int x, int y, IShotStrategy shotStrategy, IStrategieDeplacement strategieDeplacement) {
        return new Alien(game,x,y,spriteStore.getSprite("alien"),shotStrategy,strategieDeplacement);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovableFactory#createShip(int, int)
     */
    @Override
    public IMovable createShip(int x, int y) {
        return new Ship(game,x,y,spriteStore.getSprite("vaisseau")); 
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovableFactory#createShot(int, int)
     */
    @Override
    public IMovable createShot(int x, int y) {
        return new Shot(game,x,y,spriteStore.getSprite("tir")); 
    }
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovableFactory#createShot(int, int)
     */
    @Override
    public IMovable createShotAlien(int x, int y) {
        return new Shot(game,x,y,spriteStore.getSprite("tir-alien")); 
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovableFactory#createSuperShot(int, int)
     */
    @Override
    public IMovable createSuperShot(int x, int y) {
        return new ShootPuissantProxy(game,x,y,spriteStore.getSprite("super-tir"), 3); 
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovableFactory#createBonus(int, int)
     */
    @Override
    public IMovable createBonus(int x, int y, String sprite) {
        if(sprite.equals("bonus-vie"))
            return new BonusPV(game,x,y,spriteStore.getSprite(sprite));
        else if(sprite.equals("bonus-tir"))
            return new BonusShot(game,x,y,spriteStore.getSprite(sprite));
        else
            return new BonusInv(game,x,y,spriteStore.getSprite(sprite));
    }

    /**
     * Donne l'attribut deplacementBase de cette instance de SpaceInvadersFactory.
     *
     * @return L'attribut deplacementBase de cette instance de SpaceInvadersFactory.
     */
    public IStrategieDeplacement getDeplacementBase() {
        return deplacementBase;
    }

    /**
     * Donne l'attribut deplacementAleatoire de cette instance de SpaceInvadersFactory.
     *
     * @return L'attribut deplacementAleatoire de cette instance de SpaceInvadersFactory.
     */
    public IStrategieDeplacement getDeplacementAleatoire() {
        return deplacementAleatoire;
    }

    /**
     * Donne l'attribut deplacementDifficile de cette instance de SpaceInvadersFactory.
     *
     * @return L'attribut deplacementDifficile de cette instance de SpaceInvadersFactory.
     */
    public IStrategieDeplacement getDeplacementDifficile() {
        return deplacementDifficile;
    }

}

