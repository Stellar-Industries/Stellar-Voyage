package lol.koblizek.stellarvoyage.util;

public class Randomizer {
    private final String[] data;
    private String prev;

    public Randomizer(String... data) {
        this.data = data;
    }

    public String get() {
        if (prev == null)
            return Utils.random(data);
        else {
            String r = prev;
            while (r.equals(prev))
                r = Utils.random(data);
            prev = r;
            return r;
        }
    }
}
