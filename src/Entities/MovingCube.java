package Entities;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import resources.Constants;


import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MovingCube extends GLCanvas implements GLEventListener {

    private float xrot, yrot, zrot, rotationSpeed; // variables for the rotations

    protected int texture;
    public int xPos;
    public int yPos;
    public int width;
    public int height;
    protected int dx;
    protected int dy;
    public boolean isAlive;

    public MovingCube(int xPos, int yPos) {

        this.addGLEventListener(this);
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = Constants.CUBE_WIDTH;
        this.height = Constants.CUBE_HEIGHT;
        this.dy = 0;
        this.dx = 0;

        this.rotationSpeed = 200.0f;
        this.isAlive = true;

        Random rand = new Random();
        this.xrot = rand.nextFloat()*360;
        this.yrot = rand.nextFloat()*360;
        this.zrot = rand.nextFloat()*360;
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        // vider les valeurs du z-buffer
        gl.glClearDepth(1.0f);
        // activer le test de profondeur
        gl.glEnable(GL2.GL_DEPTH_TEST);
        // choisir le type de test de profondeur
        gl.glDepthFunc(GL2.GL_LEQUAL);
        // choix de la meilleur correction de perspective
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        float aspect = (float) getWidth() / (float) getHeight();
        new GLU().gluPerspective(45.0f, aspect, 1.0, 100.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        try {
            // 1. lire une image et l'assigner à une texture

            File im = new File("src/images/alien.png");
            Texture t = TextureIO.newTexture(im, true);
            texture = t.getTextureObject(gl);

            // 2. spécifier les paramètres de texture mapping
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
            gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
            gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);

            gl.glEnable(GL2.GL_TEXTURE_2D);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        // Initialisation des états
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);     // Clear The Screen And The Depth Buffer
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glLoadIdentity();

        gl.glTranslatef(0.0F, 0.0F, -4.0F);
        gl.glRotatef(xrot, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(yrot, 1.0f, 1.0f, 0.0f);
        gl.glRotatef(zrot, 1.0f, 0.0f, 1.0f);

        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);

        gl.glBegin(GL2.GL_QUADS);
        // Front Face
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
        // Back Face
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
        // Top Face
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
        // Bottom Face
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
        // Right face
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
        // Left Face
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
        gl.glEnd();

        gl.glFlush();
        xrot += .3f + rotationSpeed;
        yrot += .2f + rotationSpeed;
        zrot += .4f + rotationSpeed;
    }


    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int height) {
        GL2 gl = glAutoDrawable.getGL().getGL2();
        GLU glu = GLU.createGLU(gl);

        float aspect = (float)width / height;
        // Set the view port (display area)
        gl.glViewport(0, 0, width, height);
        // Setup perspective projection,
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, aspect, 0.1, 100.0);
        // Enable the model-view transform
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity(); // reset

    }
}