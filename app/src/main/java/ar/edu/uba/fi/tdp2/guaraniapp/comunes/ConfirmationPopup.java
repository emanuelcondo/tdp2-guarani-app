package ar.edu.uba.fi.tdp2.guaraniapp.comunes;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import ar.edu.uba.fi.tdp2.guaraniapp.comunes.red.ResponseWatcher;

public class ConfirmationPopup {

    private String title;
    private String subtitle;
    private String text;
    private String buttonTextOK;
    private String buttonTextCancel;
    private ResponseWatcher watcher;
    private Context context;
    private AlertDialog dialog;

    public ConfirmationPopup(String text, String buttonTextOK, String buttonTextCancel, ResponseWatcher watcher, Context context) {
        this.text = text;
        this.buttonTextOK = buttonTextOK;
        this.buttonTextCancel = buttonTextCancel;
        this.watcher = watcher;
        this.context = context;
    }

    public ConfirmationPopup(String title, String subtitle, String text, String buttonTextOK, String buttonTextCancel, ResponseWatcher watcher, Context context) {
        this.title = title;
        this.subtitle = subtitle;
        this.text = text;
        this.buttonTextOK = buttonTextOK;
        this.buttonTextCancel = buttonTextCancel;
        this.watcher = watcher;
        this.context = context;
    }

    public void show() {

        int llPadding = 30;
        LinearLayout ll = new LinearLayout(this.context);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(llPadding, llPadding, llPadding, llPadding);
        ll.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        llParam.setMargins(0, 8, 0, 8);
        ll.setLayoutParams(llParam);

        TextView tvTitle = new TextView(this.context);
        tvTitle.setText(this.title);
        tvTitle.setTextColor(Color.parseColor("#FFFFFF"));
        tvTitle.setTextSize(16);
        tvTitle.setTypeface(null, Typeface.BOLD);
        tvTitle.setBackgroundColor(Color.parseColor("#3F51B5"));
        tvTitle.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        tvTitle.setPadding(8, 8, 8, 8);
        tvTitle.setLayoutParams(llParam);

        TextView tvSubtitle = new TextView(this.context);
        tvSubtitle.setText(this.subtitle);
        tvSubtitle.setTextColor(Color.parseColor("#696969"));
        tvSubtitle.setTextSize(14);
        tvSubtitle.setTypeface(null, Typeface.ITALIC);
        tvSubtitle.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        tvSubtitle.setPadding(6, 6, 6, 6);
        tvSubtitle.setLayoutParams(llParam);

        TextView tvText = new TextView(this.context);
        tvText.setText(this.text);
        tvText.setTextColor(Color.parseColor("#000000"));
        tvText.setTextSize(20);
        tvText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvText.setLayoutParams(llParam);

        AppCompatButton buttonOK = new AppCompatButton(context);
        buttonOK.setText(buttonTextOK);
        buttonOK.setTextSize(16);
        buttonOK.setBackgroundColor(Color.parseColor("#3F51B5"));
        buttonOK.setTextColor(Color.parseColor("#FFFFFF"));
        buttonOK.setLayoutParams(llParam);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                watcher.onSuccess();
            }
        });

        AppCompatButton buttonCancel = new AppCompatButton(context);
        buttonCancel.setText(buttonTextCancel);
        buttonCancel.setTextSize(16);
        buttonCancel.setBackgroundColor(Color.parseColor("#D3D3D3"));
        buttonCancel.setTextColor(Color.parseColor("#000000"));
        buttonCancel.setLayoutParams(llParam);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                watcher.onError();
            }
        });

        if (title != null && !title.isEmpty())
            ll.addView(tvTitle);
        if (subtitle != null && !subtitle.isEmpty())
            ll.addView(tvSubtitle);
        ll.addView(tvText);
        ll.addView(buttonOK);
        ll.addView(buttonCancel);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setCancelable(true);
        builder.setView(ll);

        this.dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);
        }
    }

    public void dismiss() {
        if (this.dialog != null) {
            if (this.dialog.getWindow() != null)
                this.dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            this.dialog.dismiss();
        }
    }
}
