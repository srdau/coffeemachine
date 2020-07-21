class Move {
    public static void moveRobotToXPosition(Robot robot, int toX) {
        final int origX = robot.getX();
        int atX;
        Direction currentDirection = robot.getDirection();

        if (toX > origX) {
            // Face right
            switch (currentDirection) {
                case UP:
                    robot.turnRight();
                    break;
                case DOWN:
                    robot.turnLeft();
                    break;
                case LEFT:
                    robot.turnRight();
                    robot.turnRight();
                    break;
                default:
                    break;
            }
            for (atX = origX; atX < toX; atX += Direction.RIGHT.dx()) {
                robot.stepForward();
            }
        } else if (toX < origX) {
            // Face left
            switch (currentDirection) {
                case UP:
                    robot.turnLeft();
                    break;
                case DOWN:
                    robot.turnRight();
                    break;
                case RIGHT:
                    robot.turnLeft();
                    robot.turnLeft();
                    break;
                default:
                    break;
            }
            for (atX = origX; atX > toX; atX += Direction.LEFT.dx()) {
                robot.stepForward();
            }
        }
    }

    public static void moveRobotToYPosition(Robot robot, int toY) {
        final int origY = robot.getY();
        int atY;
        Direction currentDirection = robot.getDirection();

        if (toY > origY) {
            // Face up
            switch (currentDirection) {
                case DOWN:
                    robot.turnRight();
                    robot.turnRight();
                    break;
                case LEFT:
                    robot.turnRight();
                    break;
                case RIGHT:
                    robot.turnLeft();
                    break;
                default:
                    break;
            }
            for (atY = origY; atY < toY; atY += Direction.UP.dy()) {
                robot.stepForward();
            }
        } else if (toY < origY) {
            // Face down
            switch (currentDirection) {
                case UP:
                    robot.turnLeft();
                    robot.turnLeft();
                    break;
                case LEFT:
                    robot.turnLeft();
                    break;
                case RIGHT:
                    robot.turnRight();
                    break;
                default:
                    break;
            }
            for (atY = origY; atY > toY; atY += Direction.DOWN.dy()) {
                robot.stepForward();
            }
        }
    }

    public static void moveRobot(Robot robot, int toX, int toY) {
        moveRobotToXPosition(robot, toX);
        moveRobotToYPosition(robot, toY);
    }
}

//Don't change code below

enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            case RIGHT:
                return UP;
            default:
                throw new IllegalStateException();
        }
    }

    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            case RIGHT:
                return DOWN;
            default:
                throw new IllegalStateException();
        }
    }

    public int dx() {
        return dx;
    }

    public int dy() {
        return dy;
    }
}

class Robot {
    private int x;
    private int y;
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void stepForward() {
        x += direction.dx();
        y += direction.dy();
    }

    public Direction getDirection() {
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}