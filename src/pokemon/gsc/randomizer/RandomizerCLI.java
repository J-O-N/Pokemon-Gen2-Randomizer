package pokemon.gsc.randomizer;

import java.io.File;
import java.util.Random;
// Consider using a proper CLI parsing library later (e.g., Apache Commons CLI, picocli)

public class RandomizerCLI {

    public static void main(String[] args) {
        // Basic argument parsing (replace with a library later)
        String inputRomPath = null;
        String outputRomPath = null; // Optional, can default based on input
        Long seed = null;
        // Flags for randomization options (default to false)
        boolean tmCont = false, tmCompat = false, giftPoke = false, eventPoke = false, wildPoke = false;
        boolean movesets = false, tradeEvo = false, trainerPoke = false, trainerNames = false;
        boolean rivalStarter = false, starters = false, starterItems = false, banKeys = false;
        // Note: Stat/Type/Level randomization flags are omitted as they are not supported/used currently

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-in":
                    if (i + 1 < args.length) inputRomPath = args[++i];
                    break;
                case "-out":
                     if (i + 1 < args.length) outputRomPath = args[++i];
                    break;
                case "-seed":
                     if (i + 1 < args.length) {
                         try {
                             seed = Long.parseLong(args[++i]);
                         } catch (NumberFormatException e) {
                             System.err.println("Invalid seed value provided.");
                             printUsage();
                             return;
                         }
                     }
                    break;
                // Randomization Flags
                case "-tmCont": tmCont = true; break;
                case "-tmCompat": tmCompat = true; break;
                case "-giftPoke": giftPoke = true; break;
                case "-eventPoke": eventPoke = true; break;
                case "-wildPoke": wildPoke = true; break;
                case "-movesets": movesets = true; break;
                case "-tradeEvo": tradeEvo = true; break;
                case "-trainerPoke": trainerPoke = true; break;
                case "-trainerNames": trainerNames = true; break;
                case "-rivalStarter": rivalStarter = true; break;
                case "-starters": starters = true; break;
                case "-starterItems": starterItems = true; break;
                case "-banKeys": banKeys = true; break;
                // Add other flags here if needed in the future

                default:
                    System.err.println("Unknown option: " + args[i]);
                    printUsage();
                    return;
            }
        }

        if (inputRomPath == null) {
            System.err.println("Input ROM path (-in) is required.");
            printUsage();
            return;
        }

        File inputFile = new File(inputRomPath);
        if (!inputFile.exists() || !inputFile.isFile()) {
             System.err.println("Input ROM not found or is not a file: " + inputRomPath);
             return;
        }

        if (seed == null) {
            seed = new Random().nextLong();
            System.out.println("Seed not specified, generated: " + seed);
        } else {
            System.out.println("Using provided seed: " + seed);
        }

        // Determine output path if not provided
        if (outputRomPath == null) {
            String inputName = inputFile.getName();
            String baseName = inputName.substring(0, inputName.lastIndexOf('.'));
            String extension = inputName.substring(inputName.lastIndexOf('.'));
            // Generate seed first if not provided, so we can use it in the default filename
            outputRomPath = baseName + "_" + seed + extension; // Use seed in default output path
            System.out.println("Output ROM path not specified, defaulting to: " + outputRomPath);
        }

        // --- Core Logic ---
        Generator generator = new Generator();

        System.out.println("Loading ROM: " + inputFile.getAbsolutePath());
        boolean loadSuccess = generator.openROM(inputFile);

        if (!loadSuccess) {
            System.err.println("Failed to load ROM.");
            return;
        }

        // Set boolean flags based on parsed CLI arguments
        generator.BTMCont = tmCont;
        generator.BTMCompat = tmCompat;
        generator.BGamePokeGift = giftPoke;
        generator.BGameEventPoke = eventPoke;
        generator.BGameWildPoke = wildPoke;
        generator.BPokeMovesets = movesets;
        generator.BPokeTrade = tradeEvo;
        generator.BTrainerPoke = trainerPoke;
        generator.BTrainerNames = trainerNames;
        generator.BTrainerRival = rivalStarter;
        generator.BStarterStarters = starters;
        generator.BStarterItems = starterItems;
        generator.BStarterItemsKeys = banKeys;

        System.out.println("Randomizing ROM with seed: " + seed);
        // Need to modify Generator.randomizeROM to accept output path
        // For now, the output path is still hardcoded within randomizeForGold/Crystal
        // TODO: Refactor Generator.java to accept output path

        File outputFile = generator.randomizeROM(seed, outputRomPath); // Pass the seed

        if (outputFile != null) {
             System.out.println("Randomization complete!");
             System.out.println("Output ROM saved to: " + outputFile.getAbsolutePath());
             // We might want Generator.randomizeROM to actually use the outputRomPath argument
        } else {
            System.err.println("Randomization failed.");
        }
    }

    private static void printUsage() {
        System.err.println("Usage: java -cp bin pokemon.gsc.randomizer.RandomizerCLI -in <input_rom_path> [-out <output_rom_path>] [-seed <seed_value>] [flags...]");
        System.err.println("Flags:");
        System.err.println("  -tmCont        Randomize TM contents");
        System.err.println("  -tmCompat      Randomize TM/HM compatibility");
        System.err.println("  -giftPoke      Randomize gifted Pokemon");
        System.err.println("  -eventPoke     Randomize event Pokemon (Legendaries, Snorlax, etc.)");
        System.err.println("  -wildPoke      Randomize wild Pokemon encounters");
        System.err.println("  -movesets      Randomize Pokemon level-up movesets");
        System.err.println("  -tradeEvo      Make trade evolutions evolve at level 40 instead");
        System.err.println("  -trainerPoke   Randomize Trainer Pokemon rosters");
        System.err.println("  -trainerNames  Randomize Trainer names");
        System.err.println("  -rivalStarter  Make Rival keep their chosen starter");
        System.err.println("  -starters      Randomize the three starter Pokemon");
        System.err.println("  -starterItems  Randomize held items for starters (and Shuckle)");
        System.err.println("  -banKeys       Exclude Key Items when randomizing starter items (requires -starterItems)");
        // Add more flag descriptions here if needed
    }
}
