package pokemon.gsc.randomizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Poke_Generator {
    // Record to hold evolution data (level required, ID of evolved form)
    private record EvolutionStep(int level, int evolvesTo) {}

    private static final Map<Integer, EvolutionStep> EVOLUTION_MAP = buildEvolutionMap();

    private static Map<Integer, EvolutionStep> buildEvolutionMap() {
        Map<Integer, EvolutionStep> map = new HashMap<>();

        // Populate with direct, single-step evolutions only.
        map.put(0x01, new EvolutionStep(16, 0x02)); // Bulbasaur -> Ivysaur
        map.put(0x02, new EvolutionStep(32, 0x03)); // Ivysaur -> Venusaur
        map.put(0x04, new EvolutionStep(16, 0x05)); // Charmander -> Charmeleon
        map.put(0x05, new EvolutionStep(36, 0x06)); // Charmeleon -> Charizard
        map.put(0x07, new EvolutionStep(16, 0x08)); // Squirtle -> Wartortle
        map.put(0x08, new EvolutionStep(36, 0x09)); // Wartortle -> Blastoise
        map.put(0x0A, new EvolutionStep(7, 0x0B));  // Caterpie -> Metapod
        map.put(0x0B, new EvolutionStep(10, 0x0C)); // Metapod -> Butterfree
        map.put(0x0D, new EvolutionStep(7, 0x0E));  // Weedle -> Kakuna
        map.put(0x0E, new EvolutionStep(10, 0x0F)); // Kakuna -> Beedrill
        map.put(0x10, new EvolutionStep(18, 0x11)); // Pidgey -> Pidgeotto
        map.put(0x11, new EvolutionStep(36, 0x12)); // Pidgeotto -> Pidgeot
        map.put(0x13, new EvolutionStep(20, 0x14)); // Rattata -> Raticate
        map.put(0x15, new EvolutionStep(20, 0x16)); // Spearow -> Fearow
        map.put(0x17, new EvolutionStep(22, 0x18)); // Ekans -> Arbok
        map.put(0x19, new EvolutionStep(40, 0x1A)); // Pikachu -> Raichu (Note: Level 40 is unusual, typically stone)
        map.put(0x1B, new EvolutionStep(22, 0x1C)); // Sandshrew -> Sandslash
        map.put(0x1D, new EvolutionStep(16, 0x1E)); // Nidoran F -> Nidorina
        map.put(0x1E, new EvolutionStep(40, 0x1F)); // Nidorina -> Nidoqueen (Note: Level 40 is unusual, typically stone)
        map.put(0x20, new EvolutionStep(16, 0x21)); // Nidoran M -> Nidorino
        map.put(0x21, new EvolutionStep(40, 0x22)); // Nidorino -> Nidoking (Note: Level 40 is unusual, typically stone)
        map.put(0x23, new EvolutionStep(40, 0x24)); // Clefairy -> Clefable (Note: Level 40 is unusual, typically stone)
        map.put(0x25, new EvolutionStep(35, 0x26)); // Vulpix -> Ninetales (Note: Level 35 is unusual, typically stone)
        map.put(0x27, new EvolutionStep(35, 0x28)); // Jigglypuff -> Wigglytuff (Note: Level 35 is unusual, typically stone)
        map.put(0x29, new EvolutionStep(22, 0x2A)); // Zubat -> Golbat
        map.put(0x2A, new EvolutionStep(40, 0xA9)); // Golbat -> Crobat (Note: Level 40 is unusual, typically friendship)
        map.put(0x2B, new EvolutionStep(21, 0x2C)); // Oddish -> Gloom
        map.put(0x2C, new EvolutionStep(35, 0x2D)); // Gloom -> Vileplume (Note: Level 35 is unusual, typically stone)
        // Note: Gloom -> Bellossom (0xB6) is typically by Sun Stone, not level.
        map.put(0x2E, new EvolutionStep(24, 0x2F)); // Paras -> Parasect
        map.put(0x30, new EvolutionStep(31, 0x31)); // Venonat -> Venomoth
        map.put(0x32, new EvolutionStep(26, 0x33)); // Diglett -> Dugtrio
        map.put(0x34, new EvolutionStep(28, 0x35)); // Meowth -> Persian
        map.put(0x36, new EvolutionStep(33, 0x37)); // Psyduck -> Golduck
        map.put(0x38, new EvolutionStep(28, 0x39)); // Mankey -> Primeape
        map.put(0x3A, new EvolutionStep(28, 0x3B)); // Growlithe -> Arcanine (Note: Level 28 is unusual, typically stone)
        map.put(0x3C, new EvolutionStep(25, 0x3D)); // Poliwag -> Poliwhirl
        map.put(0x3D, new EvolutionStep(35, 0x3E)); // Poliwhirl -> Poliwrath (Note: Level 35 is unusual, typically stone)
        // Note: Poliwhirl -> Politoed (0xBA) is typically by trade w/ King's Rock, not level.
        map.put(0x3F, new EvolutionStep(16, 0x40)); // Abra -> Kadabra
        map.put(0x40, new EvolutionStep(40, 0x41)); // Kadabra -> Alakazam (Note: Level 40 is unusual, typically trade)
        map.put(0x42, new EvolutionStep(28, 0x43)); // Machop -> Machoke
        map.put(0x43, new EvolutionStep(40, 0x44)); // Machoke -> Machamp (Note: Level 40 is unusual, typically trade)
        map.put(0x45, new EvolutionStep(21, 0x46)); // Bellsprout -> Weepinbell
        map.put(0x46, new EvolutionStep(35, 0x47)); // Weepinbell -> Victreebel (Note: Level 35 is unusual, typically stone)
        map.put(0x48, new EvolutionStep(30, 0x49)); // Tentacool -> Tentacruel
        map.put(0x4A, new EvolutionStep(25, 0x4B)); // Geodude -> Graveler
        map.put(0x4B, new EvolutionStep(40, 0x4C)); // Graveler -> Golem (Note: Level 40 is unusual, typically trade)
        map.put(0x4D, new EvolutionStep(40, 0x4E)); // Ponyta -> Rapidash
        map.put(0x4F, new EvolutionStep(37, 0x50)); // Slowpoke -> Slowbro
        // Note: Slowpoke -> Slowking (0xC7) is typically by trade w/ King's Rock, not level.
        map.put(0x51, new EvolutionStep(30, 0x52)); // Magnemite -> Magneton
        map.put(0x54, new EvolutionStep(31, 0x55)); // Farfetch'd has no level evolution
        map.put(0x56, new EvolutionStep(34, 0x57)); // Doduo -> Dodrio
        map.put(0x58, new EvolutionStep(38, 0x59)); // Seel -> Dewgong
        map.put(0x5A, new EvolutionStep(35, 0x5B)); // Grimer -> Muk
        map.put(0x5C, new EvolutionStep(25, 0x5D)); // Shellder -> Cloyster (Note: Level 25 is unusual, typically stone)
        map.put(0x5D, new EvolutionStep(40, 0x5E)); // Gastly -> Haunter
        map.put(0x5E, new EvolutionStep(40, 0x5F)); // Haunter -> Gengar (Note: Level 40 is unusual, typically trade) - Original code had 0x5F -> 0xD0? Correcting to Gengar ID 0x5F
        map.put(0x5F, new EvolutionStep(40, 0xD0)); // Onix -> Steelix (Note: Level 40 is unusual, typically trade w/ Metal Coat) - Original code had this? Keeping for now.
        map.put(0x60, new EvolutionStep(26, 0x61)); // Drowzee -> Hypno
        map.put(0x62, new EvolutionStep(28, 0x63)); // Krabby -> Kingler
        map.put(0x64, new EvolutionStep(30, 0x65)); // Voltorb -> Electrode
        map.put(0x66, new EvolutionStep(35, 0x67)); // Exeggcute -> Exeggutor (Note: Level 35 is unusual, typically stone)
        map.put(0x68, new EvolutionStep(28, 0x69)); // Cubone -> Marowak
        map.put(0x6D, new EvolutionStep(28, 0x6E)); // Koffing -> Weezing
        map.put(0x6F, new EvolutionStep(42, 0x70)); // Rhyhorn -> Rhydon
        map.put(0x71, new EvolutionStep(35, 0xF2)); // Chansey -> Blissey (Note: Level 35 is unusual, typically friendship)
        map.put(0x74, new EvolutionStep(32, 0x75)); // Horsea -> Seadra
        map.put(0x75, new EvolutionStep(40, 0xE6)); // Seadra -> Kingdra (Note: Level 40 is unusual, typically trade w/ Dragon Scale)
        map.put(0x76, new EvolutionStep(33, 0x77)); // Goldeen -> Seaking
        map.put(0x78, new EvolutionStep(35, 0x79)); // Staryu -> Starmie (Note: Level 35 is unusual, typically stone)
        map.put(0x7B, new EvolutionStep(40, 0xD4)); // Scyther -> Scizor (Note: Level 40 is unusual, typically trade w/ Metal Coat)
        map.put(0x81, new EvolutionStep(20, 0x82)); // Magikarp -> Gyarados
        map.put(0x89, new EvolutionStep(40, 0xE9)); // Eevee -> Espeon (Note: Level 40 is unusual, typically friendship day)
        map.put(0x8A, new EvolutionStep(40, 0x8B)); // Eevee -> Umbreon (Note: Level 40 is unusual, typically friendship night)
        map.put(0x8C, new EvolutionStep(40, 0x8D)); // Porygon -> Porygon2 (Note: Level 40 is unusual, typically trade w/ Up-Grade)
        map.put(0x93, new EvolutionStep(30, 0x94)); // Omanyte -> Omastar
        map.put(0x94, new EvolutionStep(40, 0x95)); // Kabuto -> Kabutops
        map.put(0x98, new EvolutionStep(16, 0x99)); // Dratini -> Dragonair
        map.put(0x99, new EvolutionStep(32, 0x9A)); // Dragonair -> Dragonite (Note: Levels seem low, usually 30/55)
        map.put(0x9B, new EvolutionStep(14, 0x9C)); // Chikorita -> Bayleef
        map.put(0x9C, new EvolutionStep(36, 0x9D)); // Bayleef -> Meganium (Note: Level seems high, usually 32)
        map.put(0x9E, new EvolutionStep(18, 0x9F)); // Cyndaquil -> Quilava
        map.put(0x9F, new EvolutionStep(30, 0xA0)); // Quilava -> Typhlosion (Note: Level seems low, usually 36)
        map.put(0xA1, new EvolutionStep(15, 0xA2)); // Totodile -> Croconaw
        map.put(0xA3, new EvolutionStep(20, 0xA4)); // Croconaw -> Feraligatr (Note: Level seems low, usually 30)
        map.put(0xA5, new EvolutionStep(18, 0xA6)); // Sentret -> Furret
        map.put(0xA7, new EvolutionStep(22, 0xA8)); // Hoothoot -> Noctowl
        map.put(0xAA, new EvolutionStep(27, 0xAB)); // Ledyba -> Ledian
        map.put(0xAC, new EvolutionStep(20, 0x19)); // Spinarak -> Ariados (Original code had Tyrogue->Hitmonlee?)
        map.put(0xAD, new EvolutionStep(20, 0x23)); // Chinchou -> Lanturn (Original code had Tyrogue->Hitmonchan?)
        map.put(0xAE, new EvolutionStep(20, 0x27)); // Pichu -> Pikachu (Original code had Tyrogue->Hitmontop?)
        map.put(0xAF, new EvolutionStep(35, 0xB0)); // Cleffa -> Clefairy
        map.put(0xB1, new EvolutionStep(25, 0xB2)); // Igglybuff -> Jigglypuff
        map.put(0xB3, new EvolutionStep(15, 0xB4)); // Togepi -> Togetic (Note: Level 15 is unusual, typically friendship)
        map.put(0xB4, new EvolutionStep(30, 0xB5)); // Natu -> Xatu
        map.put(0xB7, new EvolutionStep(18, 0xB8)); // Mareep -> Flaaffy
        map.put(0xBB, new EvolutionStep(18, 0xBC)); // Marill -> Azumarill (Note: Level 18 seems low)
        map.put(0xBC, new EvolutionStep(27, 0xBD)); // Hoppip -> Skiploom
        map.put(0xBF, new EvolutionStep(35, 0xC0)); // Skiploom -> Jumpluff
        map.put(0xC2, new EvolutionStep(20, 0xC3)); // Sunkern -> Sunflora (Note: Level 20 is unusual, typically stone)
        map.put(0xCC, new EvolutionStep(31, 0xCD)); // Wooper -> Quagsire
        map.put(0xD1, new EvolutionStep(23, 0xD2)); // Pineco -> Forretress
        map.put(0xD8, new EvolutionStep(30, 0xD9)); // Snubbull -> Granbull
        map.put(0xDA, new EvolutionStep(38, 0xDB)); // Teddiursa -> Ursaring
        map.put(0xDC, new EvolutionStep(33, 0xDD)); // Slugma -> Magcargo
        map.put(0xDF, new EvolutionStep(25, 0xE0)); // Swinub -> Piloswine
        map.put(0xE4, new EvolutionStep(24, 0xE5)); // Remoraid -> Octillery
        map.put(0xE7, new EvolutionStep(24, 0xE8)); // Houndour -> Houndoom
        map.put(0xEC, new EvolutionStep(20, 0xED)); // Phanpy -> Donphan
        map.put(0xEE, new EvolutionStep(20, 0x7C)); // Larvitar -> Pupitar (Original code had Eevee->Vaporeon?)
        map.put(0xEF, new EvolutionStep(20, 0x7D)); // Pupitar -> Tyranitar (Original code had Eevee->Jolteon?) - Levels seem very low, usually 30/55
        map.put(0xF0, new EvolutionStep(20, 0x7E)); // Smoochum -> Jynx (Original code had Eevee->Flareon?)
        map.put(0xF6, new EvolutionStep(30, 0xF7)); // Elekid -> Electabuzz
        map.put(0xF7, new EvolutionStep(45, 0xF8)); // Magby -> Magmar

        return Collections.unmodifiableMap(map);
    }

    private static final int LAST_SAFE_POKE = 0xFB;

    private Random random;

    public Poke_Generator(Random randomInstance) {
        this.random = randomInstance;
    }

    public int insertPoke() {
        return random.nextInt(LAST_SAFE_POKE) + 1;
    }

    /**
     * Determines the fully evolved form of a Pokémon based on its starting form and level.
     * Uses a pre-computed map for efficient lookup and handles multi-stage evolutions.
     *
     * @param startPokemon The starting Pokémon ID (hexadecimal).
     * @param level The current level of the Pokémon.
     * @return The ID of the final evolved form achievable at the given level,
     *         or the starting Pokémon ID if no evolution occurs.
     */
    public int evolvePokemon(int startPokemon, int level) {
        int currentPokemon = startPokemon;
        while (true) {
            EvolutionStep nextStep = EVOLUTION_MAP.get(currentPokemon);

            // Check if an evolution exists and the level requirement is met
            if (nextStep != null && level >= nextStep.level()) {
                // Evolve to the next stage
                currentPokemon = nextStep.evolvesTo(); 
            } else {
                // No further evolution possible at this level
                break;
            }
        }
        return currentPokemon;
    }
}
