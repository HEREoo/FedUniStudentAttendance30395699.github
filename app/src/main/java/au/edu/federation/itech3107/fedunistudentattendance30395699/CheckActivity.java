package au.edu.federation.itech3107.fedunistudentattendance30395699;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import org.litepal.LitePal;

import java.io.Serializable;
import java.util.List;

public class CheckActivity extends AppCompatActivity {
    BoomMenuButton boomMenuButton;
    RecyclerView over_student;
    List<Student> checkList;
    CheckStudentAdapter checkStudentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        String type = getIntent().getStringExtra("type");
        setTitle(type);

        Course course = (Course) getIntent().getSerializableExtra("course");
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, course.getDate().split(",")));
        boomMenuButton = (BoomMenuButton) findViewById(R.id.bmb);
        over_student = findViewById(R.id.over_student);
        checkList = LitePal.where("course = ?", course.getTitle()).find(Student.class);
        for (Student student : checkList) {
            student.setDate((String) spinner.getSelectedItem());
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                for (Student student : checkList) {
                    student.setDate((String) spinner.getSelectedItem());
                }
                checkStudentAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        checkStudentAdapter = new CheckStudentAdapter(checkList, CheckActivity.this,type.contains("check"));
        over_student.setLayoutManager(new LinearLayoutManager(CheckActivity.this, RecyclerView.VERTICAL, false));
        over_student.setAdapter(checkStudentAdapter);
        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    switch (index) {
                        case 0:
                            View view2 = View.inflate(CheckActivity.this, R.layout.student_dialog, null);
                            final EditText et_studentId = (EditText) view2.findViewById(R.id.et_studentId);
                            final EditText et_studentName = (EditText) view2.findViewById(R.id.et_studentName);
                            final EditText et_studentClass = (EditText) view2.findViewById(R.id.et_studentClass);

                            AlertDialog.Builder builder = new AlertDialog.Builder(CheckActivity.this);
                            builder.setTitle("输入学生信息").setIcon(android.R.drawable.ic_dialog_info).setView(view2)
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });

                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Student studentCourse = new Student(et_studentId.getText().toString(), et_studentName.getText().toString(), course.getTitle());
                                    studentCourse.save();
                                    Toast.makeText(CheckActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                                    checkList.clear();
                                    checkList.addAll(LitePal.where("course = ?", course.getTitle()).find(Student.class));
                                    for (Student student : checkList) {
                                        student.setDate((String) spinner.getSelectedItem());
                                    }
                                    checkStudentAdapter.notifyDataSetChanged();
                                }
                            });
                            builder.show();
                            break;
                        case 1:

                            finish();
                            break;

                    }
                }
            }).normalImageRes(getImageResource()).normalText(getext());
            boomMenuButton.addBuilder(builder);
        }
    }


    private static int index = 0;

    static String getext() {
        if (index >= text.length) index = 0;
        return text[index++];

    }

    private static String[] text = new String[]{"add student", "exit"};
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    private static int[] imageResources = new int[]{R.mipmap.baocun, R.mipmap.tuichu

    };
}
