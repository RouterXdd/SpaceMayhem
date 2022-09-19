package spacemayhem.content;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import spacemayhem.SpaceMayhem;
import spacemayhem.graphics.*;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;



public class SpaceMayhemBlocks {
    public static Block
    //environment
    oreIron, oreFrancium, oreUranium, oreOsmium,
    //turrets
    flame;
    public static void load() {
        oreIron = new OreBlock(SpaceMayhemItems.iron) {{
        }};
        oreFrancium = new OreBlock(SpaceMayhemItems.francium) {{
        }};
        oreUranium = new OreBlock(SpaceMayhemItems.uranium) {{
        }};
        oreOsmium = new OreBlock(SpaceMayhemItems.osmium) {{
        }};
        flame = new ItemTurret("flame"){{
            requirements(Category.turret, with(
                    SpaceMayhemItems.iron, 80,
                    Items.lead, 60,
                    Items.silicon, 60,
                    Items.coal,30
            ));
            scaledHealth = 140;
            size = 2;
            reload = 50f;
            range = 178f;
            recoil = 1.4f;
            shoot = new ShootSpread(16, 1.4f);
            coolant = consumeCoolant(0.4f);
            ammo(
                    Items.coal, new BasicBulletType(){{
                        height = 5f;
                        width = 7f;
                        speed = 3f;
                        lifetime = 59f;
                        ammoMultiplier = 2f;
                        damage = 3f;
                        status = StatusEffects.burning;
                        statusDuration = 60f * 4f;
                    }});
            drawer = new DrawTurret("gier-"){{
            }};
            researchCost = with(SpaceMayhemItems.iron, 800, Items.lead, 100, Items.silicon, 100);
        }};
    }
}
