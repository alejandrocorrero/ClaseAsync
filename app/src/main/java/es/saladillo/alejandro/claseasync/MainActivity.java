package es.saladillo.alejandro.claseasync;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final Integer NUM_TAREAS = 10;
    @BindView(R.id.prbBar)
    ProgressBar progressBar;
    @BindView(R.id.prbCircular)
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
    public void ejecutartarea(){
        TareaSecundaria tarea = new TareaSecundaria();
        tarea.execute(NUM_TAREAS);
    }

    private class TareaSecundaria extends AsyncTask<Integer,Integer,String>{
        @Override
        protected String doInBackground(Integer... integers) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
