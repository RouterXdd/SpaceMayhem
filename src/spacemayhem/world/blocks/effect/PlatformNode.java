package spacemayhem.world.blocks.effect;

import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.world.Block;
import mindustry.world.Tile;
import spacemayhem.SpaceMayhem;
import spacemayhem.content.SpaceMayhemBlocks;
import spacemayhem.content.SpaceMayhemFx;

import static mindustry.Vars.world;
//original code made by Slotterleet
public class PlatformNode extends Block {
    public float range = 8f;
    public PlatformNode(String name) {
        super(name);
        solid = true;
        update = true;
        buildType = PlatformNodeBuild::new;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashSquare(Vars.player.team().color, x*8, y*8, range * 2 + 8);
    }

    public class PlatformNodeBuild extends Building {
        public float timer = 40f;

        @Override
        public void updateTile() {
            if (timer >= 40) {
                build(range);
            }

            timer -= Time.delta;

            //if (health <= 1) {
            //    back(range);
            //}
            //if (instantDeconstruct) {
            //    back(range);
            //}
        }

        public void build(float range) {
            for (float i = -range; i <= range; i+=8) {
                for (float j = -range; j <= range; j += 8) {
                    Tile tile = world.tileWorld(x + i, y + j);

                    if (tile == null || tile.floor().hasBuilding()) continue;

                    if (tile.floor() != Blocks.air) SpaceMayhemFx.createfloor.at(x + i, y + j);
                    tile.setFloor(Blocks.metalFloor.asFloor());
                }
            }

            this.damage(Float.MAX_VALUE);
        }
        public void back(float range) {
            for (float i = -range; i <= range; i += 8) {
                for (float j = -range; j <= range; j += 8) {
                    Tile tile = world.tileWorld(x + i, y + j);

                    if (tile == null || tile.floor().hasBuilding()) continue;

                    if (tile.floor() != Blocks.air) SpaceMayhemFx.createfloor.at(x + i, y + j);
                    tile.setFloor(SpaceMayhemBlocks.gasFloor.asFloor());
                }
            }
        }
    }
}