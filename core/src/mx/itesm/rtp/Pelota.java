package mx.itesm.rtp;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pelota extends Objeto {
    // Desplazamiento en cada frame
    private float DX = 4;
    private float DY = 4;

    public Pelota(Texture texture, float x, float y) {
        super(texture, x, y);
    }

    // Aqui se dibujará pelota
    // Es nuestro metodo render
    public void dibujar(SpriteBatch batch) {
        sprite.draw(batch);
    }

    // Actualiza la posicion de la pelota
    public void mover(Raqueta raqueta) {
        float xPos = sprite.getX();
        float yPos = sprite.getY();

        // probar limites izquierda, derecha
        if (xPos >= PantallaJeuego.ANCHO - sprite.getWidth()) {
            DX = -DX;
        }

        // Prueba colision con la raqueta del jugador
        float xRaqueta = raqueta.sprite.getX();
        float yRaqueta = raqueta.sprite.getY();

        if (xPos >= xRaqueta && xPos <= xRaqueta + raqueta.sprite.getWidth() &&
                yPos >= yRaqueta && yPos <= (yRaqueta + raqueta.sprite.getHeight()-sprite.getHeight())) {
            DX = -DX;

        }

        // probar limites arriba abajo
        if (yPos <= 0 || yPos >= PantallaJeuego.ALTO - sprite.getHeight()) {
            DY = -DY;
        }

        // mueve la pelota a la nueva posicion
        sprite.setPosition(xPos + DX, yPos + DY);
    }
}
