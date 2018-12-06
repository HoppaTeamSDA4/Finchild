package com.finchild.hoppateam.sda4.finchild;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.finchild.hoppateam.sda4.finchild.adapter.ProfileSettingAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    private final String[] profile_menu = {"Change Password", "Manage parent account",};
    private TextView item_view;
    private ImageView btnBack;
    private ImageView btnSettings;
    private Button pwReset;
    private PopupWindow popupWindow;
    private String pw;
    private String confirmPw;
    private View popupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ListView lv = (ListView) findViewById(R.id.childAccList);
        btnBack = (ImageView) findViewById(R.id.ivBack);
        // to set the back button instead of the logout
        btnBack.setImageResource(R.drawable.back_button);
        btnBack.setOnClickListener(this);
        btnSettings = (ImageView) findViewById(R.id.ivSettings);
        btnSettings.setImageResource(0);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item_view = (TextView) view.findViewById(R.id.profileItem);
                switch (item_view.getText().toString()) {
                    case "Manage parent account":
                        startActivity(new Intent(Settings.this, AddParentAccount.class));
                        break;
                    case "Change Password":
                        showPasswordResetWindow(item_view);

                        break;
                }
            }
        });
        ProfileSettingAdapter adapter = new ProfileSettingAdapter(this, R.layout.childacc_listview_entry, profile_menu);
        lv.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        if (v == btnBack) {
            finish();
        }

        if (v == pwReset) {
            EditText pwView = (EditText) popupView.findViewById(R.id.password);
            EditText confirmPwView = (EditText)popupView. findViewById(R.id.confirmPassword);
            pw = pwView.getText().toString().trim();
            confirmPw = confirmPwView.getText().toString().trim();
            if (TextUtils.isEmpty(pw)) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
                return;
            } else if (TextUtils.isEmpty(confirmPw)) {
                Toast.makeText(this, "Please confirm the password", Toast.LENGTH_LONG).show();
                return;
            } else if (!pw.equals(confirmPw)) {
                Toast.makeText(this, "Inconsistent password", Toast.LENGTH_LONG).show();
                return;
            } else {
                final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                currentUser.updatePassword(pw).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        FirebaseDatabase.getInstance().getReference().child("user")
                                .child(currentUser.getUid()).child("password").setValue(pw);
                        popupWindow.dismiss();
                    }
                });

            }

            popupWindow.dismiss();
        }
    }

    private void showPasswordResetWindow(View view) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);

        popupView = inflater.inflate(R.layout.change_password_layout,
                (ViewGroup) Settings.this.findViewById(R.id.popup_element));

        // create the popup window
        int width = 800;
        int height = 600;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        popupWindow = new PopupWindow(popupView, width, height, focusable);

        pwReset = (Button) popupView.findViewById(R.id.pwReset);
        pwReset.setOnClickListener(Settings.this);
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.TOP, 0, 250);
        popupWindow.setFocusable(true);
        // dismiss the popup window when touched

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });


    }


}
