package spacemayhem.graphics;

import arc.*;
import arc.files.*;
import arc.graphics.*;
import arc.graphics.Texture.*;
import arc.graphics.g2d.*;
import arc.graphics.g3d.*;
import arc.graphics.gl.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.game.EventType.*;
import mindustry.graphics.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static mindustry.Vars.*;

public class SMShaders {
    public static GasShader gas;
    public static SMCacheLayer gasLayer;

    public static void init(){
            gas = new GasShader("gas");
    }

    public static class GasShader extends SMGroundShader {
        Texture texture;

        public GasShader(String frag){
            super(frag);

            Core.assets.load("sprites/gasholder.png", Texture.class).loaded = t -> {
                texture = t;
                texture.setFilter(TextureFilter.linear);
                texture.setWrap(TextureWrap.mirroredRepeat);
            };
        }


        @Override
        public void apply(){
            setUniformf("u_campos", Core.camera.position.x, Core.camera.position.y);
            setUniformf("u_ccampos", Core.camera.position);
            setUniformf("u_resolution", Core.graphics.getWidth(), Core.graphics.getHeight());
            setUniformf("u_time", Time.time);

            texture.bind(1);
            renderer.effectBuffer.getTexture().bind(0);
        }
    }
    public static class SMGroundShader extends SMShader {
        Texture noiseTex;

        public SMGroundShader(String frag) {
            super(frag, "screenspace");
            loadNoise();
        }

        public String texture() {
            return "noise";
        }

        public void loadNoise() {
            Core.assets.load("sprites/" + texture() + ".png", Texture.class).loaded = t -> {
                t.setFilter(TextureFilter.linear);
                t.setWrap(TextureWrap.repeat);
            };
        }

        @Override
        public void apply() {
            setUniformf("u_campos", Core.camera.position.x - Core.camera.width / 2, Core.camera.position.y - Core.camera.height / 2);
            setUniformf("u_resolution", Core.camera.width, Core.camera.height);
            setUniformf("u_time", Time.time);

            if (hasUniform("u_noise")) {
                if (noiseTex == null) {
                    noiseTex = Core.assets.get("sprites/" + texture() + ".png", Texture.class);
                }

                noiseTex.bind(1);
                renderer.effectBuffer.getTexture().bind(0);

                setUniformi("u_noise", 1);
            }
        }
    }

    public static class SMShader extends Shader {
        public SMShader(String frag, String vert) {
            super(Vars.tree.get("shaders/" + vert + ".vert"), Vars.tree.get("shaders/" + frag + ".frag"));
        }
    }
}

