package es.saladillo.alejandro.claseasync;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final Integer NUM_TAREAS = 10;
    @BindView(R.id.prbBar)
    ProgressBar progressBar;
    @BindView(R.id.btnStart)
    Button btnStart;
    @BindView(R.id.lblMessage)
    TextView lblMessage;
    @BindView(R.id.prbCircular)
    ProgressBar prbCircular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }
    //creacion del hilo secundario pasandole un numero de tareas
    @OnClick(R.id.btnStart)
    public void ejecutartarea() {
        TareaSecundaria tarea = new TareaSecundaria();
        tarea.execute(NUM_TAREAS);
    }
    //Hilo secundario que realiza la tarea , el primer parametro corresponde al valor recivido
    //en este caso el NUM_TAREA, el segundo valor corresponde a la actualizacion del progreso onPRogressUpdate
    //El tercer valor corresponde al postexecute para actualizar la barra al acabar
    private class TareaSecundaria extends AsyncTask<Integer, Integer, Integer> {
        //metodo donde ejecutaremos la atrea en segundo plano, en este caso un numero de tareas que actualizaran una barra de tareas
        @Override
        protected Integer doInBackground(Integer... params) {
            int numTrabajos = params[0];
            // Se realizan los pasos.
            for (int i = 0; i < numTrabajos && !isCancelled(); i++) {
                // Se pone a ejecutarTarea.
                ejecutarTarea();
                // Informa del progreso.
                publishProgress(i + 1);
            }
            // Se retorna el número de trabajos realizados.
            return numTrabajos;
        }

        @Override
        protected void onPreExecute() {
            actualizarBar(true);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            actualizarBar(false);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            actualizarProgreso(values[0]);
        }

        private void ejecutarTarea() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void actualizarBar(boolean iniciada) {
        if (!iniciada) {
            progressBar.setProgress(0);
            lblMessage.setText("");
        }
        btnStart.setEnabled(!iniciada);
        btnStart.setEnabled(iniciada);
        progressBar.setVisibility(iniciada ? View.VISIBLE : View.INVISIBLE);
        lblMessage.setVisibility(iniciada ? View.VISIBLE : View.INVISIBLE);
        prbCircular.setVisibility(iniciada ? View.VISIBLE : View.INVISIBLE);
    }

    public void actualizarProgreso(int values) {
        progressBar.setProgress(values);
        lblMessage.setText("Trabajos realizados: " + values + " de " + NUM_TAREAS);
    }

}
