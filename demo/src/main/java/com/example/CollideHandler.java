package com.example;

public class CollideHandler implements ICollideHandler {
    private final IKillEntity killEntity;
    private final IEater eater;
    private final GameTimer gameTimer;
    private final Revive revive;

    /**
     * Constructor for CollideHandler
     *
     * @param gameTimer The game timer to manage timed events
     * @param revive    The revive handler for ghost revival logic
     * @param eater     The eater handler for eating logic
     * @param killEntity The entity responsible for killing players and ghosts
     */
    public CollideHandler(GameTimer gameTimer, Revive revive, IEater eater, IKillEntity killEntity) {
        this.killEntity = killEntity;
        this.eater = eater;
        this.gameTimer = gameTimer;
        this.revive = revive;
    }

    /**
     * Handles the collision between the player and a ghost.
     * @param ghost The ghost that the player collided with
     */
    @Override
    public void ghostCollision(Ghost ghost) {
        switch (ghost.getState()) {
            case CHASE:
                killEntity.killPlayer(); // The player collides with a ghost in chase state and dies
                break;
            case FRIGHTENED:
                killEntity.killGhost(ghost); // The player kills the ghost in frightened state
                eater.eatGhost(ghost);       // The player eats the ghost (points awarded)
                break;
            case EATEN:
                break;
            default:
                break;
        }
    }

    /**
     * Handles the collision between the player and a home.
     * @param ghost The ghost that collided with the home
     */
    @Override
    public void homeCollision(Ghost ghost) {
        revive.tryReviveGhost(ghost); // Attempt to revive the ghost
    }

    /**
     * Handles the collision between the player and a door.
     * @param door The door that the enitity collided with
     */
    @Override
    public void doorCollision(Door door) {
        door.openDoor();

        // Schedule the door to close after 1 second
        long currentTime = System.currentTimeMillis();
        gameTimer.addFunctionToList(
            GameTimer.atTimeRunFunction(
                "OpenTheDoor", currentTime, 1000L, () -> {
                    door.closeDoor();
                }
            )
        );

    }

    /**
     * Handles the collision between the player and a pellet.
     * @param pellet The pellet that the player collided with
     */
    @Override
    public void pelletCollision(Pellet pellet) {
        eater.eatPellet(pellet); // The player eats the pellet (points awarded)
    }

    /**
     * Handles the collision between the player and a big pellet.
     * @param pellet The big pellet that the player collided with
     */
    @Override
    public void bigPelletCollision(BigPellet pellet) {
        eater.eatBigPellet(pellet); // The player eats the big pellet (points awarded and ghosts frightened)
    }    
}
