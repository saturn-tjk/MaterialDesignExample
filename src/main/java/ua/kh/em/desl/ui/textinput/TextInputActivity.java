package ua.kh.em.desl.ui.textinput;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.kh.em.desl.R;

public class TextInputActivity extends AppCompatActivity {

    final Context context = this;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.txt_inp_1) TextInputLayout txtInput_1;
    @BindView(R.id.txt_inp_2) TextInputLayout txtInput_2;
    @BindView(R.id.txt_inp_3) TextInputLayout txtInput_3;
    @BindView(R.id.et_inp_1) EditText etInput_1;
    @BindView(R.id.et_inp_2) TextInputEditText etInput_2;
    @BindView(R.id.et_inp_3) EditText etInput_3;
    @BindView(R.id.text_output) TextView textOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        nameError();
    }

// Валидация ввода имени	
    private void nameError() {
        txtInput_1.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                if (charSequence.length() > 0 && charSequence.length() <= 3) {
                    txtInput_1.setError(getString(R.string.username_must));
// Установка подключения вывода ошибки при валидации ввода текста
                    txtInput_1.setErrorEnabled(true);
                } else {
                    txtInput_1.setErrorEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

// Обработчик нажатия на FAB
    @OnClick(R.id.fab)
    void fabClick(){
        String txt_1 = etInput_1.getText().toString();
        String txt_2 = etInput_2.getText().toString();
        String txt_3 = etInput_3.getText().toString();

        if(txt_1.isEmpty() || txt_1.length() == 0 || txt_2.isEmpty() || txt_2.length() == 0){
            Toast toast = Toast.makeText(getApplicationContext(), R.string.input_empty, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();

        } else if (!emailValid(txt_3)) {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.email_input_error, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();

        }else {
            Toast.makeText(context, txt_1 + "\n" + txt_2 + "\n" + txt_3, Toast.LENGTH_SHORT).show();
            textOutput.setText(txt_1 + "\n" + txt_2 + "\n" + txt_3);
        }
    }

// Валидация email
    private boolean emailValid(String input_Email) {
//        String EMAIL_VALID = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        String EMAIL_VALID = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

        Pattern pattern = Pattern.compile(EMAIL_VALID, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input_Email);
        return matcher.matches();
    }

}
