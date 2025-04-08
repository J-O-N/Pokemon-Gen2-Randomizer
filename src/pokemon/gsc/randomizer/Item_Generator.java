package pokemon.gsc.randomizer;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Item_Generator {

    private static final Set<Integer> INVALID_ITEMS = buildInvalidItemSet();
    private static final Set<Integer> KEY_ITEMS = buildKeyItemSet();

    private static Set<Integer> buildInvalidItemSet() {
        Set<Integer> itemSet = new HashSet<>();
        // Empty spaces / unusable items.
        itemSet.addAll(Arrays.asList(
            0x06, 0x19, 0x2D, 0x32, 0x38, 0x46, 0x5A, 0x64, 0x73, 0x74,
            0x78, 0x81, 0x87, 0x88, 0x89, 0x8D, 0x8E, 0x91, 0x93, 0x94,
            0x95, 0x99, 0x9A, 0x9B, 0xA2, 0xAB, 0xB0, 0xB3, 0xBE
        ));
        return Collections.unmodifiableSet(itemSet);
    }

    private static Set<Integer> buildKeyItemSet() {
        Set<Integer> itemSet = new HashSet<>();
        // Key Items that shouldn't be randomly assigned generally.
        itemSet.addAll(Arrays.asList(
            0x07, 0x36, 0x37, 0x3D, 0x42, 0x44, 0x43, 0x45, 0x47, 0x7F,
            0x80, 0x82, 0x85, 0x86, 0xAF, 0xB2
        ));
        return Collections.unmodifiableSet(itemSet);
    }

    private static final int LAST_SAFE_ITEM = 0xF1;

    private Random random;

    public Item_Generator(Random randomInstance){
        this.random = randomInstance;
    }

    /**
     * Generates a random item ID, excluding invalid items and key items.
     * @return A valid, non-key item ID.
     */
    public int insertItem() {
        int item = 0;
        while(item == 0 || KEY_ITEMS.contains(item)) {
            item = insertAnyItem();
        } 
        return item;
    }

    /**
     * Generates a random item ID, excluding only invalid items (allows key items).
     * @return A valid item ID, potentially a key item.
     */
    public int insertAnyItem() {
        int item = 0;
        while(item == 0 || INVALID_ITEMS.contains(item)) {
            item = random.nextInt(LAST_SAFE_ITEM) + 1;
        } 
        return item;
    }
}
