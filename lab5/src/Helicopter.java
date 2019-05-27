import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Helicopter extends JFrame {

    private Canvas3D canvas;
    private SimpleUniverse universe;
    private BranchGroup root;

    private TransformGroup air = new TransformGroup();
    private TransformGroup helicopter;

    private Map<String, Shape3D> shapeMap;

    public Helicopter() throws IOException {

        configureWindow();
        configureCanvas();
        configureUniverse();

        root = new BranchGroup();
        root.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);


        addImageBackground("resource/image/war.jpg");
        addLightToUniverse();

        ChangeViewAngle();

        helicopter = getHelicopterGroup();
        air.addChild(helicopter);

        root.addChild(air);

        addAppearance();

        HelicopterAnimation helicopterAnimation = new HelicopterAnimation(this);
        canvas.addKeyListener(helicopterAnimation);

        root.compile();
        universe.addBranchGraph(root);
    }

    private void configureWindow() {
        setTitle("Helicopter Animation");
        setSize(1920, 1050);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configureCanvas() {
        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        canvas.setFocusable(true);
        add(canvas, BorderLayout.CENTER);
    }

    private void configureUniverse() {
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
    }

    private void addImageBackground(String imagePath) {
        TextureLoader t = new TextureLoader(imagePath, canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }

    private void addLightToUniverse() {
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(1000);

        DirectionalLight directionalLight = new DirectionalLight(
                new Color3f(new Color(255, 255, 255)),
                new Vector3f(0, -0.5f, -0.5f));
        directionalLight.setInfluencingBounds(bounds);

        AmbientLight ambientLight = new AmbientLight(
                new Color3f(new Color(255, 255, 245)));
        ambientLight.setInfluencingBounds(bounds);

        root.addChild(directionalLight);
        root.addChild(ambientLight);
    }

    private TransformGroup getHelicopterGroup() throws IOException {
        Transform3D transform3D = new Transform3D();

        transform3D.setTranslation(new Vector3d(1, 0, 0));

        TransformGroup group = getModelGroup("resource/uh60.obj");
        group.setTransform(transform3D);

        return group;
    }

    private TransformGroup getModelGroup(String path) throws IOException {
        Scene scene = getSceneFromFile(path);
        shapeMap = scene.getNamedObjects();

        printModelElementsList(shapeMap);

        TransformGroup group = new TransformGroup();

        for (String shapeName : shapeMap.keySet()) {
            Shape3D shape = shapeMap.get(shapeName);

            scene.getSceneGroup().removeChild(shape);
            group.addChild(shape);
        }

        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        return group;
    }


    private void printModelElementsList(Map<String, Shape3D> shapeMap) {
        for (String name : shapeMap.keySet()) {
            System.out.printf("Name: %s\n", name);
        }
    }


    private void addAppearance() {

        Appearance windows = new Appearance();
        windows.setMaterial(getMaterial(
                Color.BLACK,
                new Color(0x01234a)));
        shapeMap.get("window_l1").setAppearance(windows);
        shapeMap.get("window_l2").setAppearance(windows);
        shapeMap.get("window_r1").setAppearance(windows);
        shapeMap.get("window_r2").setAppearance(windows);

        Appearance rotors = new Appearance();
        rotors.setMaterial(getMaterial(
                Color.BLACK,
                Color.BLACK));
        shapeMap.get("main_rotor").setAppearance(rotors);
        shapeMap.get("rear_rotor").setAppearance(rotors);

        Appearance tyres = new Appearance();
        tyres.setMaterial(getMaterial(
                Color.BLACK,
                Color.BLACK));
        shapeMap.get("tyre_r").setAppearance(tyres);
        shapeMap.get("tyre_l").setAppearance(tyres);
        shapeMap.get("rear_tyre").setAppearance(tyres);

        Appearance doors = new Appearance();
        doors.setMaterial(getMaterial(
                Color.BLACK,
                Color.DARK_GRAY));
        shapeMap.get("door_l").setAppearance(doors);
        shapeMap.get("cabindoor_l").setAppearance(doors);
        shapeMap.get("door_r").setAppearance(doors);
        shapeMap.get("cabindoor_r").setAppearance(doors);

        Appearance body = new Appearance();
        body.setMaterial(getMaterial(
                Color.BLACK,
                Color.DARK_GRAY));
        shapeMap.get("fuselage").setAppearance(body);
        shapeMap.get("elevator").setAppearance(body);
    }

    Material getMaterial(
            Color emissiveColor,
            Color defaultColor) {
        Material material = new Material();
        material.setEmissiveColor(new Color3f(emissiveColor));
        material.setAmbientColor(new Color3f(defaultColor));
        material.setDiffuseColor(new Color3f(defaultColor));
        material.setSpecularColor(new Color3f(defaultColor));
        material.setShininess(64);
        material.setLightingEnable(true);
        return material;
    }

    private void ChangeViewAngle() {
        ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D vpTranslation = new Transform3D();
        vpTranslation.setTranslation(new Vector3f(0, 0, 6));
        vpGroup.setTransform(vpTranslation);

    }

    public static Scene getSceneFromFile(String location) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        return file.load(new FileReader(location));
    }

    public TransformGroup getHelicopterTransformGroup() {
        return helicopter;
    }

    public static void main(String[] args) {
        try {
            Helicopter window = new Helicopter();
            window.setVisible(true);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
