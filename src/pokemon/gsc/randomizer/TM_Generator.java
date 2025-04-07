package pokemon.gsc.randomizer;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class TM_Generator {
    private static final Set<Integer> HM_MOVES = buildHmMap();

    private static Set<Integer> buildHmMap() {
        Set<Integer> hmSet = new HashSet<>();
        hmSet.add(0xFA); // HM01 Cut
        hmSet.add(0x7F); // HM02 Fly
        hmSet.add(0x39); // HM03 Surf
        hmSet.add(0x94); // HM04 Strength
        hmSet.add(0x46); // HM05 Flash
        hmSet.add(0x13); // HM06 Whirlpool
        hmSet.add(0x0F); // HM07 Waterfall
        return Collections.unmodifiableSet(hmSet);
    }

    private static final int LAST_SAFE_TM = 0xFB;

    private Random random;

    public TM_Generator(Random randomInstance){
        this.random = randomInstance;
    }

    public int giveRandomByte() {
        return random.nextInt(256);
    }
    
    public int giveMove() {
        return random.nextInt(LAST_SAFE_TM) + 1;
    }

    public int giveNonHMMove() {
        int move = 0;
        while(move == 0 || HM_MOVES.contains(move)){
            move = giveMove();
        }
        return move;
    }

    public Set<Integer> getTmMoveSet(int size) {
        // Insertion order preserved to stay compatible with previous implementation.
        Set<Integer> moveSet = new LinkedHashSet<>();
        while (moveSet.size() < size) {
            int tmMove = giveNonHMMove();
            if(!moveSet.add(tmMove)) {
                System.out.println("Threw out HM or Repeat " + tmMove);
            }
        }
        return moveSet;
    }
}
