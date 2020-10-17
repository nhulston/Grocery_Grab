package com.smithbois.grocerygrab.fragments.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.smithbois.grocerygrab.R;

public class SignUpTabFragment extends Fragment {

    protected Button signUpButton;

    protected ConstraintLayout signUpFormLayout;
    protected ConstraintLayout signUpSuccessLayout;

    protected ImageView signUpSuccessImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment_layout, container, false);

        final Context context = getContext();

        signUpFormLayout = root.findViewById(R.id.signup_form_layout);
        signUpSuccessLayout = root.findViewById(R.id.signup_success_layout);

        //((ViewGroup) root.findViewById(R.id.signup_layout_parent)).getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        //((ViewGroup) root.findViewById(R.id.signup_layout_parent)).getLayoutTransition().setDuration(750);

        signUpSuccessImage = root.findViewById(R.id.signup_success_image);
        signUpSuccessImage.setAnimation(AnimationUtils.loadAnimation(context, R.anim.expand_in));

        signUpButton = root.findViewById(R.id.signup_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpFormLayout.setVisibility(View.GONE);
                signUpSuccessLayout.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }
}
