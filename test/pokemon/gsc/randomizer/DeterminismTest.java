package pokemon.gsc.randomizer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

public class DeterminismTest {

    @Test
    public void testItemGeneratorDeterminism() {
        long seed = 12345L; // Use a fixed seed for the test

        // Create two Random instances with the same seed
        Random random1 = new Random(seed);
        Random random2 = new Random(seed);

        // Create two Item_Generator instances, each using one of the seeded Random objects
        Item_Generator itemGen1 = new Item_Generator(random1);
        Item_Generator itemGen2 = new Item_Generator(random2);

        // Generate a sequence of items from both generators
        int numItemsToGenerate = 100;
        int[] items1 = new int[numItemsToGenerate];
        int[] items2 = new int[numItemsToGenerate];

        for (int i = 0; i < numItemsToGenerate; i++) {
            items1[i] = itemGen1.insertItem();
            items2[i] = itemGen2.insertItem();
        }

        // Assert that the sequences are identical
        for (int i = 0; i < numItemsToGenerate; i++) {
            assertEquals(items1[i], items2[i], "Item generation should be deterministic for the same seed at index " + i);
        }

        // Test the other item generation method as well
        int[] itemsAny1 = new int[numItemsToGenerate];
        int[] itemsAny2 = new int[numItemsToGenerate];

        // Re-seed the random generators to ensure a fresh start for this part
        random1.setSeed(seed);
        random2.setSeed(seed);
        itemGen1 = new Item_Generator(random1); // Re-instantiate to reset internal state if necessary
        itemGen2 = new Item_Generator(random2);

        for (int i = 0; i < numItemsToGenerate; i++) {
            itemsAny1[i] = itemGen1.insertItemAny();
            itemsAny2[i] = itemGen2.insertItemAny();
        }

        // Assert that the sequences are identical
        for (int i = 0; i < numItemsToGenerate; i++) {
            assertEquals(itemsAny1[i], itemsAny2[i], "Item generation (Any) should be deterministic for the same seed at index " + i);
        }
    }

    @Test
    public void testNameGeneratorDeterminism() {
        long seed = 98765L;

        Random random1 = new Random(seed);
        Random random2 = new Random(seed);

        Name_Generator nameGen1 = new Name_Generator(random1);
        Name_Generator nameGen2 = new Name_Generator(random2);

        // Test a few different name sizes
        assertEquals(nameGen1.getRandomNameSize4().length, nameGen2.getRandomNameSize4().length, "Name length should match");
        // Note: Comparing byte arrays directly with assertEquals might not work as expected
        // depending on the JUnit version/assertions used. A loop might be safer.
        byte[] name1_size4 = nameGen1.getRandomNameSize4();
        byte[] name2_size4 = nameGen2.getRandomNameSize4();
        for(int i=0; i<name1_size4.length; i++) {
            assertEquals(name1_size4[i], name2_size4[i], "Name (size 4) byte should match at index " + i);
        }

        byte[] name1_size7 = nameGen1.getRandomNameSize7();
        byte[] name2_size7 = nameGen2.getRandomNameSize7();
        assertEquals(name1_size7.length, name2_size7.length, "Name length should match");
         for(int i=0; i<name1_size7.length; i++) {
            assertEquals(name1_size7[i], name2_size7[i], "Name (size 7) byte should match at index " + i);
        }
    }

    @Test
    public void testPokeGeneratorDeterminism() {
        long seed = 67890L;

        Random random1 = new Random(seed);
        Random random2 = new Random(seed);

        Poke_Generator pokeGen1 = new Poke_Generator(random1);
        Poke_Generator pokeGen2 = new Poke_Generator(random2);

        int numPokesToGenerate = 100;
        for (int i = 0; i < numPokesToGenerate; i++) {
            assertEquals(pokeGen1.insertPoke(), pokeGen2.insertPoke(), "Poke generation should be deterministic at index " + i);
        }
        // Also test evolution logic if it uses random (it doesn't seem to, but good practice)
        // assertEquals(pokeGen1.evolvePokemon(1, 20), pokeGen2.evolvePokemon(1, 20), "Evolution should be deterministic");
    }

     @Test
    public void testTMGeneratorDeterminism() {
        long seed = 13579L;

        Random random1 = new Random(seed);
        Random random2 = new Random(seed);

        // TM_Generator has internal state (movesPicked), so re-instantiate for comparisons
        TM_Generator tmGen1 = new TM_Generator(random1);
        TM_Generator tmGen2 = new TM_Generator(random2);

        int numTMsToGenerate = 50; // Generate enough to potentially fill movesPicked
        int[] tms1 = new int[numTMsToGenerate];
        int[] tms2 = new int[numTMsToGenerate];

        for (int i = 0; i < numTMsToGenerate; i++) {
             // Need fresh instances if internal state affects subsequent calls non-deterministically
             // In this case, insertTM modifies internal state (movesPicked, index)
             // So, to test determinism of the *sequence*, we compare outputs step-by-step
             tms1[i] = tmGen1.insertTM();
             tms2[i] = tmGen2.insertTM();
             assertEquals(tms1[i], tms2[i], "TM generation (insertTM) should be deterministic at index " + i);
        }

        // Test other methods - re-seed and re-instantiate if needed
        random1.setSeed(seed);
        random2.setSeed(seed);
        tmGen1 = new TM_Generator(random1);
        tmGen2 = new TM_Generator(random2);
        for (int i = 0; i < numTMsToGenerate; i++) {
             assertEquals(tmGen1.giveMove(), tmGen2.giveMove(), "TM generation (giveMove) should be deterministic at index " + i);
        }

        random1.setSeed(seed);
        random2.setSeed(seed);
        tmGen1 = new TM_Generator(random1);
        tmGen2 = new TM_Generator(random2);
         for (int i = 0; i < numTMsToGenerate; i++) {
             assertEquals(tmGen1.giveNonHMMove(), tmGen2.giveNonHMMove(), "TM generation (giveNonHMMove) should be deterministic at index " + i);
        }

        random1.setSeed(seed);
        random2.setSeed(seed);
        tmGen1 = new TM_Generator(random1);
        tmGen2 = new TM_Generator(random2);
         for (int i = 0; i < numTMsToGenerate; i++) {
             assertEquals(tmGen1.giveRandomByte(), tmGen2.giveRandomByte(), "TM generation (giveRandomByte) should be deterministic at index " + i);
        }
    }
}
