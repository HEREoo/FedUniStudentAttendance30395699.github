package au.edu.federation.itech3107.fedunistudentattendance30395699;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import org.litepal.LitePal;

import java.util.List;

public class StudentActivity extends AppCompatActivity {
    BoomMenuButton boomMenuButton;
    ListView lv;
    List<Course> courseList;
    MyAdapter myAdapter;
    int flag,dateflag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        lv = findViewById(R.id.lv);
        courseList = LitePal.findAll(Course.class);
        myAdapter = new MyAdapter(StudentActivity.this,courseList);
        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(StudentActivity.this, "当前选择"+courseList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                flag=position;
            }
        });
        boomMenuButton = (BoomMenuButton) findViewById(R.id.bmb);
        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index) {
                                case 1:
                                    finish();
                                    break;
                                case 0:

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
    private static String [] text = new String[]{"添加","退出"

    };
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    private static int[] imageResources = new int[]{
            R.mipmap.tj,
            R.mipmap.tuichu

    };
}
