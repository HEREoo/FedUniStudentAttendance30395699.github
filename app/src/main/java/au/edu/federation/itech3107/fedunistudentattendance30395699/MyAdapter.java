package au.edu.federation.itech3107.fedunistudentattendance30395699;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

class MyAdapter extends BaseAdapter {

    Context context;
    boolean check;
    List<Course> courseList;

    public MyAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
        this.check = check;
    }


    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_item, null);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_teacher = view.findViewById(R.id.tv_teacher);
        TextView tv_time = view.findViewById(R.id.tv_time);

        tv_name.setText("Name:" + courseList.get(position).getTitle());
        tv_teacher.setText("Teacher Name:" + courseList.get(position).getTeacher());
        String date = courseList.get(position).getDate();
        String[] split = date.split(",");
        tv_time.setText("Date:from " + split[0] + " to " + split[split.length - 1] + " every " + split[0].split(" ")[3]);
        return view;
    }
}