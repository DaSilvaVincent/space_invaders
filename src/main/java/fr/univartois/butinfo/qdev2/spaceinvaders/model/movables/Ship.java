/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 marti
 * Tous droits rï¿½servï¿½s.
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;


import java.util.Objects;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.SpaceInvadersGame;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Le type Ufo
 *
 * @author marti
 *
 * @version 0.1.0
 */
public class Ship extends AbstractMovable {

    /**
     * L'attribut etat...
     */
    private IEtatShip etat;
    
    /**
     * L'attribut changement...
     */
    private boolean changementPossible = true;
    
    /**
     * Crée une nouvelle instance de Ship.
     * 
     * @param game Game
     * @param xPosition double
     * @param yPosition double
     * @param sprite Sprite
     */
    public Ship(SpaceInvadersGame game, double xPosition, double yPosition, Sprite sprite) {
        super(game, xPosition, yPosition, sprite);
        this.etat = new NormalShip();
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
        other.bonusTaked(this);
        
    }
    
	@Override
	public void hitAlien() {
		game.playerIsDead();	
	}

	@Override
	public void receiveShot() {
	    if ((changementPossible) && (game.getLife().get() != 1)) {
    	    game.reducePlayerLife();
    	    invinsible();
  	    }
	    else if ((changementPossible) && (game.getLife().get() == 1))
	        game.reducePlayerLife();
	
	}
	
	/**
	 * 
	 */
	@Override
	public void invinsible() {
	    if(changementPossible) {
    	    this.etat = etat.nextState();
            this.setSprite(etat.sprite());
            changementPossible = false;
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(4), e -> {
                        this.etat = etat.nextState();
                        this.setSprite(etat.sprite()); 
                        changementPossible = true;
                    }));
            timeline.play();
	    }
	}

	@Override
	public void tryToShoot() {
		//Ne rien faire
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
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(etat);
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
        Ship other = (Ship) obj;
        return Objects.equals(etat, other.etat);
    }
    
    

}
