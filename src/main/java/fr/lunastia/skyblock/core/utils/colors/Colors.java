package fr.lunastia.skyblock.core.utils.colors;

public enum Colors {
    PREFIX("&#229dc3&l[&#2796c6&lS&#2b8ec9&lk&#3087cc&ly&#357fcf&lB&#3978d1&ll&#3e70d4&lo&#4369d7&lc&#4761da&lk&#4c5add&l]§r§7"),
    HAT("&#fb5cf1&l[&#f058e6&lC&#e654da&lh&#db50cf&la&#d04cc3&lp&#db4dbf&le&#e74fbc&la&#f250b8&lu&#fd51b4&l]§r§7"),
    BANK("&#fbf800&l[&#f6f004&lB&#f1e808&la&#ece00c&ln&#e7d70f&lq&#e2cf13&lu&#ddc717&le&#d8bf1b&l]§r§7"),
    TRASH("&#868484&l[&#888787&lP&#8b8a8a&lo&#8d8d8c&lu&#8f908f&lb&#929492&le&#949795&ll&#969a97&ll&#999d9a&le&#9ba09d&l]§r§7"),
    REPAIR("&#8f9873&l[&#919972&lE&#929a72&ln&#949b71&lc&#959c70&ll&#979d70&lu&#999e6f&lm&#9a9f6e&le &#9ca06e&la&#9da16d&lm&#9fa26d&lé&#a0a36c&ll&#a2a46b&li&#a4a56b&lo&#a5a66a&lr&#a7a769&lé&#a8a869&le&#aaa968&l]§r§7"),
    MOD("&#ff3c3c&l[&#f43939&lS&#ea3737&la&#df3434&ln&#d53131&lc&#ca2f2f&lt&#c02c2c&li&#b52929&lo&#ab2727&ln&#a02424&l]"),

    // Colors
    MOD_RED("&#ff3c3c"),
    DISCORD_COLOR("&#5865F2"),
    LOGS("&#89fbf4&l[&#74e6dd&lL&#5ed1c7&lo&#49bcb0&lg&#33a799&l]"),

    COMMON("&#a5ada3"),
    GOOD("&#66ff3c"),
    WARNING("&#ffcb3c"),
    NORMAL("&#3c53ff"),
    BAD("&#ff3c3c");

    private final String k;

    Colors(String k) {
        this.k = k;
    }

    public String color() {
        return k;
    }
}
