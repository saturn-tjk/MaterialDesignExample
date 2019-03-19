package ua.kh.em.desl.ui.snackbar;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.kh.em.desl.R;

public class SnackBarActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.btn_snb_a) void onClickA(){
        Snackbar snackbar = Snackbar.make(linearLayout,R.string.snb_text_1,Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @OnClick(R.id.btn_snb_b)void onClickB(){
        Snackbar snackbar = Snackbar.make(linearLayout,R.string.snb_text_2_1,Snackbar.LENGTH_LONG)
                .setAction(R.string.snb_action_2, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(linearLayout,R.string.snb_text_2_2,Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });
        snackbar.show();
    }

    @OnClick(R.id.btn_snb_c) void onClickC(){
        Snackbar snackbar = Snackbar.make(linearLayout,R.string.snb_text_3_1,Snackbar.LENGTH_LONG)
                .setAction(R.string.snb_action_3, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(linearLayout,R.string.snb_text_3_2,Snackbar.LENGTH_LONG);
                        snackbar1.getView().setBackgroundColor(Color.parseColor("#f78d2e"));
                        snackbar1.show();
                    }
                })
                .setActionTextColor(Color.BLACK);
        snackbar.getView().setBackgroundColor(Color.parseColor("#f78d2e"));
        snackbar.show();
    }

    @OnClick(R.id.fab) void onClickFab(){
        Snackbar snackbar = Snackbar.make(linearLayout,R.string.snb_text_1,Snackbar.LENGTH_LONG);
        snackbar.setDuration(4000);
        snackbar.show();
    }

}
