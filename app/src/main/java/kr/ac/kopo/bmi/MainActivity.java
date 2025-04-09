package kr.ac.kopo.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editName, editWeight, editHeight;
    Button btnCalculate;
    TextView textResult;
    ImageView imageBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.edit_name);
        editWeight = findViewById(R.id.edit_weight);
        editHeight = findViewById(R.id.edit_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        textResult = findViewById(R.id.text_result);
        imageBmi = findViewById(R.id.image_bmi);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String weightStr = editWeight.getText().toString();
                String heightStr = editHeight.getText().toString();

                // 개별 체크
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (weightStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "체중을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (heightStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "키를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr) / 100.0;

                double bmi = weight / (height * height);
                String resultText = name + "님의 체중은 " + weight + "kg, 키는 " + height + "cm 이므로\n"
                        + "BMI 지수는 " + String.format("%.2f",bmi) + " kg/m² 입니다.\n";

                if (bmi < 18.5) {
                    resultText += "저체중입니다.";
                    imageBmi.setImageResource(R.drawable.underweight);
                } else if (bmi < 23) {
                    resultText += "정상 체중입니다.";
                    imageBmi.setImageResource(R.drawable.normal);
                } else if (bmi < 25) {
                    resultText += "과체중입니다.";
                    imageBmi.setImageResource(R.drawable.overweight);
                } else if (bmi < 30) {
                    resultText += "비만입니다.";
                    imageBmi.setImageResource(R.drawable.obese);
                } else {
                    resultText += "고도비만입니다.";
                    imageBmi.setImageResource(R.drawable.severe_obese);
                }

                textResult.setText(resultText);
            }
        });
    }
}
