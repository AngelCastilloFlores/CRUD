package com.example.android.menudietas.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.menudietas.activity.AyudaActivity;
import com.example.android.menudietas.activity.CalculoActivity;
import com.example.android.menudietas.R;

import static com.example.android.menudietas.R.id.actividad_rg;

/**
 * Created by angelcastillo on 5/5/17.
 */
public class DietasFragment extends Fragment{
    
    private static final String TAG = DietasFragment.class.getSimpleName();
    private static final String HOMBRE = "Hombre";
    private static final String MUJER = "Mujer";

    private static final double NULA = 1.2;
    private static final double LIGERA = 1.375;
    private static final double MODERADA = 1.55;
    private static final double INTENSA = 1.725;
    private static final double MUYINTENSA = 1.9;

    private static final String PESO = "Peso";

    float resultadoP;
    float resultadoG;
    float resultadoH;
    float resultadoK;

    //mantenimiento
    float resultadoKmantenimiento;
    float resultadoPmantenimiento;
    float resultadoHmantenimiento;
    float resultadoGmantenimiento;


    //PESO
    private RadioGroup sexoRg;
    private RadioButton hombreRb;
    private RadioButton mujerRb;

    //ACTIVIDAD
    private RadioGroup actividad_rb;
    private RadioButton rbnula;
    private RadioButton rbligera;
    private RadioButton rbmoderada;
    private RadioButton rbintensa;
    private RadioButton rbmuyintensa;

    //EDAD
    private TextView quantityTextView;
    private Button incrementAgeButton;
    private Button decrementAgeButton;

    //ALTURA
    private EditText alturaET;

    private ImageButton siguiente;
    private Button calculadora;


    float peso;
    float altura;
    int quantity = 16;
    double actividad;
    String sexo;

    //VARIABLES FORMULA:
    float tmb;
    float calorias;
    float proteinas;
    float grasas;
    float hidratos;

    //VARIABLES FORMULA MANTENIMIENTO:
    float kmantenimiento;
    float pmantenimiento;
    float hmantenimiento;
    float gmantenimiento;

    TextView pesoET;

    public static DietasFragment newInstance(){
        return new DietasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dietas_fragment, container, false);

        sexoRg = (RadioGroup) view.findViewById(R.id.sexo_rg);
        hombreRb = (RadioButton) view.findViewById(R.id.hombre_rb);
        mujerRb = (RadioButton) view.findViewById(R.id.mujer_rb);

        //ACTIVIDAD
        actividad_rb = (RadioGroup) view.findViewById(actividad_rg);
        rbnula = (RadioButton) view.findViewById(R.id.rbnula);
        rbligera = (RadioButton) view.findViewById(R.id.rbligera);
        rbmoderada = (RadioButton) view.findViewById(R.id.rbmoderada);
        rbintensa = (RadioButton) view.findViewById(R.id.rbintensa);
        rbmuyintensa = (RadioButton) view.findViewById(R.id.rbmuyintensa);

        //EDAD
        quantityTextView = (TextView) view.findViewById(R.id.edadTv);
        incrementAgeButton = (Button) view.findViewById(R.id.incrementAge);
        decrementAgeButton = (Button) view.findViewById(R.id.decrementAge);
        
        //ALTURA
        alturaET = (EditText) view.findViewById(R.id.alturaET);

        //PESO
        pesoET = (EditText) view.findViewById(R.id.pesoET);

        //IMAGEN BOTÓN AYUDA
        siguiente = (ImageButton) view.findViewById(R.id.ayuda);

        calculadora = (Button) view.findViewById(R.id.btnSumar);
        
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sexoRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.hombre_rb) {
                    Log.i(TAG, "soy un hombre.");
                    sexo = HOMBRE;
                } else {
                    Log.i(TAG, "soy una mujer.");
                    sexo = MUJER;
                }
            }
        });

        actividad_rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if (checkedId == R.id.rbnula) {
                    Log.i(TAG, "ACTIVIDAD NULA");
                    actividad = NULA;
                } else if (checkedId == R.id.rbligera) {
                    Log.i(TAG, "ACTIVIDAD LIGERA");
                    actividad = LIGERA;
                } else if (checkedId == R.id.rbmoderada) {
                    Log.i(TAG, "ACTIVIDAD MODERADA");
                    actividad = MODERADA;
                } else if (checkedId == R.id.rbintensa) {
                    Log.i(TAG, "ACTIVIDAD INTENSA");
                    actividad = INTENSA;
                } else if (checkedId == R.id.rbmuyintensa) {
                    Log.i(TAG, "ACTIVIDAD MUY INTENSA");
                    actividad = MUYINTENSA;
                }
            }
        });

        
        alturaET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                altura = convertToFloat(alturaET.getText().toString());
            }
        });


        pesoET.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                peso = convertToFloat(pesoET.getText().toString());
            }
        });


        incrementAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });


        decrementAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent siguiente = new Intent(getActivity(), AyudaActivity.class);
                startActivity(siguiente);
            }
        });


        //BOTÓN CALCULA
        calculadora.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(validarFormulario()){
                    onClickAceptar();
                }

            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        

      
    }

    private boolean validarFormulario(){
        if(sexo == null){
            Toast.makeText(getActivity(), "Ha dejado el campo Sexo vacio.", Toast.LENGTH_LONG).show();
            return  false;
        }

        if(altura <= 0){
            Toast.makeText(getActivity(), "Ha dejado el campo Altura vacio.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(peso <= 0){
            Toast.makeText(getActivity(), "Ha dejado el campo Peso vacio.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(actividad == 0){
            Toast.makeText(getActivity(), "Ha dejado el campo Actividad vacio.", Toast.LENGTH_LONG).show();
            return  false;
        }

        return true;
    }


    //RELACIONAR EL ONCLICK CON EL BOTÓN
    public void onClickAceptar() {
        //Toast.makeText(getApplicationContext(), "Siguiente", Toast.LENGTH_SHORT).show();
        calculo_edad();
        calculo_altura();
        calculo_peso();
        calculo_sexo();
        calculo_actividad();
        calculo_tmb();
        calculo_calorias();


        Intent siguiente = new Intent(getActivity(), CalculoActivity.class);
        siguiente.putExtra("calculo_edad", quantity);
        siguiente.putExtra("calculo_altura", altura);
        siguiente.putExtra("calculo_peso", peso);
        siguiente.putExtra("calculo_sexo", sexo);
        siguiente.putExtra("calculo_actividad", actividad);
        siguiente.putExtra("calculo_tmb", tmb);
        siguiente.putExtra("calculo_calorias", calorias);
        startActivity(siguiente);

    }

    private Float convertToFloat(String stringFloat) {
        float result;
        try {
            result = Float.valueOf(stringFloat);

        } catch (NumberFormatException e) {
            result = 0;
        }

        Log.i(TAG, "result = " + result);
        return result;
    }


    //Este método se usa para llamar al botón +

    public void increment() {
        quantity = quantity + 1;
        if (quantity >= 120) {
            display((int) (quantity = 120));
        }
        display((int) quantity);
    }

    //Este metodo se usa para llamar al botón de -

    public void decrement() {
        quantity = quantity - 1;
        if (quantity < 16) {
            display((int) (quantity = 16));
        } else {
            display((int) quantity);
        }
    }


    /**
     * mostrar resultado
     */
    public float display(int number) {
        quantityTextView.setText(String.valueOf(number));
        return quantity;
    }

    public float calculo_edad() {
        return quantity;
    }

    public float calculo_altura() {
        return altura;
    }

    public float calculo_peso() {
        return peso;
    }

    public String calculo_sexo() {
        return sexo;
    }

    public double calculo_actividad(){
        return actividad;
    }

    public float calculo_tmb() {
        if(calculo_sexo().equals(HOMBRE)){
            tmb = (float) (66 + (13.7 * calculo_peso()) + (5 * calculo_altura()) - (6.8 * calculo_edad()));
        }else{
            tmb = (float) (655 + (9.6 * calculo_peso()) + (1.8 * calculo_altura()) - (4.7 * calculo_edad()));
        }
        return tmb;
    }

    public float calculo_calorias_orientativo() {
        if (calculo_sexo().equals(HOMBRE)) {
            calorias = (float) ((66.473 + (13.752 * calculo_peso()) + (5.0033 * calculo_altura()) - (6.755 * calculo_edad())));
        } else {
            calorias = (float) ((665.1 + (9.463 * calculo_peso()) + (1.8 * calculo_altura()) - (4.6756 * calculo_edad())));
        }
        return calorias;
    }

    public float calculo_calorias(){
        calorias = (float) (calculo_calorias_orientativo() * actividad);
        return calorias;
    }
    /*
    Mujeres  [655 + (9.6 x Peso kg) ] + [ (1.8 x Altura cm) – (4.7 x Edad)] x Factor actividad

    Hombres  [66 + (13.7 x Peso kg) ] + [ (5 x Altura cm) – (6.8 x Edad)] x Factor actividad


    Personas sedentarias (hace poca actividad física): 1.2
    Actividad ligera (hace actividad físisca 1 a 3 veces por semana): 1.375
    Actividad moderada (hace actividad físisca 3 a 5 veces por semana): 1.55
    Actividad intensa (hace actividad física 6 a 7 veces por semana): 1.725
    Actividad extremadamente alta (atletas profesionales mucha actividad física): 1.9
*/
/*
    public float formula() {

        switch (calculo_sexo()) {
            case HOMBRE:
                switch (calculo_actividad()) {
                    //actividad nula
                    case NULA:
                        //PARA AUMENTAR MASA MUSCULAR
                        resultadoK = (float) (((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.2) + 500);
                        resultadoP = (float) (peso * 2.4);
                        resultadoG = (float) (peso * 0.9);
                        resultadoH = ((resultadoK) - ((resultadoP * 4) + (resultadoG * 9))) / 4;

                        //PARA MANTENERSE
                        resultadoKmantenimiento = (float) ((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.2);
                        resultadoPmantenimiento = (float) (peso * 1.7);
                        resultadoHmantenimiento = (float) (peso * 6.2);
                        resultadoGmantenimiento = (float) (peso * 0.5);

                        break;


                    //actividad ligera
                    case LIGERA:
                        //PARA AUMENTAR MASA MUSCULAR
                        resultadoK = (float) (((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.375) + 500);
                        resultadoP = (float) (peso * 2.4);
                        resultadoG = (float) (peso * 0.9);
                        resultadoH = ((resultadoK) - ((resultadoP * 4) + (resultadoG * 9))) / 4;

                        //Para mantenerse
                        resultadoKmantenimiento = (float) ((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.375);
                        resultadoPmantenimiento = (float) (peso * 1.7);
                        resultadoHmantenimiento = (float) (peso * 6.2);
                        resultadoGmantenimiento = (float) (peso * 0.5);

                        break;


                    //Actividad moderada
                    case MODERADA:
                        //Para aumentar masa muscular
                        resultadoK = (float) (((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.55) + 500);
                        resultadoP = (float) (peso * 2.4);
                        resultadoG = (float) (peso * 0.9);
                        resultadoH = ((resultadoK) - ((resultadoP * 4) + (resultadoG * 9))) / 4;

                        //Para mantenerse
                        resultadoKmantenimiento = (float) ((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.55);
                        resultadoPmantenimiento = (float) (peso * 1.7);
                        resultadoHmantenimiento = (float) (peso * 6.2);
                        resultadoGmantenimiento = (float) (peso * 0.5);

                        break;


                    //Actividad intensa
                    case INTENSA:
                        //Para aumentar masa muscular
                        resultadoK = (float) (((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.725) + 500);
                        resultadoP = (float) (peso * 2.4);
                        resultadoG = (float) (peso * 0.9);
                        resultadoH = ((resultadoK) - ((resultadoP * 4) + (resultadoG * 9))) / 4;

                        //Para mantenerse
                        resultadoKmantenimiento = (float) ((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.725);
                        resultadoPmantenimiento = (float) (peso * 1.7);
                        resultadoHmantenimiento = (float) (peso * 6.2);
                        resultadoGmantenimiento = (float) (peso * 0.5);

                        break;


                    //Actividad muy intensa
                    case MUYINTENSA:
                        //Para aumentar masa muscular
                        resultadoK = (float) (((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.9) + 500);
                        resultadoP = (float) (peso * 2.4);
                        resultadoG = (float) (peso * 0.9);
                        resultadoH = ((resultadoK) - ((resultadoP * 4) + (resultadoG * 9))) / 4;

                        //Para mantenerse
                        resultadoKmantenimiento = (float) ((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.9);
                        resultadoPmantenimiento = (float) (peso * 1.7);
                        resultadoHmantenimiento = (float) (peso * 6.2);
                        resultadoGmantenimiento = (float) (peso * 0.5);

                        break;
                }
                break;


            //Si es MUJER hacer estos calculos
            case MUJER:
                switch (calculo_actividad()) {
                    //Actividad nula
                    case NULA:
                        //Para aumentar masa muscular
                        resultadoK = (float) (((665.1 + (9.463 * peso) + (1.8 * altura) - (4.6756 * edad)) * 1.2) + 500);
                        resultadoP = (float) (peso * 2.4);
                        resultadoG = (float) (peso * 0.9);
                        resultadoH = ((resultadoK) - ((resultadoP * 4) + (resultadoG * 9))) / 4;

                        //Para mantenerse
                        resultadoKmantenimiento = (float) ((665.1 + (9.463 * peso) + (1.8 * altura) - (4.6756 * edad)) * 1.2);
                        resultadoPmantenimiento = (float) (peso * 1.7);
                        resultadoHmantenimiento = (float) (peso * 6.2);
                        resultadoGmantenimiento = (float) (peso * 0.5);

                        break;


                    //Actividad ligera
                    case LIGERA:
                        //Para aumentar masa muscular
                        resultadoK = (float) (((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.375) + 500);
                        resultadoP = (float) (peso * 2.4);
                        resultadoG = (float) (peso * 0.9);
                        resultadoH = ((resultadoK) - ((resultadoP * 4) + (resultadoG * 9))) / 4;

                        //Para mantenerse
                        resultadoKmantenimiento = (float) ((665.1 + (9.463 * peso) + (1.8 * altura) - (4.6756 * edad)) * 1.375);
                        resultadoPmantenimiento = (float) (peso * 1.7);
                        resultadoHmantenimiento = (float) (peso * 6.2);
                        resultadoGmantenimiento = (float) (peso * 0.5);

                        break;


                    //Actividad moderada
                    case MODERADA:
                        //Para aumentar masa muscular
                        resultadoK = (float) (((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.55) + 500);
                        resultadoP = (float) (peso * 2.4);
                        resultadoG = (float) (peso * 0.9);
                        resultadoH = ((resultadoK) - ((resultadoP * 4) + (resultadoG * 9))) / 4;

                        //Para mantenerse
                        resultadoKmantenimiento = (float) ((665.1 + (9.463 * peso) + (1.8 * altura) - (4.6756 * edad)) * 1.55);
                        resultadoPmantenimiento = (float) (peso * 1.7);
                        resultadoHmantenimiento = (float) (peso * 6.2);
                        resultadoGmantenimiento = (float) (peso * 0.5);

                        break;


                    //Actividad intensa
                    case INTENSA:
                        //Para aumentar masa muscular
                        resultadoK = (float) (((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.725) + 500);
                        resultadoP = (float) (peso * 2.4);
                        resultadoG = (float) (peso * 0.9);
                        resultadoH = ((resultadoK) - ((resultadoP * 4) + (resultadoG * 9))) / 4;

                        //Para mantenerse
                        resultadoKmantenimiento = (float) ((665.1 + (9.463 * peso) + (1.8 * altura) - (4.6756 * edad)) * 1.725);
                        resultadoPmantenimiento = (float) (peso * 1.7);
                        resultadoHmantenimiento = (float) (peso * 6.2);
                        resultadoGmantenimiento = (float) (peso * 0.5);

                        break;


                    //Actividad muy intensa
                    case MUYINTENSA:
                        //Para aumentar masa muscular
                        resultadoK = (float) (((66.473 + (13.752 * peso) + (5.0033 * altura) - (6.755 * edad)) * 1.9) + 500);
                        resultadoP = (float) (peso * 2.4);
                        resultadoG = (float) (peso * 0.9);
                        resultadoH = ((resultadoK) - ((resultadoP * 4) + (resultadoG * 9))) / 4;

                        //Para mantenerse
                        resultadoKmantenimiento = (float) ((665.1 + (9.463 * peso) + (1.8 * altura) - (4.6756 * edad)) * 1.9);
                        resultadoPmantenimiento = (float) (peso * 1.7);
                        resultadoHmantenimiento = (float) (peso * 6.2);
                        resultadoGmantenimiento = (float) (peso * 0.5);

                        break;
                }
                break;
        }
        return 0;
    }*/
}
