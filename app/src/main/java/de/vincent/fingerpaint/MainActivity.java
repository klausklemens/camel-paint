package de.vincent.fingerpaint;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Painter";

    DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(0).setCheckable(false);

        drawView = findViewById(R.id.drawViewCanvas);
        drawView.setBackgroundColor(Color.RED);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.background:
                    background_helper();
                    return false;
                case R.id.brush_color:
                    brush_color_helper();
                    return false;
                case R.id.brush_size:
                    brush_size_helper();
                    return false;
                case R.id.clear:
                    drawView.setBackgroundColor(Color.WHITE);
                    drawView.clearPoints();
                    return false;
            }
            return false;
        }
    };

    private void background_helper() {
        String[] colors = {
                getResources().getString(R.string.white),
                getResources().getString(R.string.blue),
                getResources().getString(R.string.cyan),
                getResources().getString(R.string.green),
                getResources().getString(R.string.magenta),
                getResources().getString(R.string.red),
                getResources().getString(R.string.yellow),
                getResources().getString(R.string.black),
                getResources().getString(R.string.random),
                getResources().getString(R.string.image)
        };

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getResources().getString(R.string.pick_background));
        dialog.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Click", "Angeklickt: " + which);
                switch (which) {
                    case 0:
                        drawView.setBackgroundColor(Color.WHITE);
                    case 1:
                        drawView.setBackgroundColor(Color.BLUE);
                    case 2:
                        drawView.setBackgroundColor(Color.CYAN);
                    case 3:
                        drawView.setBackgroundColor(Color.GREEN);
                    case 4:
                        drawView.setBackgroundColor(Color.MAGENTA);
                    case 5:
                        drawView.setBackgroundColor(Color.RED);
                    case 6:
                        drawView.setBackgroundColor(Color.YELLOW);
                    case 7:
                        drawView.setBackgroundColor(Color.BLACK);
                    case 8:
                        drawView.setBackgroundColor(0);
                    case 9:
                        // TODO: background image
                }
            }
        });
        dialog.show();
    }

    private void brush_color_helper() {
        String[] colors = {
                getResources().getString(R.string.white),
                getResources().getString(R.string.blue),
                getResources().getString(R.string.cyan),
                getResources().getString(R.string.green),
                getResources().getString(R.string.magenta),
                getResources().getString(R.string.red),
                getResources().getString(R.string.yellow),
                getResources().getString(R.string.black),
                getResources().getString(R.string.random)
        };

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getResources().getString(R.string.pick_brush_color));
        dialog.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, "Angeklickt: " + which);
                switch (which) {
                    case 0:
                        drawView.setBrushColor(Color.WHITE);
                    case 1:
                        drawView.setBrushColor(Color.BLUE);
                    case 2:
                        drawView.setBrushColor(Color.CYAN);
                    case 3:
                        drawView.setBrushColor(Color.GREEN);
                    case 4:
                        drawView.setBrushColor(Color.MAGENTA);
                    case 5:
                        drawView.setBrushColor(Color.RED);
                    case 6:
                        drawView.setBrushColor(Color.YELLOW);
                    case 7:
                        drawView.setBrushColor(Color.BLACK);
                    case 8:
                        drawView.setBrushColor(0);
                }
            }
        });
        dialog.show();
    }

    private void brush_size_helper() {
        String[] colors = {
                getResources().getString(R.string.size_xs),
                getResources().getString(R.string.size_s),
                getResources().getString(R.string.size_m),
                getResources().getString(R.string.size_l),
                getResources().getString(R.string.size_xl),
        };

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getResources().getString(R.string.pick_brush_size));
        dialog.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        drawView.setBrushWidth(0);
                    case 1:
                        drawView.setBrushWidth(5);
                    case 2:
                        drawView.setBrushWidth(10);
                    case 3:
                        drawView.setBrushWidth(15);
                    case 4:
                        drawView.setBrushWidth(20);
                }
            }
        });
        dialog.show();
    }

}
