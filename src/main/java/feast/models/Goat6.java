package feast.models;


public class Goat6 {

    private Horse7 horse7;

    public Goat6(Horse7 horse7) {
        this.horse7 = horse7;
    }

    @Override
    public String toString() {
        return "which is stuffed inside a horse, " + horse7.toString();
    }
}
