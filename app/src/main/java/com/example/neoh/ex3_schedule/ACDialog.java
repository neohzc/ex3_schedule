package com.example.neoh.ex3_schedule;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class ACDialog extends AlertDialog implements OnClickListener {

    public Callback callback = null;

    private Context TAG;
    private int id;
    private EditText etCourse, etClassroom, etTeacher;  //编辑框
    private Button btnConfrm, btnCancel;  //确定取消按钮


    public ACDialog(Context context,int id) {
        super(context);
        TAG = context;
        this.id = id;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        //控件
        etCourse = (EditText) findViewById(R.id.edCourseNameID);
        etClassroom = (EditText) findViewById(R.id.edClassroomID);
        etTeacher = (EditText) findViewById(R.id.edTeacherID);
        btnConfrm = (Button) findViewById(R.id.btnConfrm);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnConfrm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnConfrm) {
            //确定
            if (etCourse.getText().toString() != null) {
                callback.onClick(etCourse.getText().toString(), etClassroom.getText().toString(), etTeacher.getText().toString(),id);
                dismiss();
            } else
                Toast.makeText(TAG, "至少输入课程名称", Toast.LENGTH_SHORT).show();
        } else {
            //取消
            dismiss();
        }
    }


}
