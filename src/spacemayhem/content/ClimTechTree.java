package spacemayhem.content;

import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.game.Objectives.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.blocks.defense.turrets.*;

import static spacemayhem.content.SpaceMayhemBlocks.*;
import static spacemayhem.content.SpaceMayhemItems.*;
import static spacemayhem.content.SpaceMayhemLiquids.*;
import static mindustry.Vars.*;
import static mindustry.content.Items.*;
import static mindustry.content.TechTree.*;

public class ClimTechTree {
    public static void load() {
        SpaceMayhemPlanets.clim.techTree = nodeRoot("clim", coreIsland, false, () -> {
            nodeProduce(whiteGas, ()-> {
                nodeProduce(gasMatter, ()-> {
                    nodeProduce(gasScrap, ()-> {

                    });
                });
            });
            node(gasCollector, ()-> {
                node(gasCooler, ()-> {

                });
                node(gasTurbine, ()-> {

                });
            });
            node(blow, ()-> {
                node(blast, ()-> {

                });
            });
            node(platformNode, ()-> {
                node(platformNodeLarge, ()-> {

                });
            });
            node(siliconDuct, ()-> {
                node(gasNode, ()-> {

                });
            });
        });
    }
}