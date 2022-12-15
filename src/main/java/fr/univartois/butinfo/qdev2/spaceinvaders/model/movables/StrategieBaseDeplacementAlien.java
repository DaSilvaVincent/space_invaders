/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 vin--
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;

/**
 * Le type MoveBase
 *
 * @author vin--
 *
 * @version 0.1.0
 */
public class StrategieBaseDeplacementAlien implements IStrategieDeplacement{
    
    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.IStrategieDeplacement#strategieDeplacementAlien(long)
     */
    @Override
    public boolean strategieDeplacementAlien(AbstractMovable alien, long delta) {
        
        // On met Ã  jour la position de l'objet sur l'axe x.
        int limitMaxX = alien.game.getRightLimit() - alien.getWidth();
        double newX = AbstractMovable.updatePosition(alien.xPosition.get(), alien.horizontalSpeed, delta, alien.game.getLeftLimit(), limitMaxX);
        alien.xPosition.set(newX);

        // On met Ã  jour la position de l'objet sur l'axe y.
        int limitMaxY = alien.game.getBottomLimit() - alien.getHeight();
        double newY = AbstractMovable.updatePosition(alien.yPosition.get(), alien.verticalSpeed, delta, alien.game.getTopLimit(), limitMaxY);
        alien.yPosition.set(newY);

        if ((newX == alien.game.getLeftLimit()) || (newX == limitMaxX)) {
            // L'objet a atteint la limite sur l'axe x.
            alien.yPosition.set(alien.yPosition.get()+35);
            alien.setHorizontalSpeed(-alien.horizontalSpeed*1);
            return false;
        }
        
        if (( newY == alien.game.getTopLimit()) || (newY == limitMaxY)) {
            // L'objet a atteint la limite sur l'axe y.
            alien.game.alienReachedPlanet();
            return false;
        }

        // L'objet n'a atteint aucune limite
        return true;
    }
    
}

