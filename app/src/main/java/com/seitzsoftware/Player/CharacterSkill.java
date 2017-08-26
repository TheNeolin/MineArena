package com.seitzsoftware.Player;

import com.seitzsoftware.database.DBColumn;

/**
 * Created by vette on 6/25/2017.
 */

public class CharacterSkill {

    String skillName;
    int skillLevel = 1;
    DBColumn dbColumn;

    public CharacterSkill(String skillName){
        this.skillName = skillName;
    }
}
