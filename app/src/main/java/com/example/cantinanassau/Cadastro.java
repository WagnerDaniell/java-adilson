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

    private EditText edtMatriculaCliente;
    private EditText edtNomeCliente;
    private EditText edtTelefone;
    private EditText edtCpfResponsavel;
    private EditText edtNomeResponsavel;
    private EditText edtTelefoneResponsavel;
    private Spinner ageSpinner;
    private Button submitButton;
    private LinearLayout responsavelLayout;
    private List<EditText> clientFields = new ArrayList<>();
    private List<EditText> responsavelFields = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        // Inicializar componentes
        edtMatriculaCliente = findViewById(R.id.edtMatriculaCliente);
        edtNomeCliente = findViewById(R.id.edtNomeCliente);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtCpfResponsavel = findViewById(R.id.edtCpfResponsavel);
        edtNomeResponsavel = findViewById(R.id.edtNomeResponsavel);
        edtTelefoneResponsavel = findViewById(R.id.edtTelefoneResponsavel);
        ageSpinner = findViewById(R.id.ageSpinner);
        submitButton = findViewById(R.id.submitButton);
        responsavelLayout = findViewById(R.id.responsavelLayout);

        // Adicionar campos à lista de campos do cliente
        clientFields.add(edtMatriculaCliente);
        clientFields.add(edtNomeCliente);
        clientFields.add(edtTelefone);

        // Adicionar campos à lista de campos do responsável
        responsavelFields.add(edtCpfResponsavel);
        responsavelFields.add(edtNomeResponsavel);
        responsavelFields.add(edtTelefoneResponsavel);

        // Configurar o spinner com faixas etárias
        String[] ageRanges = {"Selecione", "Menor de 18 anos", "Adulto"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ageRanges);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(adapter);

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

        // Adicionar listeners de texto para validação dos campos do responsável
        for (EditText field : responsavelFields) {
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

        // Adicionar listener para o spinner
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Verificar se é menor de idade
                if (position == 1) { // "Menor de 18 anos"
                    responsavelLayout.setVisibility(View.VISIBLE);
                } else {
                    responsavelLayout.setVisibility(View.GONE);
                    // Limpar campos do responsável quando escondidos
                    clearResponsavelFields();
                }
                validateFields();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                responsavelLayout.setVisibility(View.GONE);
                validateFields();
            }
        });

        // Validar campos inicialmente
        validateFields();
    }

    private void clearResponsavelFields() {
        edtCpfResponsavel.setText("");
        edtNomeResponsavel.setText("");
        edtTelefoneResponsavel.setText("");
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

        // Verificar se uma faixa etária foi selecionada (não é a primeira opção)
        boolean ageSelected = ageSpinner.getSelectedItemPosition() > 0;

        // Verificar se é menor de idade
        boolean isMinor = ageSpinner.getSelectedItemPosition() == 1; // "Menor de 18 anos"

        // Verificar campos do responsável se for menor de idade
        boolean allResponsavelFieldsFilled = true;
        if (isMinor) {
            for (EditText field : responsavelFields) {
                if (field.getText().toString().trim().isEmpty()) {
                    allResponsavelFieldsFilled = false;
                    break;
                }
            }
        }

        // Habilitar ou desabilitar o botão
        boolean enableButton = allClientFieldsFilled && ageSelected && (isMinor ? allResponsavelFieldsFilled : true);
        submitButton.setEnabled(enableButton);

        // Mudar a cor do botão conforme o estado
        if (submitButton.isEnabled()) {
            submitButton.setBackgroundColor(Color.parseColor("#FF6200EE")); // Roxo
        } else {
            submitButton.setBackgroundColor(Color.parseColor("#CCCCCC")); // Cinza
        }

    }
}
