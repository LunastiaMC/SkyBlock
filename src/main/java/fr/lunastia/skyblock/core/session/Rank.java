package fr.lunastia.skyblock.core.session;

public class Rank {
    private final int id;
    private final String name;
    private final String coloredName;
    private final String arrow;

    public Rank(int id, String name, String coloredName, String arrow) {
        this.id = id;
        this.name = name;
        this.coloredName = coloredName;
        this.arrow = arrow;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColoredName() {
        return coloredName;
    }

    public String getArrow() {
        return arrow;
    }
}