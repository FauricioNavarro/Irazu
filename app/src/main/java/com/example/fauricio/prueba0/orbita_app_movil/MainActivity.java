package com.example.fauricio.prueba0.orbita_app_movil;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import net.sourceforge.jtds.jdbc.*;

public class MainActivity extends AppCompatActivity {
    private WebView orbita;
    private TextView textView;
    public Connection conexionSql;
    public static String mensaje = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.texto);
        servicio servicio1 = new servicio();
        servicio1.execute("");
        Log.e("MSG >>",mensaje);
        textView.setText(mensaje);
    }

    public class servicio extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... params) {
            String mensajeAux;
            try{
                conexionSql = connectionclass();
                if(conexionSql==null){
                    mensaje = "Revisar acceso a internet";
                    mensajeAux = "Revisar acceso a internet";
                    Log.e("mesaje >>","Referencia nula");
                }else{
                    String query = "SELECT * FROM USUARIO";
                    Statement statement = conexionSql.createStatement();
                    Log.e("mesaje >>","Entro a la consulta");
                    ResultSet resultSet = statement.executeQuery(query);
                    Log.e("mesaje >>","Ejecución de la consulta");
                    if(resultSet.next()){
                        mensaje = "Query exitoso";
                        mensajeAux = "Query exitoso";

                    }else{
                        mensaje = "Query no exitoso";
                        mensajeAux = "Query no exitoso";
                    }
                    //CallableStatement callSp = conexionSql.prepareCall("{call TablaProyectos()}");
                }
            }catch (Exception e){
                mensaje = "Query invalido";
                mensajeAux = e.getMessage();
            }
            //textView.setText(mensaje);
            Log.e("mesaje final >>",mensajeAux);
            return mensaje;
        }
    }

    @SuppressLint("NewApi")
    public Connection connectionclass(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String stringConexion;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            //stringConexion = "jdbc:jtds:sqlserver://diseno.database.windows.net:1433;database=Proyectos_SETECLab;user=adminDiseno@diseno;password=Irazu2701$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            //stringConexion = "jdbc:jtds:sqlserver://192.168.100.7:1433;database=Proyectos_SETECLab;user=adminDiseno@diseno;password=Irazu2701$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            stringConexion = "jdbc:jtds:sqlserver://172.19.48.1:1433;database=Proyectos_SETECLab;user=adminDiseno@diseno;password=Irazu2701$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            Log.e("MSG >>>>","Entro string de conexion");
            //connection = DriverManager.getConnection(stringConexion,"davidrz2103@hotmail.com","david2196");
            connection = DriverManager.getConnection(stringConexion);
        }catch (SQLException e){
            Log.e("error 1",e.getMessage());
        }catch (ClassNotFoundException a){
            Log.e("error 2",a.getMessage());
        }catch (Exception f){
            Log.e("error 3",f.getMessage());
        }
        return connection;
    }

}
