package spacemayhem.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.units.UnitAssembler.*;
import spacemayhem.graphics.SMPal;

import static arc.graphics.g2d.Draw.rect;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;



public class SpaceMayhemFx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect

            createfloor = new Effect(10f, 30f, e -> {
        color(Color.white);
        stroke(e.fout() * 2f);
        Lines.circle(e.x, e.y, 1f + e.finpow() * 10f);
    }),

            eruptioninstBomb = new Effect(15f, 100f, e -> {
        color(SMPal.eruption);
        stroke(e.fout() * 4f);
        Lines.circle(e.x, e.y, 4f + e.finpow() * 20f);

        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 6f, 80f * e.fout(), i*90 + 45);
        }

        color();
        for(int i = 0; i < 4; i++){
            Drawf.tri(e.x, e.y, 3f, 30f * e.fout(), i*90 + 45);
        }

        Drawf.light(e.x, e.y, 150f, SMPal.eruption, 0.9f * e.fout());
    }),
            eruptioninstTrail = new Effect(30, e -> {
                for(int i = 0; i < 2; i++){
                    color(i == 0 ? SMPal.eruption : SMPal.francium);

                    float m = i == 0 ? 1f : 0.5f;

                    float rot = e.rotation + 180f;
                    float w = 15f * e.fout() * m;
                    Drawf.tri(e.x, e.y, w, (30f + Mathf.randomSeedRange(e.id, 15f)) * m, rot);
                    Drawf.tri(e.x, e.y, w, 10f * m, rot + 180f);
                }

                Drawf.light(e.x, e.y, 60f, SMPal.eruption, 0.6f * e.fout());
            }),
            eruptioninstHit = new Effect(20f, 200f, e -> {
                color(SMPal.eruption);

                for(int i = 0; i < 2; i++){
                    color(i == 0 ? SMPal.eruption : SMPal.francium);

                    float m = i == 0 ? 1f : 0.5f;

                    for(int j = 0; j < 5; j++){
                        float rot = e.rotation + Mathf.randomSeedRange(e.id + j, 50f);
                        float w = 23f * e.fout() * m;
                        Drawf.tri(e.x, e.y, w, (80f + Mathf.randomSeedRange(e.id + j, 40f)) * m, rot);
                        Drawf.tri(e.x, e.y, w, 20f * m, rot + 180f);
                    }
                }

                e.scaled(10f, c -> {
                    color(SMPal.eruption);
                    stroke(c.fout() * 2f + 0.2f);
                    Lines.circle(e.x, e.y, c.fin() * 30f);
                });

                e.scaled(12f, c -> {
                    color(SMPal.eruption);
                    randLenVectors(e.id, 25, 5f + e.fin() * 80f, e.rotation, 60f, (x, y) -> {
                        Fill.square(e.x + x, e.y + y, c.fout() * 3f, 45f);
                    });
                });
            }),

    eruptioninstShoot = new Effect(24f, e -> {
        e.scaled(10f, b -> {
            color(Color.white, SMPal.eruption, b.fin());
            stroke(b.fout() * 3f + 0.2f);
            Lines.circle(b.x, b.y, b.fin() * 50f);
        });

        color(SMPal.eruption);

        for(int i : Mathf.signs){
            Drawf.tri(e.x, e.y, 13f * e.fout(), 85f, e.rotation + 90f * i);
            Drawf.tri(e.x, e.y, 13f * e.fout(), 50f, e.rotation + 20f * i);
        }

        Drawf.light(e.x, e.y, 180f, SMPal.eruption, 0.9f * e.fout());
    });
}
