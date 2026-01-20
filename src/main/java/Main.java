import CellularAutomata.CellularMatrix;
import CellularAutomata.Sand;
import Util.Color;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

    private long window;

    private CellularMatrix cellularMatrix;

    private double mouseX;
    private double mouseY;

    double lastX = mouseX;
    double lastY = mouseY;

    public final static int WIDTH = 800;
    public final static int HEIGHT = 800;

    static void main(String[] args) {
        new Main().run();
    }

    public void run() {

        init();
        loop();
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        // Terminate GLFW and free the error callback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    private void init() {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will not be resizable

        // Create the window
        window = glfwCreateWindow(WIDTH, HEIGHT, "SandSimulator", NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        glfwSetFramebufferSizeCallback(window, this::resize);

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });
        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);
            resize(window, pWidth.get(0), pHeight.get(0));
            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );

            glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
                this.mouseX = xpos;
                this.mouseY = ypos;
            });

        } // the stack frame is popped automatically

        cellularMatrix = new CellularMatrix();

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // Switch back to ModelView matrix to draw shapes
        glMatrixMode(GL_MODELVIEW);
    }

    private void resize(long window, int width, int height) {
        // 1. Viewport uses ACTUAL PIXELS (width/height passed by callback)
        glViewport(0, 0, width, height);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glOrtho(0.0, WIDTH, HEIGHT, 0.0, 1.0, -1.0);

        glMatrixMode(GL_MODELVIEW);
    }
    private void loop() {


        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer


            if (glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS) {
                double dx = mouseX - lastX;
                double dy = mouseY - lastY;
                double distance = Math.hypot(dx, dy); // Calculates the diagonal distance
                double steps = Math.max(1, distance / CellularMatrix.CELLSIZE);

                for (double i = 0; i <= steps; i++) {
                    double t = i / steps;

                    double currentX = lastX + (dx * t);
                    double currentY = lastY + (dy * t);
                    cellularMatrix.setCell(currentX, currentY);

                }
                lastX = mouseX;
                lastY = mouseY;
            } else {
                lastX = mouseX;
                lastY = mouseY;
            }

            cellularMatrix.draw();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

}