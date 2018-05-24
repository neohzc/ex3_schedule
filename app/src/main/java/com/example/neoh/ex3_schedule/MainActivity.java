package com.example.neoh.ex3_schedule;

import android.app.TabActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements Callback {

    private static int maxSizeOfCourses = 42;
    Context TAG = this;

    private SQLiteDatabase db;
    GridView gridview;


    Course[] list = new Course[maxSizeOfCourses];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        InitListItem();
        InitDataFromDB();

        gridview = (GridView) findViewById(R.id.gridview);
        CourseAdapter adapter = new CourseAdapter(this, R.layout.griview_item, list);
        //adapter.notifyDataSetChanged();
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showACDoalog(position);
            }
        });

    }


    /*data opt*/
    private void InitDataFromDB() {

        db = openOrCreateDatabase(DBinfo._DBNAME, Context.MODE_PRIVATE, null);
        int version = db.getVersion();
        if (version < 1) {
            db.execSQL("CREATE TABLE " + DBinfo._TABLENAME + " ( " +
                    DBinfo._NO + " text PRIMARY KEY ," +
                    DBinfo._NAME + " text ," +
                    DBinfo._ROME + " text ," +
                    DBinfo._TEACHER + " text )"
            );
            db.setVersion(1);
        }
        getAllData();
    }

    private void getAllData() {
        Cursor cursor = db.query(DBinfo._TABLENAME, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(DBinfo._NO));
                list[id].setCourseName(cursor.getString(cursor.getColumnIndex(DBinfo._NAME)));
                list[id].setClassRoom(cursor.getString(cursor.getColumnIndex(DBinfo._ROME)));
                list[id].setTeacher(cursor.getString(cursor.getColumnIndex(DBinfo._TEACHER)));
            }
        }
    }

    private void updateData(String id, String course, String classroom, String teacher) {
        Cursor cursor = db.rawQuery(
                "select * from " + DBinfo._TABLENAME + "    where   id=? ",
                new String[]{id});

        if (cursor.getCount() != 0) {
            //int iId= Integer.valueOf(id);
            db.execSQL("update " + DBinfo._TABLENAME + " set " + DBinfo._NAME + "=?, " + DBinfo._ROME + "=?," + DBinfo._TEACHER + "=? where " + DBinfo._NO + "=?",
                    new Object[]{course, classroom, teacher, id});
            Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

        } else {
            db.execSQL("insert into " + DBinfo._TABLENAME + " ( " +
                            DBinfo._NO + " ," +
                            DBinfo._NAME + "  ," +
                            DBinfo._ROME + "  ," +
                            DBinfo._TEACHER + " ) values (?,?,?,?)", new Object[]{
                            id, course, classroom, teacher
                    }
            );
            Toast.makeText(MainActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
        }


    }



    /* */
    public void showACDoalog(int position) {
        ACDialog acDialog = new ACDialog(MainActivity.this, position);
        acDialog.callback = new Callback() {
            @Override
            public void onClick(String Course, String Classroom, String Teacher, int id) {
                //Toast.makeText(MainActivity.this,"ck",Toast.LENGTH_SHORT).show();
                /*core*/
                updateData(String.valueOf(id), Course, Classroom, Teacher);

                getAllData();

                CourseAdapter adapter = new CourseAdapter(TAG, R.layout.griview_item, list);
                gridview.setAdapter(adapter);
            }
        };
        acDialog.show();
    }



    @Override
    public void onClick(String Course, String Classroom, String Teacher, int id) {
        Toast.makeText(MainActivity.this, "ck", Toast.LENGTH_SHORT).show();
    }

    private void InitListItem() {
        for (int i = 0; i < maxSizeOfCourses; i++) {
            list[i] = new Course("", "", "", i);
        }

    }

}
