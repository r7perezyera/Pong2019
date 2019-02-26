package mx.itesm.rtp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Raqueta extends Objeto {
    private float DY = 4;

    public Raqueta(Texture texture, float x, float y) {
        super(texture,x,y);
    }

    public void dibujar(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void seguirPelota(Pelota pelota) {
        sprite.setY(pelota.sprite.getY()-sprite.getHeight()/2 + pelota.sprite.getHeight()/2);
    }

    /*
    public void mover() {
        // primero neceisto conocer la pos de la raqueta
        float yPos = sprite.getY();

        // solo se mueve vertical
        // probar limites arriba abajo
        if (yPos <= 0 || yPos >= PantallaJeuego.ALTO - sprite.getHeight()) {
            DY = -DY
        }

        // mover la pelota a la nueva posicion
        sprite.setPosition(yPos + DY);

    }
    */

}
