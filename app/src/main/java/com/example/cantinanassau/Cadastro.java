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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cantinanassau.Models.Cliente;
import com.example.cantinanassau.Models.Item;

import java.util.ArrayList;
import java.util.List;

public class Cadastro extends AppCompatActivity {


    private EditText edtNomeCliente;
    private EditText edtTelefone;
    private EditText edtCpf;
    private BancodeDados Database;
    private Button submitButton;

    private List<EditText> clientFields = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        Database = new BancodeDados(this);

        // Inicializar componentes

        edtNomeCliente = findViewById(R.id.edtNomeCliente);
        edtTelefone = findViewById(R.id.edtTelefone);
        edtCpf = findViewById(R.id.edtCpf);//CAMPO NOVA AJEITA AE SIDNEY
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

        submitButton.setOnClickListener(v -> addNewCliente());
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

    public void addNewCliente(){
        String nomeCliente = edtNomeCliente.getText().toString().trim(); //.trim remove espaços extras
        String telefoneCliente = edtTelefone.getText().toString().trim();
        String cpfCliente = edtCpf.getText().toString().trim();

        // Só posso verificar se está vazio se tranformar tudo em string
        if (nomeCliente.isEmpty() || telefoneCliente.isEmpty() || cpfCliente.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        long telefoneDoCliente;
        int cpfDoCliente;
        int saldoCliente = 0;

        try {
            telefoneDoCliente = Integer.parseInt(telefoneCliente);
            cpfDoCliente = Integer.parseInt(cpfCliente);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valor ou estoque inválido!", Toast.LENGTH_SHORT).show();
            return;
        }

        Cliente newCliente = new Cliente(cpfDoCliente, nomeCliente, saldoCliente ,telefoneDoCliente);

        boolean result = Database.insertCliente(newCliente);

        if(result){
            Toast.makeText(this, "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Erro ao cadastrar o Cliente!", Toast.LENGTH_SHORT).show();
        }

    };



}
