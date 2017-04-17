package com.example.rogger.paralelo;
//Primero corremos la aplicacion
//Como se aprecio, ambos procesos se ejecutaron sin problemas
//ninguno espero a que acabe el otro para que se ejecutaran
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Principal extends AppCompatActivity {

    private ImageView mImageView;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mImageView = (ImageView) findViewById(R.id.imagen);
    }
//Los botones ya estan referenciados en el activity_principal para cada una de estas funciones
    public void onClick(View v) {
        uploadImage();
    }

    public void onClick2(View v) {
        launchProgressDialog();
    }
//Aqui cargamos la imagen
    //para ello usamos un hilo
    public void uploadImage() {
        new Thread(new Runnable() {
            private Bitmap loadImageFromNetwork(String url){
                try {
                    Bitmap mBitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                    return mBitmap;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
//esta es la operacion que realiza el hilo
            public void run() {
                String url = "https://maniawallpapers.files.wordpress.com/2012/03/234074-1920x1080.jpg";
                final Bitmap mBitmap = loadImageFromNetwork(url);
                mImageView.post(new Runnable() {
                    public void run() {
                        mImageView.setImageBitmap(mBitmap);
                    }
                });
            }
        }).start();//aca recien inicia su operacion
    }
//para la siguiente operacion de la barra de carga
    public void launchProgressDialog() {
        mProgressDialog = new ProgressDialog(Principal.this);
        mProgressDialog.setTitle("Simulando descarga ...");
        mProgressDialog.setMessage("Descarga en progreso ...");
        mProgressDialog.setProgressStyle(mProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setProgress(0);
        mProgressDialog.setMax(100);
        mProgressDialog.show();
//se define otro hilo
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j=1; j<=10; j++){
                    try{
                        Thread.sleep(500);
                        mProgressDialog.incrementProgressBy(100/10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                mProgressDialog.dismiss();

                }//aqui se inicia la operacion
        }).start();
    }
}