package fr.branyoto;

import fr.branyoto.utils.PathCreator;

public class MainTest {
    public static void main(String[] args) {
        System.out.println(PathCreator.getFirstNotExisting("test"));
        System.out.println(PathCreator.getFirstNotExisting("coucou"));
        System.out.println(PathCreator.getFirstNotExisting("output/test"));
        System.out.println(PathCreator.getFirstNotExisting("output/test", s -> s+"_"));
        System.out.println(PathCreator.getFirstNotExisting("output/test", () -> 'a', s -> (char) (s+1)));
    }
}
