import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import java.awt.*;

public class AppearanceUtils {
    public static Appearance getAppearance(Color color, String texturePath) {
        Appearance ap = new Appearance();

        Color3f emissive = new Color3f(Color.BLACK);
        Color3f ambient = new Color3f(color);
        Color3f diffuse = new Color3f(color);
        Color3f specular = new Color3f(color);

        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 64.0f));

        if (texturePath != null && !texturePath.isEmpty()) {
            TextureLoader loader = new TextureLoader(texturePath, "LUMINANCE", new Container());
            Texture texture = loader.getTexture();

            texture.setBoundaryModeS(Texture.WRAP);
            texture.setBoundaryModeT(Texture.WRAP);
            texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));
            TextureAttributes texAttr = new TextureAttributes();
            texAttr.setTextureMode(TextureAttributes.MODULATE);
            ap.setTexture(texture);
            ap.setTextureAttributes(texAttr);
        }

        return ap;
    }
}
