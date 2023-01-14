package spacemayhem.world.blocks.storage;

import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.*;
import spacemayhem.content.SpaceMayhemBlocks;
import spacemayhem.content.SpaceMayhemFx;

import static mindustry.Vars.world;

public class PlatformCore extends CoreBlock {

    private final float range;

    public PlatformCore(String name) {
            super(name);
            solid = true;
            update = true;
            range = 40f;
        }

        @Override
        public void drawPlace(int x, int y, int rotation, boolean valid) {
            super.drawPlace(x, y, rotation, valid);
            Drawf.dashSquare(Vars.player.team().color, x*8, y*8, range * 2 + 8);
        }

        public class PlatformCoreBuild extends CoreBuild {
            public float timer = 40f;

            @Override
            public void updateTile() {
                if (timer >= 40) {
                    platform(range);
                }

                timer -= Time.delta;
            }

            public void platform(float range) {
                for (float i = -range; i <= range; i+=8) {
                    for (float j = -range; j <= range; j += 8) {
                        Tile tile = world.tileWorld(x + i, y + j);

                        if (tile == null || tile.floor().hasBuilding()) continue;

                        if (tile.floor() != Blocks.air) SpaceMayhemFx.createfloor.at(x + i, y + j);
                        tile.setFloor(Blocks.metalFloor.asFloor());
                    }
                }
            }
        }
    }
