package spacemayhem.content;

import arc.func.*;
import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.Vars;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.graphics.g3d.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.maps.planet.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import mindustry.content.*;
import spacemayhem.graphics.*;
import spacemayhem.world.maps.generators.*;
import spacemayhem.world.meta.*;

import static mindustry.content.Items.serpuloItems;
import static mindustry.content.Planets.*;
import static spacemayhem.content.SpaceMayhemItems.*;

public class SpaceMayhemPlanets {

    public static Planet
            clim, eclet;
    public static void load() {
        clim = new Planet("clim", Planets.sun, 1f, 3){{
            defaultCore = SpaceMayhemBlocks.coreIsland;
            sectorSeed = 6;
            generator = new ClimPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this,  0, 0, 0, 5, SMPal.gas.a(0.75f), 2, 0.45f, 1.13f, 0.45f)
            );
            iconColor = SMPal.gas;
            accessible = true;
            alwaysUnlocked = true;
            atmosphereColor = Color.white;
            startSector = 48;
            atmosphereRadIn = 0.01f;
            atmosphereRadOut = 0.3f;
            clearSectorOnLose = true;
            defaultEnv = SMEnv.gas;
            ruleSetter = r -> {
                r.loadout = ItemStack.list(gasMatter, 180);
                r.staticFog = true;
                r.staticColor = SMPal.gas;
                r.defaultTeam = Team.sharded;
                r.waveTeam = Team.blue;
                r.attributes.clear();
                r.showSpawns = true;
                r.fog = false;
                r.onlyDepositCore = false;
                r.coreIncinerates = true;
            };
        }};
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
        //verilus
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
        //notva
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
        eclet = makeAsteroid("eclet", clim , Blocks.iceWall, Blocks.stoneWall, 0.47f, 14, 1.85f, gen -> {
            gen.carbonChance = 0.58f;
            gen.iceChance = 0.4f;
        });
        clim.hiddenItems.addAll(Vars.content.items()).removeAll(climItems);
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
