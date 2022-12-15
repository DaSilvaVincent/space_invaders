# Space-Invaders en JavaFX

## Description

Ce projet fournit une implantation de base du jeu *Space-Invaders* en *JavaFX*
Pour pouvoir développer votre propre implantation de ce projet, vous devez
en créer une **divergence** en cliquant sur le bouton `Fork` en haut à droite
de cette page.

Lorsque ce sera fait, vous pourrez inviter les membres de votre groupe en tant
que *Developer* pour vous permettre de travailler ensemble sur ce projet.

## Consignes

Vous pouvez retrouver ci-dessous les liens vers les sujets de TP vous guidant
dans le développement de votre projet.

- [Lancement du projet](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/q-dev-2/tp/-/tree/main/TP03)
- [Des patrons de conception dans le Space-Invaders (1)](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/q-dev-2/tp/-/tree/main/TP04)
- [Des patrons de conception dans le Space-Invaders (2)](https://gitlab.univ-artois.fr/enseignements-rwa/modules/but-2/q-dev-2/tp/-/tree/main/TP05)

## Aperçu

Le diagramme de classes ci-dessous vous donne un aperçu des fonctionnalités
disponibles dans ce projet.
N'hésitez pas à le mettre à jour dans la version dont vous disposez dans votre
propre projet, afin de permettre à votre enseignant de TP de vous faire des
retours sur vos choix de conception.
Il est en fait au format textuel (comme vous pouvez vous en rendre compte si
vous consulter le texte brut de ce document) et rendu automatiquement par
*GitLab*, ce qui devrez vous faciliter la tâche.

```plantuml
@startuml

hide empty members

package fr.univartois.butinfo.qdev2.spaceinvaders.view {
    class Sprite
    interface ISpriteStore
    class SpriteStore
}

class Sprite {
    - image : Image

    + Sprite(Image)
    + getWidth() : int
    + getHeight() : int
    + getImage() : Image
    + draw(GraphicsContext, int, int) : void
}

interface ISpriteStore {
    + {abstract} getSprite(String) : Sprite
}

class SpriteStore implements ISpriteStore {
    - cache: Map<String, Sprite>
}

ISpriteStore --> Sprite : << crée >>



package fr.univartois.butinfo.qdev2.spaceinvaders.model {
    interface IMovable
    interface IMovableFactory
    interface ISpaceInvadersController
    class SpaceInvadersAnimation
    class SpaceInvadersGame
}

interface IMovable {
    + {abstract} getWidth() : int
    + {abstract} getHeight() : int
    + {abstract} setX(int) : void
    + {abstract} getX() : int
    + {abstract} getXProperty() : DoubleProperty
    + {abstract} setY(int) : void
    + {abstract} getY() : int
    + {abstract} getYProperty() : DoubleProperty
    + {abstract} consume() : void
    + {abstract} isConsumed() : boolean
    + {abstract} isConsumedProperty() : BooleanProperty
    + {abstract} setHorizontalSpeed(double) : void
    + {abstract} getHorizontalSpeed() : double
    + {abstract} setVerticalSpeed(double) : void
    + {abstract} getVerticalSpeed() : double
    + {abstract} move(long) : boolean
    + {abstract} setSprite(Sprite) : void
    + {abstract} getSprite() : Sprite
    + {abstract} getSpriteProperty() : ObjectProperty<Sprite>
    + {abstract} isCollidingWith(IMovable) : boolean
    + {abstract} collidedWith(IMovable) : void
}

interface IMovableFactory {
    + {abstract} setSpriteStore(ISpriteStore) : void
    + {abstract} setGame(SpaceInvadersGame) : void
    + {abstract} createAlien(int, int) : IMovable
    + {abstract} createShip(int, int) : IMovable
    + {abstract} createShot(int, int) : IMovable
}

interface ISpaceInvadersController {
    + {abstract} setGame(SpaceInvadersGame) : void
    + {abstract} setSpriteStore(ISpriteStore) : void
    + {abstract} prepare() : void
    + {abstract} bindScore(IntegerProperty) : void
    + {abstract} bindLife(IntegerProperty) : void
    + {abstract} addMovable(IMovable) : void
    + {abstract} gameOver(String) : void
    + {abstract} reset() : void
}

class SpaceInvadersAnimation {
    - previousTimestamp : long

    + SpaceInvadersAnimation(List<IMovable>)
    + handle(long) : void
    - moveObjects(long) : void
    - checkCollisions() : void
}

class SpaceInvadersGame {
    - {static} SHIP_SPEED : double
    - {static} SHOT_TEMPORIZATION : long
    - width : int
    - height : int
    - life : IntegerProperty
    - score : IntegerProperty
    - lastShot : long
    - nbRemainingAliens : int

    + SpaceInvadersGame(int, int, ISpriteStore, IMovableFactory)
    + getWidth() : int
    + getLeftLimit() : int
    + getRightLimit() : int
    + getHeight() : int
    + getTopLimit() : int
    + getBottomLimit() : int
    + setController(ISpaceInvadersController) : void
    + prepare() : void
    + start() : void
    - initStatistics() : void
    - createMovables() : void
    + moveLeft() : void
    + moveRight() : void
    + stopMoving() : void
    + fireShot() : void
    + alienIsDead(IMovable) : void
    + reducePlayerLife() : void
    + playerIsDead() : void
    + alienReachedPlanet() : void
    - addMovable(IMovable) : void
    + removeMovable(IMovable) : void
    - clearAllMovables() : void
}

IMovableFactory --> IMovable : << crée >>
SpaceInvadersAnimation o-- "0..*" IMovable
SpaceInvadersGame o-- "1" ISpriteStore
SpaceInvadersGame o-- "1" IMovableFactory
SpaceInvadersGame o-- "1" ISpaceInvadersController
SpaceInvadersGame o-- "1" IMovable : ship
SpaceInvadersGame o-- "0..*" IMovable : movableObjects
SpaceInvadersGame *-- "1" SpaceInvadersAnimation



package fr.univartois.butinfo.qdev2.spaceinvaders.model.movables {
    abstract class AbstractMovable
}

abstract class AbstractMovable implements IMovable {
    # xPosition : DoubleProperty
    # yPosition : DoubleProperty
    # consumed : BooleanProperty
    # horizontalSpeed : double
    # verticalSpeed : double

    # AbstractMovable(SpaceInvadersGame, double, double, Sprite)
    + getWidth() : int
    + getHeight() : int
    + setX(int) : void
    + getX() : int
    + getXProperty() : DoubleProperty
    + setY(int) : void
    + getY() : int
    + getYProperty() : DoubleProperty
    + consume() : void
    + isConsumed() : boolean
    + isConsumedProperty() : BooleanProperty
    + setHorizontalSpeed(double) : void
    + getHorizontalSpeed() : double
    + setVerticalSpeed(double) : void
    + getVerticalSpeed() : double
    + move(long) : boolean
    + setSprite(Sprite) : void
    + getSprite() : Sprite
    + getSpriteProperty() : ObjectProperty<Sprite>
    + isCollidingWith(IMovable) : boolean
}

AbstractMovable o-- "1" Sprite
AbstractMovable o-- "1" SpaceInvadersGame



package fr.univartois.butinfo.qdev2.spaceinvaders.controller {
    class SpaceInvadersController
}

class SpaceInvadersController implements ISpaceInvadersController {
    - stage : Stage
    - background : Canvas
    - movingPane : Pane
    - message : Label
    - score : Label
    - life : Labem

    + setStage(Stage) : void
    - createBackground() : void
    - addKeyListeners() : void
}

SpaceInvadersController o-- "1" SpaceInvadersGame
SpaceInvadersController o-- "1" ISpriteStore



package fr.univartois.butinfo.qdev2.spaceinvaders {
    class SpaceInvaders
}

class SpaceInvaders {
    + start(Stage) : void
    + {static} main(String[]) : void
}

SpaceInvaders --> SpaceInvadersController : << charge >>
SpaceInvaders -left-> SpaceInvadersGame : << crée >>

@enduml
```
