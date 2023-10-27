package au.edu.federation.itech3107.fedunistudentattendance30395699;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText et_teacherId,et_teacherPassword,et_teacherPassword2,et_teacherName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_teacherId=findViewById(R.id.et_teacherId);
        et_teacherPassword=findViewById(R.id.et_teacherPassword);
        et_teacherPassword2=findViewById(R.id.et_teacherPassword2);
        et_teacherName=findViewById(R.id.et_teacherName);

        findViewById(R.id.btn_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Teacher> teachers = LitePal.where("teacherId=? and teacherPassword=?", et_teacherId.getText().toString(),
                        et_teacherPassword.getText().toString()).find(Teacher.class);
                if (teachers.size() > 0) {
                    Intent intent = new Intent(LoginActivity.this, CourseActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Account or password error！", Toast.LENGTH_SHORT).show();
                }
            }
        });


        findViewById(R.id.btn_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegistryActivity.class);
                startActivity(intent);
            }
        });
    }
}
