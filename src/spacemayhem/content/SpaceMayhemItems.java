package spacemayhem.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;

public class SpaceMayhemItems{
    public static Item
    iron, francium, uranium, osmium;

    public static void load(){
        //gier
        iron = new Item("iron", Color.valueOf("808080")){{
            hardness = 2;
            cost = 2.1f;
        }};
        francium = new Item("francium", Color.valueOf("d44a26")){{
            hardness = 4;
            cost = 2.9f;
        }};
        //notva
        uranium = new Item("uranium", Color.valueOf("25770a")){{
            hardness = 4;
            cost = 3.2f;
        }};
        //verlius
        osmium = new Item("osmium", Color.valueOf("7c9f9f")){{
            hardness = 3;
            cost = 2.6f;
        }};
    }

}
