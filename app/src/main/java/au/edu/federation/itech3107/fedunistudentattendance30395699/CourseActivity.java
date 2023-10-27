package au.edu.federation.itech3107.fedunistudentattendance30395699;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CourseActivity extends AppCompatActivity {
    BoomMenuButton boomMenuButton;
    List<Course> courseList;
    MyAdapter myAdapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);



        boomMenuButton = (BoomMenuButton) findViewById(R.id.bmb);
        lv = findViewById(R.id.lv);
        courseList = LitePal.findAll(Course.class);
        myAdapter = new MyAdapter(CourseActivity.this, courseList);
        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(CourseActivity.this).setItems(new CharSequence[]{
                        "check Attendance",
                        "mark Attendance",
                        "delete course"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            startActivity(new Intent(CourseActivity.this, CheckActivity.class)
                                    .putExtra("course", courseList.get(position))
                                    .putExtra("type","check Attendance")

                            );
                        } else  if (which == 1) {
                            startActivity(new Intent(CourseActivity.this, CheckActivity.class)
                                    .putExtra("course", courseList.get(position))
                                    .putExtra("type","mark Attendance")
                            );
                        } else {
                            new AlertDialog.Builder(CourseActivity.this).setTitle("Excuse me.")  //设置标题
                                    .setIcon(R.mipmap.ic_launcher) //设置图标
                                    .setMessage("Are you sure to delete？") //提示信息
                                    .setNegativeButton("Cancel", null)   //添加“取消”按钮
                                    .setPositiveButton("Determine", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            LitePal.deleteAll(Course.class, "title=? ", courseList.get(position).getTitle());
                                            Toast.makeText(CourseActivity.this, "Deleted successfully！", Toast.LENGTH_SHORT).show();
                                            courseList.remove(position);
                                            myAdapter.notifyDataSetChanged();
                                        }
                                    }).show();  //创建对话框
                        }
                    }
                }).show();
            }
        });

        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {
                    switch (index) {
                        case 1:
                            finish();
                            break;
                        case 0:
                            View view2 = View.inflate(CourseActivity.this, R.layout.course_dialog, null);
                            final EditText et_courseName = view2.findViewById(R.id.et_courseName);
                            final EditText et_courseTeacher = view2.findViewById(R.id.et_courseTeacher);
                            final TextView tvDate = view2.findViewById(R.id.tv_date);
                            tvDate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Calendar calendar = Calendar.getInstance();
                                    int year = calendar.get(Calendar.YEAR);
                                    int month = calendar.get(Calendar.MONTH);
                                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                                    new DatePickerDialog(CourseActivity.this, new DatePickerDialog.OnDateSetListener() {
                                        @Override
                                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                            calendar.set(Calendar.YEAR, year);
                                            calendar.set(Calendar.MONTH, month);
                                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy E", Locale.US);
                                            List<String> dates = new ArrayList<>();
                                            for (int i = 0; i < 12; i++) {
                                                dates.add(simpleDateFormat.format(calendar.getTime()));
                                                calendar.add(Calendar.DAY_OF_WEEK, 7);
                                            }
                                            tvDate.setText(String.join(",", dates));
                                        }
                                    }, year, month, day).show();


                                }
                            });

                            AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this);
                            builder.setTitle("Input information").setIcon(android.R.drawable.ic_dialog_info).setView(view2).setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss()).setPositiveButton("Determine", (dialog, which) -> {
                                List<Course> courses = LitePal.where("title=?", et_courseName.getText().toString()).find(Course.class);
                                if (courses.size() > 0) {
                                    Toast.makeText(CourseActivity.this, "Course information already exists！", Toast.LENGTH_SHORT).show();
                                } else {
                                    Course course = new Course(et_courseName.getText().toString(), et_courseTeacher.getText().toString(), tvDate.getText().toString());
                                    course.save();
                                    Toast.makeText(CourseActivity.this, "success！", Toast.LENGTH_SHORT).show();
                                    courseList.add(course);
                                    myAdapter.notifyDataSetChanged();
                                }
                            });
                            builder.show();
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

    private static String[] text = new String[]{"Add", "Exit"

    };
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    private static int[] imageResources = new int[]{R.mipmap.tj, R.mipmap.tuichu};
}
