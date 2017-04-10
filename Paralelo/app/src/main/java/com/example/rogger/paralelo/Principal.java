package com.example.rogger.paralelo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    private ProgressDialog Progress;
    private MiTareaAsincronaDialog tarea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void onClick(View view) {
            Progress = new ProgressDialog(Principal.this);
            Progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            Progress.setMessage("Procesando...");
            Progress.setCancelable(true);
            Progress.setMax(100);

            tarea = new MiTareaAsincronaDialog();
            tarea.execute();
    }

    private void tareaLarga()
    {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException ignored) {}
    }

    private class MiTareaAsincronaDialog extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            for(int i=1; i<=10; i++) {
                tareaLarga();

                publishProgress(i*10);

                if(isCancelled())
                    break;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0];

            Progress.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {

            Progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    MiTareaAsincronaDialog.this.cancel(true);
                }
            });

            Progress.setProgress(0);
            Progress.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                Progress.dismiss();
                Toast.makeText(Principal.this, "Tarea finalizada!",
                        Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(Principal.this, "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
