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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_r);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        setupModel();
        setupPlane();
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

}