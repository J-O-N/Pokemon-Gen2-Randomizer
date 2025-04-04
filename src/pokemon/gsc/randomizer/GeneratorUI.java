package pokemon.gsc.randomizer;

// Imports will be added as UI components are moved
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.File;
import java.util.Random; // Added import

public class GeneratorUI extends JPanel implements ItemListener, ActionListener { // Implement listeners here

    // UI Components moved from GeneratorPanel
    JPanel area1, area2, area3, area4, area5, TMPanel, GamePanel, PokePanel, TrainerPanel, StarterPanel;
    TitledBorder TMBorder, GameBorder, PokeBorder, TrainerBorder, StarterBorder;
    JCheckBox TMCont, TMCompat, GamePokeGift, GameEventPoke, GameWildPoke, PokeStats, PokeTypes, PokeMovesets,
            PokeLevels, PokeLevelsWild, PokeLevelsTrainer, PokeLevelsGifts, PokeTrade, TrainerRival, TrainerNames, TrainerPoke,
            StarterStarters, StarterItems, StarterItemsKeys;
    JButton openROM, saveROM, close;
    JFrame confirm; // This might need careful handling - maybe it belongs in the main app frame?
    JLabel confirmText, seedLabel;
    JTextField seedField;

    // Reference to the core logic
    private GeneratorPanel generatorLogic; // Now instantiated here

    public GeneratorUI() {
        // Instantiate the logic class
        this.generatorLogic = new GeneratorPanel();

        // Set the layout for this panel (GeneratorUI)
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initComponents();
        layoutComponents();
    }

    private void initComponents() {
        // Component initialization logic moved from GeneratorPanel constructor

        // Panels and Borders
        area1 = new JPanel();
        area1.setLayout(new BoxLayout(area1, BoxLayout.Y_AXIS));
        area1.setAlignmentY(TOP_ALIGNMENT);
        area2 = new JPanel();
        area2.setLayout(new BoxLayout(area2, BoxLayout.Y_AXIS));
        area2.setAlignmentY(TOP_ALIGNMENT);
        area3 = new JPanel();
        area3.setLayout(new BoxLayout(area3, BoxLayout.Y_AXIS));
        area3.setAlignmentY(TOP_ALIGNMENT);
        area4 = new JPanel();
        area4.setLayout(new BoxLayout(area4, BoxLayout.X_AXIS));
        area4.setAlignmentY(TOP_ALIGNMENT);
        area5 = new JPanel();
        area5.setLayout(new BoxLayout(area5, BoxLayout.X_AXIS));
        area5.setAlignmentY(TOP_ALIGNMENT);

        TMPanel = new JPanel();
        TMBorder = new TitledBorder("TM Options");
        TMPanel.setBorder(TMBorder);
        TMPanel.setLayout(new BoxLayout(TMPanel, BoxLayout.Y_AXIS));

        GamePanel = new JPanel();
        GameBorder = new TitledBorder("Game Options");
        GamePanel.setBorder(GameBorder);
        GamePanel.setLayout(new BoxLayout(GamePanel, BoxLayout.Y_AXIS));

        PokePanel = new JPanel();
        PokeBorder = new TitledBorder("Pokemon Options");
        PokePanel.setBorder(PokeBorder);
        PokePanel.setLayout(new BoxLayout(PokePanel, BoxLayout.Y_AXIS));

        TrainerPanel = new JPanel();
        TrainerBorder = new TitledBorder("Trainer Options");
        TrainerPanel.setBorder(TrainerBorder);
        TrainerPanel.setLayout(new BoxLayout(TrainerPanel, BoxLayout.Y_AXIS));

        StarterPanel = new JPanel();
        StarterBorder = new TitledBorder("Starter Options");
        StarterPanel.setBorder(StarterBorder);
        StarterPanel.setLayout(new BoxLayout(StarterPanel, BoxLayout.Y_AXIS));

        // Buttons and Seed Field
        openROM = new JButton("Open ROM");
        openROM.setToolTipText("Loads up a ROM.");
        openROM.addActionListener(this);
        saveROM = new JButton("Save ROM");
        saveROM.setToolTipText("Saves a new ROM with the selected settings");
        saveROM.addActionListener(this);

        seedLabel = new JLabel("Seed (Optional):");
        seedField = new JTextField();
        seedField.setToolTipText("Enter a number (long) to use as a specific seed, or leave blank for random.");
        seedField.setMaximumSize(new Dimension(Integer.MAX_VALUE, seedField.getPreferredSize().height));

        // Confirmation Dialog Initialization (moved from GeneratorPanel)
        confirm = new JFrame("Rom randomization complete.");
        confirm.setLayout(new BoxLayout(confirm.getContentPane(), BoxLayout.Y_AXIS));
        confirmText = new JLabel("<html><center>Randomization Complete.<br>The rom is at: <br>");
        confirmText.setBorder(new EmptyBorder(5, 5, 5, 5));
        confirm.add(confirmText);
        close = new JButton("Close");
        close.addActionListener(this); // Action handled in this class now
        confirm.add(close);
        confirm.pack();
        confirm.setVisible(false);
        confirm.setAlwaysOnTop(true);
        close.setToolTipText("See this button? See how it's not centered? Fuck this button. >:C");
        confirm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Dispose instead of exit

        // Checkboxes
        TMCont = new JCheckBox("TM contents");
        TMCont.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        TMCont.setToolTipText("Randomizes the contents of TMs. Dialoge regarding the TMs are not updated");
        TMCont.addItemListener(this); // Point listener to this UI class for now
        TMCompat = new JCheckBox("TM/HM compatability");
        TMCompat.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        TMCompat.setToolTipText("Randomizes which pokemon can learn what TMs and HMs");
        TMCompat.addItemListener(this); // Point listener to this UI class for now

        GamePokeGift = new JCheckBox("Gifted Pokemon");
        GamePokeGift.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        GamePokeGift.setToolTipText("The free pokemon you recieve throughout the game, like the spearow, and the shuckle");
        GamePokeGift.addItemListener(this);
        GameEventPoke = new JCheckBox("Event Pokemon");
        GameEventPoke.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        GameEventPoke.setToolTipText("The pokemon you encounter from events. Like the Red Gyarados, and the Electrodes in Mahogany");
        GameEventPoke.addItemListener(this);
        GameWildPoke = new JCheckBox("Wild Pokemon");
        GameWildPoke.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        GameWildPoke.setToolTipText("This BETTER BE ON, OR I WILL HURT YOU >:C");
        GameWildPoke.addItemListener(this);

        PokeStats = new JCheckBox("Stats");
        PokeStats.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        PokeStats.setToolTipText("Not currently supported.");
        PokeStats.addItemListener(this);
        PokeTypes = new JCheckBox("Types");
        PokeTypes.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        PokeTypes.setToolTipText("Not currently supported.");
        PokeTypes.addItemListener(this);
        PokeMovesets = new JCheckBox("Movesets");
        PokeMovesets.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        PokeMovesets.setToolTipText("The moves pokemon learn at their level benchmarks. Cannot be HMs, because that's broken");
        PokeMovesets.addItemListener(this);
        PokeLevels = new JCheckBox("Levels");
        PokeLevels.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        PokeLevels.setToolTipText("Enables the check boxes below. Be aware that these pretty much make the game unplayable");
        PokeLevels.addItemListener(this);
        PokeLevelsWild = new JCheckBox("Wild");
        PokeLevelsWild.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        PokeLevelsWild.setBorder(new EmptyBorder(0, 20, 0, 0));
        PokeLevelsWild.setToolTipText("Randomize the levels of wild and event pokemon");
        PokeLevelsWild.addItemListener(this);
        PokeLevelsTrainer = new JCheckBox("Trainer");
        PokeLevelsTrainer.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        PokeLevelsTrainer.setBorder(new EmptyBorder(0, 20, 0, 0));
        PokeLevelsTrainer.setToolTipText("Randomize the levels of trainer's pokemon");
        PokeLevelsTrainer.addItemListener(this);
        PokeLevelsGifts = new JCheckBox("Gifts");
        PokeLevelsGifts.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        PokeLevelsGifts.setBorder(new EmptyBorder(0, 20, 0, 0));
        PokeLevelsGifts.setToolTipText("Randomize the levels of pokemon gifts to the player");
        PokeLevelsGifts.addItemListener(this);
        PokeTrade = new JCheckBox("Trades evolve at 40");
        PokeTrade.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        PokeTrade.setToolTipText("Instead of having to trade for certain pokemon, they evolve at level 40. Kadabra -> Alakazam for example");
        PokeTrade.addItemListener(this);

        TrainerPoke = new JCheckBox("Pokemon");
        TrainerPoke.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        TrainerPoke.setToolTipText("Randomizes the pokemon trainers have. If their fight had their moves hardcoded in, those will be randomized too");
        TrainerPoke.addItemListener(this);
        TrainerNames = new JCheckBox("Names");
        TrainerNames.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        TrainerNames.setToolTipText("Replaces trainers' names from the name pool. You'll probably see a lot of repeats");
        TrainerNames.addItemListener(this);
        TrainerRival = new JCheckBox("Rival keeps starter");
        TrainerRival.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        TrainerRival.setToolTipText("The rival keeps the starter he selects at the beginning of the game");
        TrainerRival.addItemListener(this);

        StarterStarters = new JCheckBox("Random starters");
        StarterStarters.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        StarterStarters.setToolTipText("Turn this on and leave it on");
        StarterStarters.addItemListener(this);
        StarterItems = new JCheckBox("Random Items");
        StarterItems.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        StarterItems.setToolTipText("Instead of a Berry, the starter (AND SHUCKIE), will be holding a random item. Shuckie and the starter do not necessarily have the same item.");
        StarterItems.addItemListener(this);
        StarterItemsKeys = new JCheckBox("Ban Key Items");
        StarterItemsKeys.setAlignmentX(JComponent.LEFT_ALIGNMENT);
        StarterItemsKeys.setBorder(new EmptyBorder(0, 20, 0, 0));
        StarterItemsKeys.setToolTipText("Removes Key Items from the pool of possible items. Like the SQUIRTBOTTLE and SUPER ROD");
        StarterItemsKeys.addItemListener(this);

        // Initial state (disabled)
        TMCont.setEnabled(false);
        TMCompat.setEnabled(false);
        GamePokeGift.setEnabled(false);
        GameEventPoke.setEnabled(false);
        GameWildPoke.setEnabled(false);
        PokeStats.setEnabled(false);
        PokeTypes.setEnabled(false);
        PokeMovesets.setEnabled(false);
        PokeLevels.setEnabled(false);
        PokeLevelsWild.setEnabled(false);
        PokeLevelsTrainer.setEnabled(false);
        PokeLevelsGifts.setEnabled(false);
        PokeTrade.setEnabled(false);
        TrainerPoke.setEnabled(false);
        TrainerNames.setEnabled(false);
        TrainerRival.setEnabled(false);
        StarterStarters.setEnabled(false);
        StarterItems.setEnabled(false);
        StarterItemsKeys.setEnabled(false);
    }

    private void layoutComponents() {
        // Layout logic moved from GeneratorPanel constructor
        add(area4); // Add main areas to this GeneratorUI panel
        add(area5);

        area5.add(openROM);
        area5.add(saveROM);
        area5.add(seedLabel);
        area5.add(seedField);

        area4.add(area1);
        area4.add(area2);
        area4.add(area3);

        area1.add(TMPanel);
        area1.add(GamePanel);

        area2.add(PokePanel);
        area3.add(TrainerPanel);
        area3.add(StarterPanel);

        // Add checkboxes to their respective panels
        TMPanel.add(TMCont);
        TMPanel.add(TMCompat);

        GamePanel.add(GamePokeGift);
        GamePanel.add(GameEventPoke);
        GamePanel.add(GameWildPoke);

        PokePanel.add(PokeStats);
        PokePanel.add(PokeTypes);
        PokePanel.add(PokeMovesets);
        PokePanel.add(PokeLevels);
        PokePanel.add(PokeLevelsWild);
        PokePanel.add(PokeLevelsTrainer);
        PokePanel.add(PokeLevelsGifts);
        PokePanel.add(PokeTrade);

        TrainerPanel.add(TrainerRival);
        TrainerPanel.add(TrainerPoke);
        TrainerPanel.add(TrainerNames);

        StarterPanel.add(StarterStarters);
        StarterPanel.add(StarterItems);
        StarterPanel.add(StarterItemsKeys);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == openROM) {
             // Handle file choosing here
             JFileChooser choose = new JFileChooser();
             int returnVal = choose.showOpenDialog(this);
             if(returnVal == JFileChooser.APPROVE_OPTION){
                 File selectedFile = choose.getSelectedFile();
                 // Call logic class method with the selected file
                 boolean success = generatorLogic.openROM(selectedFile);
                 if (success) {
                     updateUIStateAfterROMLoad(); // Update UI only if ROM loading succeeded
                 } else {
                     // Optionally show an error message if ROM loading failed
                     JOptionPane.showMessageDialog(this, "Failed to load or process the selected ROM.", "ROM Load Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
         }
         else if (e.getSource() == saveROM) { // Use else if for clarity
             long seed;
             String seedText = seedField.getText().trim();
             if (!seedText.isEmpty()) {
                 try {
                     seed = Long.parseLong(seedText);
                 } catch (NumberFormatException nfe) {
                     seed = new Random().nextLong(); // Generate random if invalid
                     seedField.setText(String.valueOf(seed)); // Show generated seed
                     System.out.println("Invalid seed format, generated: " + seed);
                 }
             } else {
                 seed = new Random().nextLong(); // Generate random if empty
                 seedField.setText(String.valueOf(seed)); // Show generated seed
                 System.out.println("No seed provided, generated: " + seed);
             }

             File saveFile = generatorLogic.randomizeROM(seed); // Call logic class method with seed

             // Show confirmation dialog
             if (saveFile != null) {
                 confirmText.setText("<html><center>Randomization Complete.<br>The rom is at: <br>" + saveFile.getAbsolutePath() + "</center></html>");
                 confirm.pack(); // Repack to fit new text
                 confirm.setVisible(true);
             } else {
                 // Handle error? Maybe show a message dialog.
                 JOptionPane.showMessageDialog(this, "Randomization failed. Ensure a ROM is loaded.", "Error", JOptionPane.ERROR_MESSAGE);
             }
         }
         if (e.getSource() == close) {
             confirm.setVisible(false); // Just hide the confirmation dialog
             // System.exit(0); // Don't exit the whole app
         }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
         // Update boolean flags in generatorLogic based on checkbox changes
         Object source = e.getItemSelectable();
         boolean selected = e.getStateChange() == ItemEvent.SELECTED;

         if (source == TMCont) generatorLogic.BTMCont = selected;
         else if (source == TMCompat) generatorLogic.BTMCompat = selected;
         else if (source == GameEventPoke) generatorLogic.BGameEventPoke = selected;
         else if (source == GamePokeGift) generatorLogic.BGamePokeGift = selected;
         else if (source == GameWildPoke) generatorLogic.BGameWildPoke = selected;
         else if (source == PokeStats) generatorLogic.BPokeStats = selected;
         else if (source == PokeTypes) generatorLogic.BPokeTypes = selected;
         else if (source == PokeMovesets) generatorLogic.BPokeMovesets = selected;
         else if (source == PokeLevelsWild) generatorLogic.BPokeLevelsWild = selected;
         else if (source == PokeLevelsTrainer) generatorLogic.BPokeLevelsTrainer = selected;
         else if (source == PokeLevelsGifts) generatorLogic.BPokeLevelsGifts = selected;
         else if (source == PokeTrade) generatorLogic.BPokeTrade = selected;
         else if (source == TrainerRival) generatorLogic.BTrainerRival = selected;
         else if (source == TrainerNames) generatorLogic.BTrainerNames = selected;
         else if (source == TrainerPoke) generatorLogic.BTrainerPoke = selected;
         else if (source == StarterStarters) generatorLogic.BStarterStarters = selected;
         else if (source == StarterItemsKeys) generatorLogic.BStarterItemsKeys = selected;
         else if (source == StarterItems) {
             StarterItemsKeys.setEnabled(selected);
             if (!selected) {
                 StarterItemsKeys.setSelected(false);
                 generatorLogic.BStarterItemsKeys = false; // Ensure flag is updated when disabled
             }
             generatorLogic.BStarterItems = selected; // Update the flag
         } else if (source == PokeLevels) {
              PokeLevelsWild.setEnabled(selected);
              PokeLevelsTrainer.setEnabled(selected);
              PokeLevelsGifts.setEnabled(selected);
              if (!selected) {
                  PokeLevelsWild.setSelected(false);
                  PokeLevelsTrainer.setSelected(false);
                  PokeLevelsGifts.setSelected(false);
                  generatorLogic.BPokeLevelsWild = false;
                  generatorLogic.BPokeLevelsTrainer = false;
                  generatorLogic.BPokeLevelsGifts = false;
              }
              generatorLogic.BPokeLevels = selected; // Update the flag
         }
    }

     /**
      * Updates the enabled/selected state of UI components after a ROM is loaded.
      * This logic was moved from GeneratorPanel.openROM().
      */
     public void updateUIStateAfterROMLoad() {
         // Set initial selected state based on logic class flags
         TMCont.setSelected(generatorLogic.BTMCont);
         TMCompat.setSelected(generatorLogic.BTMCompat);
         GamePokeGift.setSelected(generatorLogic.BGamePokeGift);
         GameEventPoke.setSelected(generatorLogic.BGameEventPoke);
         GameWildPoke.setSelected(generatorLogic.BGameWildPoke);
         PokeStats.setSelected(generatorLogic.BPokeStats);
         PokeTypes.setSelected(generatorLogic.BPokeTypes);
         PokeMovesets.setSelected(generatorLogic.BPokeMovesets);
         PokeLevels.setSelected(generatorLogic.BPokeLevels);
         PokeLevelsWild.setSelected(generatorLogic.BPokeLevelsWild);
         PokeLevelsTrainer.setSelected(generatorLogic.BPokeLevelsTrainer);
         PokeLevelsGifts.setSelected(generatorLogic.BPokeLevelsGifts);
         PokeTrade.setSelected(generatorLogic.BPokeTrade);
         TrainerPoke.setSelected(generatorLogic.BTrainerPoke);
         TrainerNames.setSelected(generatorLogic.BTrainerNames);
         TrainerRival.setSelected(generatorLogic.BTrainerRival);
         StarterStarters.setSelected(generatorLogic.BStarterStarters);
         StarterItems.setSelected(generatorLogic.BStarterItems);
         StarterItemsKeys.setSelected(generatorLogic.BStarterItemsKeys);

         // Set enabled state
         TMCont.setEnabled(true);
         TMCompat.setEnabled(true);
         GamePokeGift.setEnabled(true);
         GameEventPoke.setEnabled(true);
         GameWildPoke.setEnabled(true);
         PokeStats.setEnabled(false); // Still not supported
         PokeTypes.setEnabled(false); // Still not supported
         PokeMovesets.setEnabled(true);
         PokeLevels.setEnabled(true);
         // Enable/disable dependent checkboxes based on the state of PokeLevels and StarterItems
         PokeLevelsWild.setEnabled(PokeLevels.isSelected());
         PokeLevelsTrainer.setEnabled(PokeLevels.isSelected());
         PokeLevelsGifts.setEnabled(PokeLevels.isSelected());
         PokeTrade.setEnabled(true);
         TrainerPoke.setEnabled(true);
         TrainerNames.setEnabled(true);
         TrainerRival.setEnabled(true);
         StarterStarters.setEnabled(true);
         StarterItems.setEnabled(true);
         StarterItemsKeys.setEnabled(StarterItems.isSelected());
     }
}
