package mx.itesm.rtp;

/*
representa un objeto que forma parte del juego (i.e. pelota, raqueta, etc...)
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Objeto {
    // Sprite que representa gráficamente el objeto
    protected Sprite sprite;

    // Constructor para crear el objeto con cierta textura y posicion
    public Objeto(Texture texture, float x, float y) {
        sprite = new Sprite(texture);
        sprite.setPosition(x,y);
    }
}
