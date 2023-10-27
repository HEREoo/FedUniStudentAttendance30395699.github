package au.edu.federation.itech3107.fedunistudentattendance30395699;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.LitePal;

import java.util.List;

public class CheckStudentAdapter extends RecyclerView.Adapter<CheckStudentAdapter.ViewHolder> {
    List<Student> checkList;
    Context context;
    boolean isCheck;

    public List<Student> getChecks() {
        return checkList;
    }

    public void setChecks(List<Student> Checks,boolean check) {
        this.checkList = Checks;
    }

    public CheckStudentAdapter(List<Student> Checks, Context context, boolean isCheck) {
        this.checkList = Checks;
        this.context = context;
        this.isCheck = isCheck;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.check_student_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Student student = checkList.get(position);
        holder.sname.setText("姓名:" + student.getName());
        List<CheckTable> checkTables = LitePal.where("title = ? and name = ? and date = ?", student.getCourse(), student.getName(), student.getDate())
                .find(CheckTable.class);
        Log.d("TAG", "onBindViewHolder: "+checkTables.size()+"");
        holder.cb.setEnabled(!isCheck);
        holder.cb.setOnCheckedChangeListener(null);
        holder.cb.setChecked(!checkTables.isEmpty());

        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CheckTable checkTable = new CheckTable(student.getCourse(), student.getName(), student.getDate());
                    checkTable.save();
                } else {
                    LitePal.deleteAll(CheckTable.class, "title = ? and name = ? and date = ?", student.getCourse(), student.getName(), student.getDate());
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return checkList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sname;
        TextView sid;
        CheckBox cb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sname = itemView.findViewById(R.id.sname);
            cb = itemView.findViewById(R.id.cb);
        }
    }
}
