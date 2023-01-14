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
import spacemayhem.graphics.*;
import spacemayhem.world.blocks.effect.*;
import spacemayhem.world.blocks.storage.*;
import spacemayhem.world.meta.SMEnv;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;
import static mindustry.content.Blocks.*;



public class SpaceMayhemBlocks {
    public static Block
    //environment
    oreIron, oreFrancium, oreUranium, oreOsmium, gasFloor,
    //production
    ironDrell, gasCollector,
    //turrets
    flame, volcano, eruption, blow,
    //storage
    coreIsland, coreCampfire,
    //effect
    platformNode, platformNodeLarge;
    public static void load() {
        //vanilla
        pneumaticDrill.envDisabled = laserDrill.envDisabled = blastDrill.envDisabled = Env.space;
        oreIron = new OreBlock(SpaceMayhemItems.iron) {{
        }};
        oreFrancium = new OreBlock(SpaceMayhemItems.francium) {{
        }};
        oreUranium = new OreBlock(SpaceMayhemItems.uranium) {{
        }};
        oreOsmium = new OreBlock(SpaceMayhemItems.osmium) {{
        }};
        gasFloor = new Floor("gas-floor") {{
            placeableOn = false;
            solid = true;
            variants = 0;
            canShadow = false;
            cacheLayer = SMCacheLayer.gas;
        }};
        ironDrell = new Drill("iron-drell"){{
            requirements(Category.production, with(Items.copper, 24, SpaceMayhemItems.iron, 8));
            tier = 3;
            drillTime = 400;
            size = 2;

            consumeLiquid(Liquids.water, 0.06f).boost();
        }};
        gasCollector = new GenericCrafter("gas-collector"){{
            requirements(Category.crafting, with(Items.silicon, 20));
            outputLiquid = new LiquidStack(SpaceMayhemLiquids.whiteGas, 8f / 60f);
            size = 2;
            hasLiquids = true;
            rotate = false;
            solid = true;
            outputsLiquid = true;
            envEnabled = SMEnv.gas;
            drawer = new DrawMulti(new DrawRegion("-bottom"),new DrawLiquidTile(SpaceMayhemLiquids.whiteGas){{drawLiquidLight = true;}},new DrawDefault(),new DrawRegion("-rotator"){{rotateSpeed = 10; spinSprite = true;}});
            liquidCapacity = 30f;
            craftTime = 140;
            lightLiquid = SpaceMayhemLiquids.whiteGas;

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
            shoot = new ShootSpread(18, 1.5f);
            coolant = consumeCoolant(0.4f);
            ammo(
                    Items.coal, new BasicBulletType(){{
                        height = 5f;
                        width = 10f;
                        speed = 3f;
                        lifetime = 59f;
                        ammoMultiplier = 2f;
                        damage = 3f;
                        status = StatusEffects.burning;
                        statusDuration = 60f * 4f;
                    }});
            drawer = new DrawTurret("gier-"){{
            }};
            researchCost = with(SpaceMayhemItems.iron, 400, Items.lead, 300, Items.silicon, 300, Items.coal, 150);
        }};
        volcano = new ItemTurret("volcano"){{
            requirements(Category.turret, with(
                    Items.lead, 130,
                    Items.graphite,75,
                    SpaceMayhemItems.iron, 90,
                    Items.silicon, 70
            ));
            scaledHealth = 160;
            size = 3;
            reload = 70f;
            range = 140f;
            recoil = 1f;
            inaccuracy = 0.6f;
            shoot = new ShootSpread(8, 5f);
            coolant = consumeCoolant(0.6f);
            ammoPerShot = 2;
            consumeAmmoOnce = true;
            ammo(
                    Items.coal, new LiquidBulletType(Liquids.slag){{
                        speed = 4f;
                        lifetime = 35f;
                        ammoMultiplier = 1f;
                        damage = 10f;
                    }},
                    Items.pyratite, new LiquidBulletType(Liquids.slag){{
                        speed = 5f;
                        lifetime = 28f;
                        ammoMultiplier = 1f;
                        reloadMultiplier = 1.6f;
                        damage = 18f;
            }});
            drawer = new DrawTurret("gier-"){{
            }};
            researchCost = with(Items.lead, 650, Items.graphite,375, SpaceMayhemItems.iron, 450, Items.silicon, 350);
        }};
        eruption = new ItemTurret("eruption"){{
            requirements(Category.turret, with(
                    Items.titanium, 80,
                    Items.lead,140,
                    SpaceMayhemItems.iron, 100,
                    Items.silicon, 120
            ));
            scaledHealth = 160;
            size = 3;
            reload = 140f;
            range = 320f;
            recoil = 1f;
            inaccuracy = 0.6f;
            coolant = consumeCoolant(0.6f);
            ammoPerShot = 3;
            consumeAmmoOnce = true;
            ammo(
                    Items.coal, new PointBulletType(){{
                        hitEffect = SpaceMayhemFx.eruptioninstHit;
                        smokeEffect = Fx.smokeCloud;
                        trailEffect = SpaceMayhemFx.eruptioninstTrail;
                        despawnEffect = SpaceMayhemFx.eruptioninstBomb;
                        trailSpacing = 20f;
                        damage = 420;
                        buildingDamageMultiplier = 0.35f;
                        speed = 320;
                        hitShake = 3f;
                        ammoMultiplier = 1f;
                        fragBullet = new LiquidBulletType(Liquids.slag){{
                            speed = 2.2f;
                            lifetime = 50f;
                            ammoMultiplier = 1f;
                            damage = 12f;
                        }};
                        fragBullets = 12;
                    }});
            drawer = new DrawTurret("gier-"){{
            }};
        }};
        blow = new ContinuousLiquidTurret("blow"){{
            requirements(Category.turret, with(Items.silicon, 30));
            health = 300;
            size = 2;
            liquidConsumed = 3f / 60f;

            float r = range = 80f;

            loopSound = Sounds.torch;
            shootSound = Sounds.none;
            loopSoundVolume = 1f;

            ammo(
                    SpaceMayhemLiquids.whiteGas, new ContinuousFlameBulletType(){{
                        damage = 2f;
                        length = r;
                        knockback = 10f;
                        pierce = false;

                        colors = new Color[]{SMPal.gas.a(0.55f), Color.white.a(0.7f), Color.white.a(0.8f), Color.white, Color.white};
                        flareColor = hitColor = SMPal.gas;
                    }});
            researchCost = with(Items.silicon, 150);
        }};
        coreIsland = new PlatformCore("core-island"){{
            requirements(Category.effect, with(Items.silicon, 2000));
            alwaysUnlocked = true;
            health = 1000;
            isFirstTier = true;
            itemCapacity = 3000;
            size = 3;
        }};
        coreCampfire = new CoreBlock("core-campfire"){{
            requirements(Category.effect, with(Items.silicon, 1600, Items.lead, 2200, SpaceMayhemItems.iron, 800));
            alwaysUnlocked = true;
            health = 3400;
            isFirstTier = true;
            itemCapacity = 3000;
            size = 3;
        }};
        platformNode = new PlatformNode("platform-node"){{
            requirements(Category.effect, with(Items.silicon, 4));
            health = 100;
            size = 1;
            envEnabled |= Env.space;
        }};
        platformNodeLarge = new PlatformNode("platform-node-mk2"){{
            requirements(Category.effect, with(Items.silicon, 20));
            health = 160;
            size = 1;
            range = 16;
            envEnabled |= Env.space;
        }};
    }
}
