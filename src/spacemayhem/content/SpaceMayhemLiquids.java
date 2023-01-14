package spacemayhem.content;

import arc.graphics.*;
import mindustry.type.*;

public class SpaceMayhemLiquids {
    public static Liquid
    whiteGas;
    public static void load() {

        whiteGas = new Liquid("white-gas", Color.valueOf("d1ffff")) {{
            gas = true;
        }};
    }
    }
