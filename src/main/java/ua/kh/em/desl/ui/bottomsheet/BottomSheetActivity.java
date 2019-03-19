package ua.kh.em.desl.ui.bottomsheet;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.kh.em.desl.R;


public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bs_persistent)
    LinearLayout bsPersistent;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    BottomSheetDialog bsDialog;

    Button btnFile, btnGallery, btnSd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

// Инициализируем и наполняем нижнюю панель.
        final BottomSheetBehavior bsBehavior = BottomSheetBehavior.from(bsPersistent);

        if (bsBehavior != null)
// Устанавливаем обратный вызов/callback для уведомления о событиях нижней панели.
        bsBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
// Метод onStateChanged() вызывается при смене состояний нижней панели.
// View bottomSheet - нижняя панель.
// int newState - новое состояние.
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
// Состояния BottomSheet
                switch (newState) {
// STATE_COLLAPSED - свернутое состояние, по умолчанию
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        break;
// STATE_EXPANDED - развернутое состояние
                    case BottomSheetBehavior.STATE_EXPANDED:
                        break;
// STATE_DRAGGING - промежуточное состояние при перетаскивании нижней панели вверх или вниз
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                }

                if(bsBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    fab.setEnabled(true);
                } else{
                    fab.setEnabled(false);
                }
            }

// Метод onSlide() вызывается, когда нижняя панель перетаскивается.
// View bottomSheet - нижняя панель.
// float slideOffset - новое смещение нижней панели в диапазоне [-1,1].
// Смещение увеличивается по мере продвижения нижней панели вверх.
// От 0 до 1 панель находится между свернутым и развернутым состояниями,
// а от -1 до 0 - между скрытым (STATE_HIDDEN) и свернутым состояниями.
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
// Модифицируем FAB.
// FAB изменяется при движении нижней панели.
                fab.animate().scaleX(1-slideOffset).scaleY(1-slideOffset).setDuration(0).start();
            }
        });

        initBsModal();
    }

    private void initBsModal() {

        View bsModal = getLayoutInflater().inflate(R.layout.bottom_sheet_modal, bsPersistent, false);
        bsDialog = new BottomSheetDialog(this);
        bsDialog.setContentView(bsModal);
        bsDialog.setCancelable(false);
        bsDialog.setCanceledOnTouchOutside(true);

        btnFile = (Button) bsModal.findViewById(R.id.btn_file);
        btnGallery = (Button) bsModal.findViewById(R.id.btn_gallery);
        btnSd = (Button) bsModal.findViewById(R.id.btn_sd);

        btnFile.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
        btnSd.setOnClickListener(this);
    }

// Множественная привязка кнопок к слушателю нажатия
// через ButterKnife
    @OnClick({R.id.btn_fb,
              R.id.btn_gp,
              R.id.btn_ln,
              R.id.btn_pt,
              R.id.btn_tw}) void socialClick(){
        Toast.makeText(this, "Shared!!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fab) void fabClick() {
       bsDialog.show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == btnFile.getId() ||
            view.getId() == btnGallery.getId() ||
            view.getId() == btnSd.getId()){
                Toast.makeText(this, "Added!!", Toast.LENGTH_SHORT).show();
        }
    }
}
