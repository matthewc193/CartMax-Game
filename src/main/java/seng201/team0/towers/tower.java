package seng201.team0.towers;

public abstract class tower {
    int resourceAmount;
    double reloadSpeed;
    String resourceType;
    double cost;
    abstract int getResourceAmount();

    abstract double getReloadSpeed();

    abstract String getResourceType();
    abstract double getCost();
}
