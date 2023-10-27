package au.edu.federation.itech3107.fedunistudentattendance30395699;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import org.litepal.LitePal;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    BoomMenuButton boomMenuButton;
    Intent intent;

    CheckStudentAdapter checkStudentAdapter;
    RecyclerView over_student;
    List<CheckTable> checkList;
    Spinner sp_courseDate;
    int flag;
    String cflag = "";
    EditText et_studentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boomMenuButton = (BoomMenuButton) findViewById(R.id.bmb);
        sp_courseDate = findViewById(R.id.sp_courseDate);
        et_studentClass = findViewById(R.id.et_studentClass);

        over_student = findViewById(R.id.over_student);








        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index) {
                                case 0:
                                    intent = new Intent(MainActivity.this, CourseActivity.class);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    intent = new Intent(MainActivity.this, StudentActivity.class);
                                    startActivity(intent);
                                    break;
                                case 2:
                                    intent = new Intent(MainActivity.this, CheckActivity.class);
                                    startActivity(intent);
                                    break;
                            }
                        }
                    })
                    .normalImageRes(getImageResource())
                    .normalText(getext());
            boomMenuButton.addBuilder(builder);
        }

    }

    private static int index = 0;

    static String getext() {
        if (index >= text.length) index = 0;
        return text[index++];

    }

    private static String[] text = new String[]{"课程", "学生", "出勤"

    };
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    private static int[] imageResources = new int[]{
            R.mipmap.ma1,
            R.mipmap.ma2,
            R.mipmap.ma3

    };

}
