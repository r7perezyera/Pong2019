package mx.itesm.rtp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.text.View;

class PantallaJeuego implements Screen
{
    private final Pong pong;

    // Tamaño del mundo
    public static final float ANCHO = 1200;
    public static final float ALTO = 800;

    //Cámara
    private OrthographicCamera camara;
    private Viewport vista;

    // Administrador de trazos
    private SpriteBatch batch;

    // Texturas
    private Texture texturaBarraHorizontal;
    private Texture texturaRaqueta;
    private Texture texturaCuadrito;

    // Objetos
    private Pelota pelota;
    private Raqueta raquetaComputadora;
    private Raqueta raquetaJugador;

    public PantallaJeuego(Pong pong) {
        this.pong = pong;
    }

    // Este método se ejecuta al mostrarse la vista en la pantalla
    @Override
    public void show() {
        // Create camera
        camara = new OrthographicCamera(ANCHO, ALTO);
        camara.position.set(ANCHO/2,ALTO/2,0);
        camara.update();

        vista = new StretchViewport(ANCHO,ALTO,camara);

        // Creamos batch para no tener un null pointer exception
        batch = new SpriteBatch();

        // Cargar todas las texturas
        cargarTexturas();

        // Crear todos los objetos
        crearObjetos();

        // indicar quien escucha y atiende eventos
        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void crearObjetos() {
        pelota = new Pelota(texturaCuadrito, ANCHO/2, ALTO/2);  // centro de la pantalla
        raquetaComputadora = new Raqueta(texturaRaqueta, ANCHO - texturaRaqueta.getWidth(), ALTO / 2);
        raquetaJugador = new Raqueta(texturaRaqueta, 5, ALTO / 2);
    }

    private void cargarTexturas() {
        texturaBarraHorizontal = new Texture("barraHorizontal.png");
        texturaRaqueta = new Texture("raqueta.png");
        texturaCuadrito = new Texture("cuadrito.png");
    }

    // Este método dibuja en la pantalla, aprox. 60 veces por segundo
    @Override
    public void render(float delta) {
        // Actualizar los objetos en la pantalla
        actualizarObjetos();

        // Borrar pantalla
        Gdx.gl.glClearColor(0,0,0,1);   // negro con opacidad
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        // Aqui se dibujan todos los elementos del juego
        // Lineas horizontales cancha
        batch.draw(texturaBarraHorizontal,0,0);
        batch.draw(texturaBarraHorizontal,0,ALTO-texturaBarraHorizontal.getHeight());
        // Linea central
        for (float y = 0; y < ALTO; y+=2*texturaCuadrito.getHeight()) {
            batch.draw(texturaCuadrito,ANCHO/2,y);
        }
        // Raquetas

        // ya no necesitamos esta madre porque ahora los Objetos se dibujan por si mismos
        //batch.draw(texturaRaqueta,0,ALTO/2);
        //batch.draw(texturaRaqueta,ANCHO-texturaRaqueta.getWidth(),ALTO/2);
        raquetaComputadora.dibujar(batch);
        raquetaJugador.dibujar(batch);

        pelota.dibujar(batch);

        batch.end();
    }

    private void actualizarObjetos() {
        pelota.mover(raquetaJugador);
        raquetaComputadora.seguirPelota(pelota);
    }

    // se ejecuta cuando la ventana cambia de tamaño
    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    // clase para crear un objeto que escucha y atiende eventos de touch
    class ProcesadorEntrada implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            raquetaJugador.sprite.setY(v.y);
            return true;    // ya se proceso el evento
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
