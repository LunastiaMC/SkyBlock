package fr.lunastia.skyblock.core.session;

public class Rank {
    private final int id;
    private final String name;
    private final String formatedName;
    private final String arrow;

    public Rank(int id, String name, String formatedName, String arrow) {
        this.id = id;
        this.name = name;
        this.formatedName = formatedName;
        this.arrow = arrow;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormatedName() {
        return formatedName;
    }

    public String getArrow() {
        return arrow;
    }
}