class Car {

    int yearModel;
    String make;
    int speed;

    public void accelerate() {
        this.speed += 5;
    }

    public void brake() {
        this.speed = (this.speed > 5) ? (this.speed - 5) : 0;
    }
}