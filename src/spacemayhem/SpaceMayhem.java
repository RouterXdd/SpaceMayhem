package spacemayhem;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.core.*;
import mindustry.type.*;
import mindustry.ui.fragments.*;
import mindustry.ui.dialogs.SettingsMenuDialog.*;
import mindustry.ui.dialogs.SettingsMenuDialog.SettingsTable.*;
import spacemayhem.content.*;
import spacemayhem.graphics.*;

public class SpaceMayhem extends Mod{

    public SpaceMayhem(){
    }

    @Override
    public void loadContent(){
        new SpaceMayhemItems().load();
        new SpaceMayhemLiquids().load();
        new SpaceMayhemBlocks().load();
        new SpaceMayhemPlanets().load();
        new GierTechTree().load();
    }
}
