package radaelli.chagas.adami.email2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnEnviar = (Button) findViewById(R.id.btnEnviar);
        // Definicao da acao do click do botao
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtendo dados digitados pelo usuario
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                String email = etEmail.getText().toString();
                EditText etAssunto = (EditText) findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();
                EditText etTexto = (EditText) findViewById(R.id.etTexto);
                String texto = etTexto.getText().toString();

                // criando a intent com a acao de enviar
                Intent i = new Intent(Intent.ACTION_SENDTO);

                // especificando que o programa so quer apps de email
                i.setData(Uri.parse("mailto:"));

                //criando um array de enderecos de email
                String[] emails = new String[]{email};
                // matriz de string de endereco de email
                i.putExtra(Intent.EXTRA_EMAIL, emails);
                // string com o assunto de email
                i.putExtra(Intent.EXTRA_SUBJECT,assunto);
                // string com o texto do email
                i.putExtra(Intent.EXTRA_TEXT, texto);

                //com isso o usuario pode selecionar o app de email que deseja utilizar caso ele tenha mais de uma app
                try{
                    startActivity(Intent.createChooser(i, "Escolha o APP"));
                }
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Não há nenhum app que posso realizar essa operação", Toast.LENGTH_LONG).show();
                }
        };



        });
    }
}