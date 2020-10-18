package com.smithbois.grocerygrab.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.Pose;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.smithbois.grocerygrab.R;

import java.util.concurrent.CompletableFuture;

public class ARActivity extends AppCompatActivity {

    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    private boolean placed;
    private Renderable arrowRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_r);

        placed = false;
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        setupModel();
        setupPlane();
        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);
        ModelRenderable.builder()
                .setSource(this, R.raw.materials)
                .build()
                .thenAccept(renderable -> arrowRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            System.out.println("Unable to load Renderable.");
                            return null;
                        });
    }

    private void setupModel() {
        ModelRenderable.builder()
                .setSource(this, R.raw.materials)
                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(ARActivity.this, "Model can't be loaded", Toast.LENGTH_LONG).show();
                    return null;
                });
    }

    private void setupPlane() {
        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(arFragment.getArSceneView().getScene());
            createModel(anchorNode);
        });
    }

    private void createModel(AnchorNode anchorNode) {
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setLocalRotation(Quaternion.axisAngle(new Vector3(1f, 0, 0), 90f));
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();
    }
    public void onUpdateFrame(FrameTime fr) {
        Frame frame = arFragment.getArSceneView().getArFrame();
        if(frame == null){
            return;
        }
        if(frame.getCamera().getTrackingState()== TrackingState.TRACKING && !placed){
            Pose pos = frame.getCamera().getPose().compose(Pose.makeTranslation(0, 0, -0.3f));
            Anchor anchor = arFragment.getArSceneView().getSession().createAnchor(pos);
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(arFragment.getArSceneView().getScene());

            // Create the arrow node and add it to the anchor.
            Node arrow = new Node();
            arrow.setParent(anchorNode);
            arrow.setRenderable(arrowRenderable);
            placed = true;
        }
    }

}