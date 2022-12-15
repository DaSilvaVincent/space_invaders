/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2022 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.qdev2.spaceinvaders.model;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import fr.univartois.butinfo.qdev2.objets.Murs;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.AlienVieDecorator;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.IStrategieDeplacement;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.StrategieAleatoireDeplacementAlien;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.StrategieBaseDeplacementAlien;
import fr.univartois.butinfo.qdev2.spaceinvaders.model.movables.StrategieDifficileDeplacementAlien;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.ISpriteStore;
import fr.univartois.butinfo.qdev2.spaceinvaders.view.Sprite;
import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * La classe {@link SpaceInvadersGame} permet de gérer une partie du jeu Space-Invaders.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class SpaceInvadersGame {

    /**
     * La vitesse du vaisseau du joueur lorsqu'il se déplace (en pixels/s).
     */
    private static final double SHIP_SPEED = 175;

    /**
     * La temporisation contraignant le temps entre deux tirs successifs (en
     * millisecondes).
     */
    private static final long SHOT_TEMPORIZATION = 500;

    /**
     * La largeur sur laquelle les objets du jeu peuvent se déplacer (en pixels).
     */
    private final int width;

    /**
     * La hauteur sur laquelle les objets du jeu peuvent se déplacer (en pixels).
     */
    private final int height;

    /**
     * L'instance de {@link ISpriteStore} permettant de créer les {@link Sprite} du jeu.
     */
    private final ISpriteStore spriteStore;

    /**
     * L'instance de {@link IMovableFactory} permettant de créer les objets du jeu.
     */
    private IMovableFactory factory;

    /**
     * Le contrôleur du jeu.
     */
    private ISpaceInvadersController controller;

    /**
     * Le vaisseau du joueur.
     */
    private IMovable ship;

    /**
     * Le nombre de vies restantes pour le joueur.
     */
    private final IntegerProperty life = new SimpleIntegerProperty();

    /**
     * Le score du joueur (en nombre d'aliens éliminés).
     */
    private final IntegerProperty score = new SimpleIntegerProperty();

    /**
     * Le timestamp du dernier tir.
     * Il permet de s'assurer que le joueur ne tire pas trop souvent.
     */
    private long lastShot = 0;

    /**
     * Le nombre d'aliens encore vivants.
     */
    private int nbRemainingAliens;

    /**
     * La liste des objets pouvant se déplacer dans le jeu.
     */
    private final List<IMovable> movableObjects = new CopyOnWriteArrayList<>();

    /**
     * L'animation du jeu, qui s'assure que les différents objets se déplacent.
     */
    private final AnimationTimer animation = new SpaceInvadersAnimation(this, movableObjects);

    /**
     * L'attribut shootRandom...
     */
    private IShotStrategy shootRandom = new ShootRandom();

    /**
     * L'attribut alwaysShoot...
     */
    private IShotStrategy alwaysShoot = new AlwaysShoot();

    /**
     * L'attribut neverShoot...
     */
    private IShotStrategy neverShoot = new NeverShoot();

    /**
     * L'attribut defaultDeplacement...
     */
    private IStrategieDeplacement defaultDeplacement = new StrategieBaseDeplacementAlien();

    /**
     * L'attribut randomDeplacement...
     */
    private IStrategieDeplacement randomDeplacement = new StrategieAleatoireDeplacementAlien();

    /**
     * L'attribut hardDeplacement...
     */
    private IStrategieDeplacement hardDeplacement = new StrategieDifficileDeplacementAlien();

    /**
     * L'attribut rand...
     */
    Random rand = new Random();

    /**
     * L'attribut limit...
     */
    int limit = 10;
    
    private int round;

    /**
     * Crée une nouvelle instance de SpaceInvadersGame.
     *
     * @param width La largeur sur laquelle les objets du jeu peuvent se déplacer (en
     *        pixels).
     * @param height La hauteur sur laquelle les objets du jeu peuvent se déplacer (en
     *        pixels).
     * @param spriteStore L'instance de {@link ISpriteStore} permettant de créer les
     *        {@link Sprite} du jeu.
     * @param factory L'instance de {@link IMovableFactory} permettant de créer les
     *        objets
     *        du jeu.
     */
    public SpaceInvadersGame(int width, int height, ISpriteStore spriteStore,
            IMovableFactory factory) {
        this.width = width;
        this.height = height;
        this.spriteStore = spriteStore;
        this.factory = factory;
    }

    /**
     * Donne la largeur sur laquelle les objets du jeu peuvent se déplacer (en pixels).
     *
     * @return La largeur du jeu.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Donne la limite à gauche pour la position des objets mobiles du jeu (mesurée en
     * pixels).
     *
     * @return La limite à gauche pour la position des objets mobiles du jeu.
     */
    public int getLeftLimit() {
        return limit;
    }

    /**
     * Donne la limite à droite pour la position des objets mobiles du jeu (mesurée en
     * pixels).
     *
     * @return La limite à droite pour la position des objets mobiles du jeu.
     */
    public int getRightLimit() {
        return width - limit;
    }

    /**
     * Donne la hauteur sur laquelle les objets du jeu peuvent se déplacer (en pixels).
     *
     * @return La hauteur du jeu.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Donne la limite en haut pour la position des objets mobiles du jeu (mesurée en
     * pixels).
     *
     * @return La limite en haut pour la position des objets mobiles du jeu.
     */
    public int getTopLimit() {
        return limit;
    }

    /**
     * Donne la limite en bas pour la position des objets mobiles du jeu (mesurée en
     * pixels).
     *
     * @return La limite en bas pour la position des objets mobiles du jeu.
     */
    public int getBottomLimit() {
        return height - 80;
    }

    /**
     * Donne l'attribut movableObjects de cette instance de SpaceInvadersGame.
     *
     * @return L'attribut movableObjects de cette instance de SpaceInvadersGame.
     */
    public List<IMovable> getMovableObjects() {
        return movableObjects;
    }
    
    /**
     * Associe à cette partie de Space-Invaders le contrôleur gérant l'affichage du
     * jeu.
     *
     * @param controller Le contrôleur de l'application.
     */
    public void setController(ISpaceInvadersController controller) {
        this.controller = controller;
    }

    /**
     * Prépare une partie de Space-Invaders sur le contrôleur de l'application.
     */
    public void prepare() {
        getFactory().setSpriteStore(spriteStore);
        getFactory().setGame(this);

        controller.setSpriteStore(spriteStore);
        controller.prepare();
        controller.bindScore(score);
        controller.bindLife(life);
    }

    /**
     * Démarre cette partie de Space-Invaders.
     * Cette méthode permet également de redémarrer une nouvelle partie.
     */
    public void start() {
        initStatistics();
        createMovables();
        animation.start();
    }

    /**
     * Initialise les statistiques de cette partie.
     */
    private void initStatistics() {
        life.set(3);
        score.set(0);
        nbRemainingAliens = 0;
    }

    /**
     * Crée les différents objets présents au début de la partie et pouvant se
     * déplacer.
     */
    private void createMovables() {
        // On commence par enlever tous les éléments mobiles encore présents.
        clearAllMovables();
        ship = getFactory().createShip(getWidth() / 2, getBottomLimit());
        addMovable(ship);
        if (round == 0) {
            for (int i = 0; i < 10; i++) {
                addMovable(new AlienVieDecorator(
                        factory.createAlien(getWidth() / 2 + (i * 50), getTopLimit() + 130,
                                neverShoot, defaultDeplacement),
                        1));
                nbRemainingAliens++;
            }
        }
        else {
            for (int i = 100; i <= 1000; i += 200) {
                addMovable(new Murs(this, i, 550));
            }
            for (int i = 0; i < 10; i++) {
                addMovable(new AlienVieDecorator(
                        factory.createAlien(getWidth() / 2 + (i * 50), getTopLimit() + 130,
                                shootRandom, defaultDeplacement),
                        1));
                nbRemainingAliens++;
            }
        }
    }

    /**
     * Choisit aléatoirement un bonus et le place dans le jeu à une position aléatoire.
     */
    public void dropBonus() {
        String[] sprites = new String[] { "bonus-vie", "bonus-tir", "bonus-invinsible" };
        int length = rand.nextInt(0, sprites.length);
        String sprite = sprites[length];

        int x = rand.nextInt(getLeftLimit(), getRightLimit());
        IMovable bonus = getFactory().createBonus(x, getTopLimit() + 10, sprite);
        addMovable(bonus);
    }

    /**
     * Déplace le vaisseau du joueur vers la gauche.
     */
    public void moveLeft() {
        ship.setHorizontalSpeed(-SHIP_SPEED);
    }

    /**
     * Déplace le vaisseau du joueur vers la droite.
     */
    public void moveRight() {
        ship.setHorizontalSpeed(SHIP_SPEED);
    }

    /**
     * Arrête le déplacement du vaisseau du joueur.
     */
    public void stopMoving() {
        ship.setHorizontalSpeed(0);
    }

    /**
     * Déclenche un tir depuis le vaisseau du joueur.
     * Cette méthode est sans effet si le délai entre deux tirs n'est pas atteint.
     */
    public void fireShot() {
        if ((System.currentTimeMillis() - lastShot) > SHOT_TEMPORIZATION) {
            lastShot = System.currentTimeMillis();
            if (score.get() != 0 && score.get() % 5 == 0) {
                IMovable shot = getFactory().createSuperShot(ship.getX(), ship.getY());
                shot.setX(shot.getX() + ((ship.getWidth() - shot.getWidth()) / 2));
                shot.setY(shot.getY() - shot.getHeight() - 10);
                addMovable(shot);
                return;
            }
            IMovable shot = getFactory().createShot(ship.getX(), ship.getY());
            shot.setX(shot.getX() + ((ship.getWidth() - shot.getWidth()) / 2));
            shot.setY(shot.getY() - shot.getHeight());
            addMovable(shot);
        }
    }

    /**
     * Donne l'attribut life de cette instance de SpaceInvadersGame.
     *
     * @return L'attribut life de cette instance de SpaceInvadersGame.
     */
    public IntegerProperty getLife() {
        return life;
    }

    /**
     * Met à jour le score du joueur lorsqu'un alien est tué.
     * Si c'était le dernier, le joueur gagne la partie.
     *
     */
    public void alienIsDead() {
        nbRemainingAliens--;
        score.set(score.get() + 1);
        if (nbRemainingAliens == 0) {
            animation.stop();
            controller.gameOver("Vous avez gagne");
            round++;
            start();
            animation.stop();
        }
    }

    /**
     * Réduit la vie du joueur, et interrompt la partie si elle atteint 0.
     */
    public void reducePlayerLife() {
        life.setValue(life.getValue() - 1);
        if (life.getValue() == 0) {
            playerIsDead();
        }
    }

    /**
     * Augmente la vie du joueur.
     */
    public void addPlayerLife() {
        life.setValue(life.getValue() + 1);
    }

    /**
     * Termine la partie lorsque le joueur est tué.
     */
    public void playerIsDead() {
        animation.stop();
        controller.gameOver("Vous etes mauvais");
    }

    /**
     * Termine la partie lorsque les aliens atteignent la planète.
     */
    public void alienReachedPlanet() {
        animation.stop();
        controller.gameOver("Vous etes vraiment mauvais");
    }

    /**
     * Ajoute un objet pouvant se déplacer dans le jeu.
     *
     * @param object L'objet à ajouter.
     */
    public void addMovable(IMovable object) {
        movableObjects.add(object);
        controller.addMovable(object);
    }

    /**
     * Supprime un objet pouvant se déplacer dans le jeu.
     *
     * @param object L'objet à supprimer.
     */
    public void removeMovable(IMovable object) {
        movableObjects.remove(object);
        object.consume();
    }

    /**
     * Supprime tous les objets pouvant se déplacer dans le jeu.
     */
    private void clearAllMovables() {
        for (IMovable movable : movableObjects) {
            movable.consume();
        }
        movableObjects.clear();
    }

    /**
     * @return factory
     */
    public IMovableFactory getFactory() {
        return factory;
    }

    /**
     * Donne l'attribut alwaysShoot de cette instance de SpaceInvadersGame.
     *
     * @return L'attribut alwaysShoot de cette instance de SpaceInvadersGame.
     */
    public IShotStrategy getAlwaysShoot() {
        return alwaysShoot;
    }

    /**
     * Donne l'attribut neverShoot de cette instance de SpaceInvadersGame.
     *
     * @return L'attribut neverShoot de cette instance de SpaceInvadersGame.
     */
    public IShotStrategy getNeverShoot() {
        return neverShoot;
    }

    /**
     * Donne l'attribut randomDeplacement de cette instance de SpaceInvadersGame.
     *
     * @return L'attribut randomDeplacement de cette instance de SpaceInvadersGame.
     */
    public IStrategieDeplacement getRandomDeplacement() {
        return randomDeplacement;
    }

    /**
     * Donne l'attribut hardDeplacement de cette instance de SpaceInvadersGame.
     *
     * @return L'attribut hardDeplacement de cette instance de SpaceInvadersGame.
     */
    public IStrategieDeplacement getHardDeplacement() {
        return hardDeplacement;
    }

}
