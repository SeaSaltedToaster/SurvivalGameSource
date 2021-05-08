package testing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import seaSaltedEngine.Engine;
import seaSaltedEngine.render.display.WindowManager;

public class ScreenshotUtils {
	
	public static void screenshot() {
        GL11.glReadBuffer(GL11.GL_FRONT);
        int width = (int) WindowManager.getWindowSizeX(Engine.getWindowInstance().getWindowID());
        int height= (int) WindowManager.getWindowSizeY(Engine.getWindowInstance().getWindowID());
        int bpp = 4;
        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
        GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer );
         
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  
        
        File file = new File("resources/res/screenshots/Screenshot"+Math.random()+".png"); 
        String format = "png";
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
           
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                int i = (x + (width * y)) * bpp;
                int r = buffer.get(i) & 0xFF;
                int g = buffer.get(i + 1) & 0xFF;
                int b = buffer.get(i + 2) & 0xFF;
                image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
            }
        }
           
        try {
            ImageIO.write(image, format, file);
        } catch (IOException e) { e.printStackTrace(); }
	}

}
