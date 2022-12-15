/**
 * Ce fichier fait partie du projet projet-2022-2023.
 *
 * (c) 2022 brigitte davault
<<<<<<< HEAD
 * Tous droits réservés.
=======
 * Tous droits rservs.
>>>>>>> aabd14eb76fcc2a3eded883edd52a6a1be7dd73f
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables;

import fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;

/**
 * Le type ComponentDecorator
 *
 * @author thimothee lepetz
 *
 * @version 0.1.0
 */
public abstract class MovableDecorator implements IMovable{
    
    /**
     * L'attribut decorated...
     */
    protected IMovable decorated;

    /**
     * L'attribut PV...
     */
    private int pV;
    /**
     * Cre une nouvelle instance de ComponentDecorator.
     * @param decorated decorated
     * @param pV PV
     */
    protected MovableDecorator(IMovable decorated, int pV) {
        super();
        this.decorated = decorated;
        this.pV = pV;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getWidth()
     */
    @Override
    public int getWidth() {
        return decorated.getWidth();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getHeight()
     */
    @Override
    public int getHeight() {
        return decorated.getHeight();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#setX(int)
     */
    @Override
    public void setX(int xPosition) {
        decorated.setX(xPosition);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getX()
     */
    @Override
    public int getX() {
        return decorated.getX();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getXProperty()
     */
    @Override
    public DoubleProperty getXProperty() {
        return decorated.getXProperty();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#setY(int)
     */
    @Override
    public void setY(int yPosition) {
        decorated.setY(yPosition);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getY()
     */
    @Override
    public int getY() {
        return decorated.getY();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getYProperty()
     */
    @Override
    public DoubleProperty getYProperty() {
        return decorated.getYProperty();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#consume()
     */
    @Override
    public void consume() {
        decorated.consume();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#isConsumed()
     */
    @Override
    public boolean isConsumed() {
        return decorated.isConsumed();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#isConsumedProperty()
     */
    @Override
    public BooleanProperty isConsumedProperty() {
        return decorated.isConsumedProperty();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#setHorizontalSpeed(double)
     */
    @Override
    public void setHorizontalSpeed(double speed) {
        decorated.setHorizontalSpeed(speed);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getHorizontalSpeed()
     */
    @Override
    public double getHorizontalSpeed() {
        return decorated.getHorizontalSpeed();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#setVerticalSpeed(double)
     */
    @Override
    public void setVerticalSpeed(double speed) {
        decorated.setVerticalSpeed(speed);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getVerticalSpeed()
     */
    @Override
    public double getVerticalSpeed() {
        return decorated.getVerticalSpeed();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#move(long)
     */
    @Override
    public boolean move(long timeDelta) {
        return decorated.move(timeDelta);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#getSprite()
     */
    @Override
    public Sprite getSprite() {
        return decorated.getSprite();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#isCollidingWith(fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable)
     */
    @Override
    public boolean isCollidingWith(IMovable other) {
        return decorated.isCollidingWith(other);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#collidedWith(fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable)
     */
    @Override
    public void collidedWith(IMovable other) {
        decorated.collidedWith(other);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.qdev2.spaceinvaders.model.IMovable#hitAlien()
     */
    @Override
    public void hitAlien() {
        decorated.hitAlien();
    }
    
    /**
     * Modifie l'attribut pV de cette instance de MovableDecorator.
     *
     * @param pV La nouvelle valeur de l'attribut pV pour cette instance de MovableDecorator.
     */
    public void setPv(int pV) {
        this.pV = pV;
    }

    /**
     * Donne l'attribut pV de cette instance de MovableDecorator.
     *
     * @return L'attribut pV de cette instance de MovableDecorator.
     */
    public int getPv() {
        return pV;
    }
}