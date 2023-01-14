package spacemayhem.world.blocks.units;


import arc.Core;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.graphics.*;
import mindustry.net.Net;
import mindustry.type.*;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import java.io.*;
/*
public class OldUnitFactory extends UnitBlock{
    protected UnitType type;
    protected float produceTime = 1000f;
    protected float launchVelocity = 0f;
    protected TextureRegion topRegion;
    protected int maxSpawn = 4;
    protected int[] capacities;

    public OldUnitFactory(String name){
        super(name);
        update = true;
        hasPower = true;
        hasItems = true;
        solid = false;
    }

    @Remote(called = Loc.server)
    public static void onOldUnitFactorySpawn(Tile tile, int spawns){
        if(!(tile.entity instanceof OldUnitFactoryBuild) || !(tile.block() instanceof OldUnitFactory)) return;

        OldUnitFactoryBuild entity = tile.entity();
        OldUnitFactory factory = (OldUnitFactory)tile.block();

        entity.buildTime = 0f;
        entity.spawned = spawns;

        if(!Net.client()){
            BaseUnit unit = factory.type.create(tile.getTeam());
            unit.setSpawner(tile);
            unit.set(tile.drawx() + Mathf.range(4), tile.drawy() + Mathf.range(4));
            unit.add();
            unit.velocity().y = factory.launchVelocity;
        }
    }

    @Override
    public void init(){
        super.init();

        capacities = new int[Vars.content.items().size];
        if(consumes.has(ConsumeType.item)){
            ConsumeItems cons = consumes.get(ConsumeType.item);
            for(ItemStack stack : cons.items){
                capacities[stack.item.id] = stack.amount * 2;
            }
        }
    }

    @Override
    public void load(){
        super.load();

        topRegion = Core.atlas.find(name + "-top");
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("progress", entity -> new Bar("bar.progress", Pal.ammo, () -> ((OldUnitFactoryBuild)entity).buildTime / produceTime));
        addBar("spawned", entity -> new Bar(() -> Core.bundle.format("bar.spawned", ((OldUnitFactoryBuild)entity).spawned, maxSpawn), () -> Pal.command, () -> (float)((OldUnitFactoryBuild)entity).spawned / maxSpawn));
    }

    @Override
    public boolean outputsItems(){
        return false;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.itemCapacity);
        stats.add(Stat.productionTime, produceTime / 60f, StatUnit.seconds);
        stats.add(Stat.maxUnits, maxSpawn, StatUnit.none);
    }

    @Override
    public void unitRemoved(Tile tile, Unit unit){
        OldUnitFactoryBuild entity = tile.entity();
        entity.spawned--;
        entity.spawned = Math.max(entity.spawned, 0);
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{Core.atlas.find(name), Core.atlas.find(name + "-top")};
    }

    @Override
    public void draw(Tile tile){
        OldUnitFactoryBuild entity = tile.entity();
        TextureRegion region = type.iconRegion;

        Draw.rect(name, tile.drawx(), tile.drawy());

        Shaders.build.region = region;
        Shaders.build.progress = entity.buildTime / produceTime;
        Shaders.build.color.set(Pal.accent);
        Shaders.build.color.a = entity.speedScl;
        Shaders.build.time = -entity.time / 20f;

        Draw.shader(Shaders.build);
        Draw.rect(region, tile.drawx(), tile.drawy());
        Draw.shader();

        Draw.color(Pal.accent);
        Draw.alpha(entity.speedScl);

        Lines.lineAngleCenter(
                tile.drawx() + Mathf.sin(entity.time, 20f, Vars.tilesize / 2f * size - 2f),
                tile.drawy(),
                90,
                size * Vars.tilesize - 4f);

        Draw.reset();

        Draw.rect(topRegion, tile.drawx(), tile.drawy());
    }

    @Override
    public void update(Tile tile){
        OldUnitFactoryBuild entity = tile.entity();

        if(entity.spawned >= maxSpawn){
            return;
        }

        if(entity.cons.valid() || tile.isEnemyCheat()){
            entity.time += entity.delta() * entity.speedScl * Vars.state.rules.unitBuildSpeedMultiplier * entity.power.satisfaction;
            entity.buildTime += entity.delta() * entity.power.satisfaction * Vars.state.rules.unitBuildSpeedMultiplier;
            entity.speedScl = Mathf.lerpDelta(entity.speedScl, 1f, 0.05f);
        }else{
            entity.speedScl = Mathf.lerpDelta(entity.speedScl, 0f, 0.05f);
        }

        if(entity.buildTime >= produceTime){
            entity.buildTime = 0f;

            Call.onOldUnitFactorySpawn(tile, entity.spawned + 1);
            useContent(tile, type);

            entity.cons.trigger();
        }
    }
    @Override
    public int getMaximumAccepted(Tile tile, Item item){
        return capacities[item.id];
    }

    @Override
    public TileEntity newEntity(){
        return new OldUnitFactoryBuild();
    }

    @Override
    public boolean canProduce(Tile tile){
        OldUnitFactoryBuild entity = tile.entity();
        return entity.spawned < maxSpawn;
    }

    public class OldUnitFactoryBuild extends UnitBuild {
        float buildTime;
        float time;
        float speedScl;
        int spawned;

        @Override
        public void write(DataOutput stream) throws IOException{
            super.write(stream);
            stream.writeFloat(buildTime);
            stream.writeInt(spawned);
        }

        @Override
        public void read(DataInput stream, byte revision) throws IOException{
            super.read(stream, revision);
            buildTime = stream.readFloat();
            spawned = stream.readInt();
        }
    }
}

 */
