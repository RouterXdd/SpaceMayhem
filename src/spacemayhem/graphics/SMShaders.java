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
import mindustry.game.EventType.*;
import mindustry.graphics.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static mindustry.Vars.*;

public class SMShaders {
    public static @Nullable GasShader gas;
    public static CacheLayer.ShaderLayer gasLayer;

    public static void init(){
            gas = new GasShader("gas");
    }

    public static class GasShader extends Shaders.SurfaceShader {
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

            setUniformi("u_stars", 1);
        }
    }
}

