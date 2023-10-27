package au.edu.federation.itech3107.fedunistudentattendance30395699;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class RegistryActivity extends AppCompatActivity {
    EditText et_teacherId,et_teacherPassword,et_teacherPassword2,et_teacherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        et_teacherId=findViewById(R.id.et_teacherId);
        et_teacherPassword=findViewById(R.id.et_teacherPassword);
        et_teacherPassword2=findViewById(R.id.et_teacherPassword2);
        et_teacherName=findViewById(R.id.et_teacherName);
        findViewById(R.id.btn_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Teacher> teachers = LitePal.where("teacherId=?", et_teacherId.getText().toString()).find(Teacher.class);
                if (teachers.size()>0){
                    Toast.makeText(RegistryActivity.this,"Teacher information already exists！",Toast.LENGTH_SHORT).show();
                }else {
                    if (et_teacherPassword.getText().toString().equals(et_teacherPassword2.getText().toString())){
                        Teacher teacher = new Teacher(et_teacherId.getText().toString(),et_teacherName.getText().toString(),et_teacherPassword.getText().toString());
                        teacher.save();
                        Toast.makeText(RegistryActivity.this,"Registration successful！",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegistryActivity.this,"Two different passwords！",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        findViewById(R.id.bnt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
