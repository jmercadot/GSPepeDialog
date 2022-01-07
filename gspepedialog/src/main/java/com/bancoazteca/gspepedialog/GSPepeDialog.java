package com.bancoazteca.gspepedialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.airbnb.lottie.LottieAnimationView;

public class GSPepeDialog implements GSPepeDialogInterface {
    private static Utils utils;
    private AlertDialog dialog;

    private static final String DEFAULT_TEXT_POSITIVE_BUTTON = "Aceptar";
    private static final String DEFAULT_TEXT_NOT_SHOW_AGAIN_BUTTON = "No mostrar de nuevo";
    private static final String DEFAULT_TEXT_LOOK_SAMPLE_BUTTON = "Ver un ejemplo";

    public static final int PEPE_TIMIDO = 0;
    public static final int PEPE_OK = 1;
    public static final int PEPE_ALERTA = 2;
    public static final int PEPE_ERROR = 3;

    private String simpleName;

    private GSPepeDialog(AlertDialog.Builder builder, String simpleName, Activity activity) {
        dialog = builder.create();
        this.simpleName = simpleName;
        utils = new Utils(activity);
    }

    public static void clear(String simpleName, Activity activity) {
        utils = new Utils(activity);
        if (utils.getNoMostrarDeNuevo(simpleName)) {
            utils.clearNoMostrarDeNuevo(simpleName);
        }
    }

    public static void clearAll(Activity activity) {
        utils = new Utils(activity);
        utils.clearAll();
    }

    public void show() {
        if (!utils.getNoMostrarDeNuevo(simpleName)) {
            dialog.show();
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        /*DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);*/
            DisplayMetrics displayMetrics = dialog.getContext().getResources().getDisplayMetrics();
            // The absolute width of the available display size in pixels.
            int displayWidth = displayMetrics.widthPixels;
            // The absolute height of the available display size in pixels.
            int displayHeight = displayMetrics.heightPixels;

            // Initialize a new window manager layout parameters
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

            // Copy the alert dialog window attributes to new layout parameter instance
            layoutParams.copyFrom(dialog.getWindow().getAttributes());

            // Set the alert dialog window width and height
            // Set alert dialog width equal to screen width 90%
            // int dialogWindowWidth = (int) (displayWidth * 0.9f);
            // Set alert dialog height equal to screen height 90%
            // int dialogWindowHeight = (int) (displayHeight * 0.9f);

            // Set alert dialog width equal to screen width 80%
            int dialogWindowWidth = (int) (displayWidth * 0.80f);
            // Set alert dialog height equal to screen height 83%
            int dialogWindowHeight = (int) (displayHeight * 0.83f);

            // Set the width and height for the layout parameters
            // This will bet the width and height of alert dialog
            layoutParams.width = dialogWindowWidth;
            layoutParams.height = dialogWindowHeight;

            // Apply the newly created layout parameters to the alert dialog window
            dialog.getWindow().setAttributes(layoutParams);
        }
    }

    @Override
    public void dismiss() {
        dialog.dismiss();
    }


    public static class Builder {
        AlertDialog.Builder builder;
        View view;
        GSPepeDialog genericDialogBuilder;
        String simpleName;
        Activity activity;


        public Builder(Activity activity, String simpleName) {
            builder = new AlertDialog.Builder(activity, R.style.MyAlertDialogTheme);
            view = activity.getLayoutInflater().inflate(R.layout.view_pepe_dialog, null, false);
            builder.setView(view);

            setPositiveButton(DEFAULT_TEXT_POSITIVE_BUTTON)
                    .setNegativeButton("")
                    .setNotShowAgain(false)
                    .setAhoraNo(false)
                    .setImageResource(-1)
                    .setLottieResource(-1)
                    .setBodyText(null)
                    .setTitle(null);

            this.simpleName = simpleName;
            this.activity = activity;
        }


        public Builder setPositiveButton(String text, GSPepeDialogInterface.OnClickListener listener) {
            return setupPositiveButton(text, listener);
        }

        public Builder setPositiveButton(String text) {
            return setupPositiveButton(text, GSPepeDialogInterface::dismiss);
        }

        private Builder setupPositiveButton(String text, GSPepeDialogInterface.OnClickListener listener) {
            AppCompatButton btnPositive = view.findViewById(R.id.btnStart);
            setupButton(btnPositive, text, listener);
            return this;
        }

        /*
         * setup negative button
         *
         * */

        public Builder setNegativeButton(String text, GSPepeDialogInterface.OnClickListener listener) {
            return setupNegativeButton(text, listener);
        }

        public Builder setNegativeButton(String text) {
            return setupNegativeButton(text, GSPepeDialogInterface::dismiss);
        }

        private Builder setupNegativeButton(String text, GSPepeDialogInterface.OnClickListener listener) {
            AppCompatButton btnNegative = view.findViewById(R.id.btnStart2);
            setupNegativeButton(btnNegative, text, listener);
            return this;
        }

        private void setupNegativeButton(AppCompatButton button, String text, OnClickListener listener) {
            button.setText(text);

            if (text == null || text.isEmpty()) {
                button.setVisibility(View.GONE);
            } else {
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(view -> {
                    if (listener != null) {
                        listener.OnClick(genericDialogBuilder);
                    }
                });
            }
        }

        public Builder setImageResource(int src) {
            ImageView imageView = view.findViewById(R.id.ivShowView);
            if (src != -1) {
                imageView.setImageResource(src);
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
            return this;
        }

        public Builder setLottieResource(int raw) {
            LottieAnimationView lavLottie = view.findViewById(R.id.lav_lottie);
            if (raw != -1) {
                lavLottie.setAnimation(raw);
                lavLottie.setVisibility(View.VISIBLE);
                lavLottie.playAnimation();
            } else {
                lavLottie.setVisibility(View.GONE);
            }
            return this;
        }

        private void setupButton(AppCompatButton button, String text, OnClickListener listener) {
            button.setText(text);

            if (text == null || text.isEmpty()) {
                button.setVisibility(View.GONE);
            } else {
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(view -> {
                    if (listener != null) {

                        listener.OnClick(genericDialogBuilder);
                    }
                });
            }
        }


        public Builder setNotShowAgain(boolean show) {
            return setupNotShowAgain(show, GSPepeDialogInterface::dismiss);
        }

        public Builder setNotShowAgain(boolean show, OnClickListener listener) {
            return setupNotShowAgain(show,listener);
        }

        private Builder setupNotShowAgain(boolean show, OnClickListener listener) {
            TextView textView = view.findViewById(R.id.tvNotShowAgain);
            if (show) {
                textView.setVisibility(View.VISIBLE);
                textView.setOnClickListener(view -> {
                    if (listener != null) {
                        utils.setNoMostrarDeNuevo(simpleName);
                        listener.OnClick(genericDialogBuilder);
                    }
                });

            } else {
                textView.setVisibility(View.GONE);
            }
            return this;
        }

        public Builder setAhoraNo(boolean show) {
            return setupAhoraNo(show, GSPepeDialogInterface::dismiss);
        }

        public Builder setAhoraNo(boolean show, OnClickListener listener) {
            return setupAhoraNo(show, listener);
        }

        private Builder setupAhoraNo(boolean show, OnClickListener listener) {
            TextView textView = view.findViewById(R.id.tvMessageDismis);
            if (show) {
                textView.setVisibility(View.VISIBLE);
                textView.setOnClickListener(view -> {
                    if (listener != null) {
                        listener.OnClick(genericDialogBuilder);
                    }
                });

            } else {
                textView.setVisibility(View.GONE);
            }
            return this;
        }


        private void setupLookSample(TextView textView, String text, GSPepeDialogInterface.OnClickListener listener) {
            if (text == null || text.isEmpty()) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setText(text);
                textView.setVisibility(View.VISIBLE);
                textView.setOnClickListener(view -> {
                    if (listener != null) {
                        listener.OnClick(genericDialogBuilder);
                    }
                });
            }
        }

        public Builder setBodyText(String text) {
            return setupBody(text, Gravity.CENTER);
        }

        public Builder setBodyText(String text, int textAlignment) {
            return setupBody(text, textAlignment);
        }

        public Builder setBodySpanned(Spanned text) {
            return setupBody(text, Gravity.CENTER);
        }

        public Builder setBodySpanned(Spanned text, int textAlignment) {
            return setupBody(text,textAlignment);
        }

        private Builder setupBody(String text, int textAlignment) {
            TextView textView = view.findViewById(R.id.tvBody1);
            textView.setGravity(textAlignment);
            if (text == null || text.isEmpty()) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setText(text);
            }
            return this;
        }

        private Builder setupBody(Spanned text, int textAlignment) {
            TextView textView = view.findViewById(R.id.tvBody1);
            textView.setGravity(textAlignment);
            if (text == null) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setVisibility(View.VISIBLE);
                textView.setText(text);
            }
            return this;
        }

        public Builder setCancelable(boolean isCancelable) {
            this.builder.setCancelable(isCancelable);
            return this;
        }

        public GSPepeDialog create() {
            genericDialogBuilder = new GSPepeDialog(builder, simpleName, activity);
            return genericDialogBuilder;
        }

        /*
         * setup title
         *
         * */

        public Builder setTitle(String text, GSPepeDialogInterface.OnClickListener listener) {
            return setupTitle(text, listener);
        }

        public Builder setTitle(String text) {
            return setupTitle(text, null);
        }

        private Builder setupTitle(String text, GSPepeDialogInterface.OnClickListener listener) {
            TextView tvTitle = view.findViewById(R.id.tvTitle);
            setupTitle(tvTitle, text, listener);
            return this;
        }

        private void setupTitle(TextView textView, String text, GSPepeDialogInterface.OnClickListener listener) {
            if (text == null || text.isEmpty()) {
                textView.setVisibility(View.GONE);
            } else {
                textView.setText(text);

                textView.setVisibility(View.VISIBLE);
                textView.setOnClickListener(view -> {
                    if (listener != null) {
                        listener.OnClick(genericDialogBuilder);
                    }
                });

            }
        }

        public Builder setStatusPepe(int status) {
            setupStatus(status);
            return this;
        }

        private void setupStatus(int status) {
            ImageView imgPepePhoto = view.findViewById(R.id.imgPepePhoto);
            ImageView imgBase = view.findViewById(R.id.imgBase);

            imgPepePhoto.setVisibility(View.INVISIBLE);
            switch (status) {
                case PEPE_TIMIDO:
                    imgBase.setImageResource(R.drawable.ic_pepe_timido);
                    break;
                case PEPE_OK:
                    imgBase.setImageResource(R.drawable.ic_pepe_ok);
                    break;
                case PEPE_ALERTA:
                    imgBase.setImageResource(R.drawable.ic_pepe_alerta);
                    break;
                case PEPE_ERROR:
                    imgBase.setImageResource(R.drawable.ic_pepe_error);
                    break;
                default:
                    imgBase.setImageResource(R.drawable.ic_pepe_avatar_vect);
                    imgPepePhoto.setVisibility(View.VISIBLE);
                    break;
            }

        }

    }
}