package spacemayhem.content;

import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.maps.planet.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import mindustry.content.*;
import spacemayhem.world.meta.SMEnv;

import static mindustry.content.Planets.*;

public class SpaceMayhemPlanets {

    public static Planet
            clim, testAsteroid;
    public static void load() {
        //gier
        gier.accessible = gier.alwaysUnlocked = true;
        gier.defaultCore = SpaceMayhemBlocks.coreCampfire;
        gier.clearSectorOnLose = true;
        gier.defaultEnv = SMEnv.flame;
        gier.ruleSetter = r ->{
            r.loadout = ItemStack.list(Items.copper, 150, SpaceMayhemItems.iron, 40);
            r.waveTeam = Team.neoplastic;
            r.showSpawns = true;
            r.fog = false;
            r.onlyDepositCore = false;
        };
        verilus.accessible = verilus.alwaysUnlocked = true;
        verilus.defaultCore = SpaceMayhemBlocks.coreCampfire;
        verilus.defaultEnv = SMEnv.ice;
        verilus.ruleSetter = r ->{
            r.loadout = ItemStack.list(Items.copper, 130, SpaceMayhemItems.osmium, 60);
            r.waveTeam = Team.blue;
            r.showSpawns = true;
            r.fog = false;
            r.onlyDepositCore = false;
        };
        notva.accessible = notva.alwaysUnlocked = true;
        notva.defaultCore = SpaceMayhemBlocks.coreCampfire;
        notva.defaultEnv = SMEnv.toxic;
        notva.ruleSetter = r ->{
            r.loadout = ItemStack.list(Items.beryllium, 160, Items.graphite, 90);
            r.waveTeam = Team.neoplastic;
            r.showSpawns = true;
            r.fog = false;
            r.onlyDepositCore = false;
        };
        testAsteroid = makeAsteroid("test-asteroid", sun , Blocks.iceWall, Blocks.stoneWall, 0.47f, 14, 1.85f, gen -> {
            gen.carbonChance = 0.58f;
            gen.iceChance = 0.4f;
        });
    }
    private static Planet makeAsteroid(String name, Planet parent, Block base, Block tint, float tintThresh, int pieces, float scale, Cons<AsteroidGenerator> cgen){
        return new Planet(name, parent, 0.12f){{
            hasAtmosphere = false;
            updateLighting = false;
            sectors.add(new Sector(this, Ptile.empty));
            camRadius = 0.68f * scale;
            minZoom = 0.6f;
            drawOrbit = false;
            accessible = true;
            alwaysUnlocked = true;
            clipRadius = 2f;
            defaultEnv = Env.space;
            icon = "commandRally";
            generator = new AsteroidGenerator();
            cgen.get((AsteroidGenerator)generator);

            meshLoader = () -> {
                iconColor = tint.mapColor;
                Color tinted = tint.mapColor.cpy().a(1f - tint.mapColor.a);
                Seq<GenericMesh> meshes = new Seq<>();
                Color color = base.mapColor;
                Rand rand = new Rand(id + 2);

                meshes.add(new NoiseMesh(
                        this, 0, 2, radius, 2, 0.55f, 0.45f, 14f,
                        color, tinted, 3, 0.6f, 0.38f, tintThresh
                ));

                for(int j = 0; j < pieces; j++){
                    meshes.add(new MatMesh(
                            new NoiseMesh(this, j + 1, 1, 0.022f + rand.random(0.039f) * scale, 2, 0.6f, 0.38f, 20f,
                                    color, tinted, 3, 0.6f, 0.38f, tintThresh),
                            new Mat3D().setToTranslation(Tmp.v31.setToRandomDirection(rand).setLength(rand.random(0.44f, 1.4f) * scale)))
                    );
                }

                return new MultiMesh(meshes.toArray(GenericMesh.class));
            };
        }};
    }
}
