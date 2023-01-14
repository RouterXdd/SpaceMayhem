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
import static mindustry.Vars.*;
import static mindustry.content.Items.*;
import static mindustry.content.TechTree.*;

public class GierTechTree {
    public static void load() {
        Planets.gier.techTree = nodeRoot("gier", coreCampfire, false, () -> {
            nodeProduce(SpaceMayhemItems.iron, ()-> {
                nodeProduce(SpaceMayhemItems.francium, ()-> {

                });
                nodeProduce(copper, ()-> {
                    nodeProduce(lead, ()-> {
                        nodeProduce(graphite, ()-> {
                            nodeProduce(coal, ()-> {
                                nodeProduce(silicon, ()-> {
                                    nodeProduce(thorium, ()-> {

                                    });
                                });
                            });
                            nodeProduce(titanium, ()-> {
                                nodeProduce(surgeAlloy, ()-> {

                                });
                            });
                        });
                    });
                });
            });
            node(ironDrell, ()-> {

            });
            node(volcano, ()-> {
                node(flame, ()-> {

                });
                node(eruption, ()-> {

                });
            });
        });
    }
}
