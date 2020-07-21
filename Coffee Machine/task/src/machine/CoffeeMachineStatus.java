package machine;

public enum CoffeeMachineStatus {
    INITIALISING,
    STARTED,
    MAIN_MENU,
    BUY_MENU,
    BEGIN_FILL,
    FILL_WATER,
    FILL_MILK,
    FILL_BEANS,
    FILL_CUPS,
    TAKING,
    REPORTING,
    CHECKING_STOCK,
    MAKING_COFFEE,
    COFFEE_MADE,
    STOPPING,
    STOPPED;

    static CoffeeMachineStatus coffeeMachineStatus = STOPPED;
}

