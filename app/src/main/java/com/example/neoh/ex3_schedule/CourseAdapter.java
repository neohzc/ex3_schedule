package com.example.neoh.ex3_schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CourseAdapter extends ArrayAdapter<Course> {

    private LayoutInflater layoutInflater;

    private class ViewHolder {
        TextView ScourseName;
        TextView SclassRoom;
        TextView Steacher;
        //TextView Stime;

    }

    public CourseAdapter(Context context, int textViewResourceId, Course[] list) {
        super(context, textViewResourceId, list);
        this.layoutInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //create new view
            convertView = layoutInflater.inflate(R.layout.griview_item, null);
        }
        ViewHolder holder = null;

        if(holder == null){
            holder = new ViewHolder();
            holder.ScourseName = (TextView)convertView.findViewById(R.id.CourseName);
            holder.SclassRoom = (TextView)convertView.findViewById(R.id.Classroom);
            holder.Steacher = (TextView)convertView.findViewById(R.id.Teacher);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        Course course = getItem(position);
        holder.ScourseName.setText(course.getCourseName());
        holder.SclassRoom.setText(course.getClassRoom());
        holder.Steacher.setText(course.getTeacher());

        return convertView;
    }


}
