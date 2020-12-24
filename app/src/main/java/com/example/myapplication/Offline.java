package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class Offline extends AppCompatActivity {
    private EditText editColumns;
    private EditText editPlayers;
    private int posicionColumns;
    private int posicionPlayers;
    private Spinner spinnerColumns;
    private Spinner spinnerPlayers;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        goFullScreen();
        this.spinnerPlayers = (Spinner) findViewById(R.id.spinnerPlayers);
        ArrayAdapter<CharSequence> adapterPlayers = ArrayAdapter.createFromResource(this, R.array.players_array, android.R.layout.simple_spinner_item);
        adapterPlayers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerPlayers.setAdapter((SpinnerAdapter) adapterPlayers);
        this.spinnerColumns = (Spinner) findViewById(R.id.spinnerColumns);
        ArrayAdapter<CharSequence> adapterColumns = ArrayAdapter.createFromResource(this, R.array.columns_array, android.R.layout.simple_spinner_item);
        adapterColumns.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerColumns.setAdapter((SpinnerAdapter) adapterColumns);
        this.spinnerColumns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /* class com.example.myapplication.Offline.C03851 */

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Offline.this.posicionColumns = position;
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.spinnerPlayers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /* class com.example.myapplication.Offline.C03862 */

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Offline.this.posicionPlayers = i;
                View decorView = getWindow().getDecorView();
                // Hide the status bar.
                int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN  | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                decorView.setSystemUiVisibility(uiOptions);
                // Remember that you should never show the action bar if the
                // status bar is hidden, so hide that too if necessary.
                ActionBar actionBar = getSupportActionBar();
                actionBar.hide();
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                View decorView = getWindow().getDecorView();
                // Hide the status bar.
                int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN  | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                decorView.setSystemUiVisibility(uiOptions);
                // Remember that you should never show the action bar if the
                // status bar is hidden, so hide that too if necessary.
                ActionBar actionBar = getSupportActionBar();
                actionBar.hide();
            }
        });
        EditText editText = (EditText) findViewById(R.id.morePlayers2);
        this.editPlayers = editText;
        editText.setEnabled(false);
        EditText editText2 = (EditText) findViewById(R.id.moreColumns);
        this.editColumns = editText2;
        editText2.setEnabled(false);
        Log.i("length", "" + this.editPlayers.getText().length());
        this.editPlayers.setText("1");
        this.editColumns.setText("2");
        if (((CheckBox) findViewById(R.id.checkPlayers)).isChecked()) {
            this.editPlayers.setEnabled(true);
            this.spinnerPlayers.setEnabled(false);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void SeeGrid(View view) {
        Intent intent = new Intent(this, SeeGrid.class);
        if (this.spinnerColumns.isEnabled()) {
            intent.putExtra("posicion", (this.posicionColumns + 1) * 5);
        } else {
            try {

                if (Integer.parseInt(this.editColumns.getText().toString()) <= 50) {
                    Toast.makeText(this, "" + Integer.parseInt(this.editColumns.getText().toString()), Toast.LENGTH_SHORT).show();
                    if (Integer.parseInt(this.editColumns.getText().toString()) > 1) {
                        intent.putExtra("posicion", Integer.parseInt(this.editColumns.getText().toString()));
                        startActivity(intent);

                    }
                }else {
                    Toast.makeText(this, "ENTER NUMBERS LESS THAN 51, GREATER THAN 1", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (Exception e) {
                Toast.makeText(this, "ENTER VALUE", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    public void jugar(View view) {
        Intent intent = new Intent(this, Juego.class);
        if (this.spinnerPlayers.isEnabled()) {
            intent.putExtra("jugadores", this.posicionPlayers + 1);
        } else {
            try {
                if (Integer.parseInt(this.editColumns.getText().toString()) <= 50 & Integer.parseInt(this.editColumns.getText().toString()) > 1) {

                    intent.putExtra("jugadores", Integer.parseInt(this.editPlayers.getText().toString()));

                }else {
                    Toast.makeText(this, "ENTER NUMBERS GREATER THAN 0", Toast.LENGTH_LONG).show();
                    return;
                }
            } catch (Exception e) {
                Toast.makeText(this, "ENTER VALUE", Toast.LENGTH_LONG).show();
                return;
            }
        }
        if (this.spinnerColumns.isEnabled()) {
            intent.putExtra("posicion", (this.posicionColumns + 1) * 5);
        } else {
            try {
                if (Integer.parseInt(this.editColumns.getText().toString()) <= 50 & (Integer.parseInt(this.editColumns.getText().toString()) > 1)) {

                        intent.putExtra("posicion", Integer.parseInt(this.editColumns.getText().toString()));
                        startActivity(intent);

                }else{
                Toast.makeText(this, "ENTER NUMBERS LESS THAN 51, GREATER THAN 1", Toast.LENGTH_LONG).show();

                };
            } catch (Exception e2) {
                Toast.makeText(this, "ENTER VALUE", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onCheckboxClickedPlayer(View view) {
        if (((CheckBox) view).isChecked()) {
            this.editPlayers.setEnabled(true);
            this.spinnerPlayers.setEnabled(false);
            return;
        }
        this.editPlayers.setEnabled(false);
        this.spinnerPlayers.setEnabled(true);
    }

    public void onCheckboxClickedColumns(View view) {
        if (((CheckBox) view).isChecked()) {
            this.editColumns.setEnabled(true);
            this.spinnerColumns.setEnabled(false);
            return;
        }
        this.editColumns.setEnabled(false);
        this.spinnerColumns.setEnabled(true);
    }
    private void goFullScreen()
    {
        // Only navigation will be shown when opening a spinner
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN  | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        // Go full screen again when a spinner is closed
        if (hasFocus) {
            goFullScreen();
        }
    }
}