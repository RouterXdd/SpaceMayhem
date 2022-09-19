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
        Planets.gier.accessible = Planets.gier.alwaysUnlocked = true;
        Planets.notva.accessible = Planets.notva.alwaysUnlocked = true;
        Planets.verilus.accessible = Planets.verilus.alwaysUnlocked = true;
    }

    @Override
    public void loadContent(){
        new SpaceMayhemItems().load();
        new SpaceMayhemBlocks().load();
    }
}
