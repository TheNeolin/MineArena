package com.seitzsoftware.Player;

import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.game.game.state.CharacterState;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vette on 6/11/2017.
 */

public class Character {

    public Ore coal_Ore,copper_Ore,iron_Ore,silver_Ore,gold_Ore,mithril_Ore,rune_Ore,obsidian_Ore,dragon_Ore,adamantite_Ore;
    public Bar copper_Bar,iron_Bar,silver_Bar,gold_Bar,mithril_Bar,rune_Bar,obsidian_Bar,dragon_Bar,adamantite_Bar;
    public CharacterStat totalStrength,totalStamina;
    public CharacterSkill mining_skill;

    public List<Ore> Ores;
    public List<Bar> Bars;
    public List<Armor> Armor;
    public List<CharacterStat> CharacterStats;
    public List<CharacterSkill> CharacterSkills;

    public Armor helmet,shoulders,chest,gloves,pants,boots,shield;
    public Weapon weapon;

    public Character(){
        loadOres();
        getOres();
        loadBars();
        getBars();
        loadArmor();
        getArmor();
        loadStats();
        getStats();
        loadSkills();
        getCharacterSkills();
    }

    public void loadCharacter(){
        loadOreAmounts();
        loadBarAmounts();
        loadArmorItems();
        loadCharacterStats();
    }

    public List<CharacterStat> getStats(){
        CharacterStats = Arrays.asList(totalStrength,totalStamina);
        return CharacterStats;
    }

    public List<Ore> getOres() {
        Ores = Arrays.asList(coal_Ore,copper_Ore,iron_Ore,silver_Ore,gold_Ore,mithril_Ore,rune_Ore,obsidian_Ore,dragon_Ore,adamantite_Ore);
        return Ores;
    }

    public List<Ore> getOresReverse() {
        Ores = Arrays.asList(adamantite_Ore,dragon_Ore,obsidian_Ore,rune_Ore,mithril_Ore,gold_Ore,silver_Ore,iron_Ore,copper_Ore,coal_Ore);
        return Ores;
    }

    public List<Bar> getBars() {
        Bars = Arrays.asList(copper_Bar,iron_Bar,silver_Bar,gold_Bar,mithril_Bar,rune_Bar,obsidian_Bar,dragon_Bar,adamantite_Bar);
        return Bars;
    }

    public List<Armor> getArmor() {
        Armor = Arrays.asList(helmet,shoulders,chest,gloves,pants,boots,shield);
        return Armor;
    }

    public List<CharacterSkill> getCharacterSkills() {
        CharacterSkills = Arrays.asList(mining_skill);
        return CharacterSkills;
    }

    public void loadSkills(){
        mining_skill = new CharacterSkill("Mining");
    }

    public void loadOres(){
        coal_Ore = new Ore("coal_ore.png","Coal Ore","coal_ore",1,0);
        copper_Ore = new Ore("copper_ore.png","Copper Ore","copper_ore",5000,5);
        iron_Ore = new Ore("iron_ore.png","Iron Ore","iron_ore",6500,10);
        silver_Ore = new Ore("silver_ore.png","Silver Ore","silver_ore",7500,20);
        gold_Ore = new Ore("gold_ore.png","Gold Ore","gold_ore",8400,30);
        mithril_Ore = new Ore("mithril_ore.png","Mithril Ore","mithril_ore",9100,50);
        rune_Ore = new Ore("rune_ore.png","Rune Ore","rune_ore",9600,100);
        obsidian_Ore = new Ore("obsidian_ore.png","Obsidian Ore","obsidian_ore",9900,300);
        dragon_Ore = new Ore("dragon_ore.png","Dragon Ore","dragon_ore",9990,500);
        adamantite_Ore = new Ore("adamantite_ore.png","Adamantite Ore","adamantite_ore",9999,1000);
    }

    public void loadBars(){
        copper_Bar = new Bar("copper_bar.png","Copper Bar","copper_bar","Copper Ore");
        iron_Bar = new Bar("iron_bar.png","Iron Bar","iron_bar","Iron Ore");
        silver_Bar = new Bar("silver_bar.png","Silver Bar","silver_bar","Silver Ore");
        gold_Bar = new Bar("gold_bar.png","Gold Bar","gold_bar","Gold Ore");
        mithril_Bar = new Bar("mithril_bar.png","Mithril Bar","mithril_bar","Mithril Ore");
        rune_Bar = new Bar("rune_bar.png","Rune Bar","rune_bar","Rune Ore");
        obsidian_Bar = new Bar("obsidian_bar.png","Obsidian Bar","obsidian_bar","Obsidian Ore");
        dragon_Bar = new Bar("dragon_bar.png","Dragon Bar","dragon_bar","Dragon Ore");
        adamantite_Bar = new Bar("adamantite_bar.png","Adamantite Bar","adamantite_bar","Adamantite Ore");
    }

    public void loadArmor(){
        helmet = new Armor("helmet",1,1,1,1,0,1,0,0,0);
        shoulders = new Armor("shoulders",0,0,0,1,1,1,0,0,0);
        chest = new Armor("chest",1,1,1,1,1,1,1,1,1);
        gloves = new Armor("gloves",0,0,0,1,0,1,1,0,1);
        pants = new Armor("pants",0,0,0,1,1,1,1,0,1);
        boots = new Armor("boots",0,0,0,0,0,0,1,0,1);
        shield = new Armor("shield",0,0,0,0,1,1,0,1,1);
    }

    public void loadStats(){
        totalStrength = new CharacterStat("Stength",0);
        totalStamina = new CharacterStat("Stamina",0);
    }

    public void loadArmorItems(){
        int armorTier = 1;
        for (Armor a : getArmor()){
            a.material = GameMainActivity.dbHandler.GetItemString(GameMainActivity.getUserName(),a.dBColumn.getObjDBColumnName());
            a.materialTier = armorTier;
            armorTier ++;
        }
    }

    public void loadWeapons(){
        //sword = new Weapon()
    }

    public void loadOreAmounts(){
        for (Ore o : getOres()) {
            o.characterAmount = GameMainActivity.dbHandler.GetItemAmount(GameMainActivity.getUserName(),o.dBColumn.getObjDBColumnName());
            o.amountInFurnace = 0;
            o.amountInFurnaceQueue = 0;
        }
    }

    public void loadBarAmounts(){
        for (Bar b : getBars()) {
            b.characterAmount = GameMainActivity.dbHandler.GetItemAmount(GameMainActivity.getUserName(),b.dBColumn.getObjDBColumnName());
            b.amountInForge = 0;
        }
    }

    public void loadCharacterStats(){
        int Stength = 0;
        int Stamina = 0;
        for (Armor a : getArmor()){
            a.loadArmorAttributes();
            Stength += a.strengthValue;
            Stamina += a.staminaValue;
        }
        totalStrength.statValue = Stength;
        totalStamina.statValue = Stamina;
    }

}
