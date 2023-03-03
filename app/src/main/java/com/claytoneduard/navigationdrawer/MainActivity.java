package com.claytoneduard.navigationdrawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.claytoneduard.navigationdrawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarEmail();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_servico, R.id.nav_clientes, R.id.nav_contato, R.id.nav_sobre
        )
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void enviarEmail() {

        String celular = "tel:64992118865";
        String imagem = "https://www.lg.com/pt/lg-experience/images/pt-pt/sunset-praias-header.jpg";
        String endereco = "https://www.google.com/maps/place/Aut%C3%B3dromo+Internacional+de+Goi%C3%A2nia+Ayrton+Senna/@-16.7191168,-49.1942484,17z/data=!3m1!4b1!4m6!3m5!1s0x935eefe7d8858de7:0xe6292e91f0add220!8m2!3d-16.7191219!4d-49.1920544!16s%2Fm%2F06w85db";
        // Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(celular));
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imagem));
        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(endereco));
        Intent intent = new Intent(Intent.ACTION_SEND);
        //destinatarios
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"clayton.eduard@hotmail.com"});
        // asunto do email
        intent.putExtra(Intent.EXTRA_SUBJECT, "Contao pelo APP");
        // mensagem do email
        intent.putExtra(Intent.EXTRA_TEXT, "Mensagem automática");

        // tipo de acao a enviar,
         intent.setType("message/rfc822"); // tipo email
        //intent.setType("text/plain"); // tipo whastap, facaboock e etc
        //intent.setType("image/*"); // qualquer tipo de imagem
        //intent.setType("application/pdf"); // fazer abertura de aplicativos tipo pdf
        startActivity(Intent.createChooser(intent, "Escolha um App de e-mail"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}