package com.example.cantinanassau;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class Cadastro extends AppCompatActivity {


    private EditText edtNomeCliente;
    private EditText edtTelefone;

    private Button submitButton;

    private List<EditText> clientFields = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        // Inicializar componentes

        edtNomeCliente = findViewById(R.id.edtNomeCliente);
        edtTelefone = findViewById(R.id.edtTelefone);
        submitButton = findViewById(R.id.submitButton);


        // Adicionar campos à lista de campos do cliente

        clientFields.add(edtNomeCliente);
        clientFields.add(edtTelefone);


        // Adicionar listeners de texto para validação dos campos do cliente
        for (EditText field : clientFields) {
            field.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    validateFields();
                }
            });
        }

        // Validar campos inicialmente
        validateFields();
    }

    private void validateFields() {
        boolean allClientFieldsFilled = true;

        // Verificar se todos os campos do cliente estão preenchidos
        for (EditText field : clientFields) {
            if (field.getText().toString().trim().isEmpty()) {
                allClientFieldsFilled = false;
                break;
            }
        }

        // Habilitar ou desabilitar o botão
        boolean enableButton = allClientFieldsFilled;
        submitButton.setEnabled(enableButton);

        // Mudar a cor do botão conforme o estado
        if (submitButton.isEnabled()) {
            submitButton.setBackgroundColor(Color.parseColor("#FF6200EE")); // Roxo
        } else {
            submitButton.setBackgroundColor(Color.parseColor("#CCCCCC")); // Cinza
        }

    }
}
