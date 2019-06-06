//package com.example.petbook;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//public class MainActivity extends AppCompatActivity {
//    String participants[] = new String[100];
//    StringBuffer sb;
//    int version = 1;
//    int count = 0;
//    MyDatabaseOpenHelper helper;
//    SQLiteDatabase database;
//
//    Button btn_add;
//    Button btn_winner;
//    EditText et_name;
//    TextView tv_people;
//
//    String sql;
//    Cursor cursor;
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        setUp();
//        nameList();
//    }
//
//    private void setUp()
//    {
//        btn_add = (Button)findViewById(R.id.btn_add_people);
//        btn_winner = (Button)findViewById(R.id.btn_winner);
//        et_name = (EditText)findViewById(R.id.et_name);
//        tv_people = (TextView)findViewById(R.id.tv_people);
//        btn_add.setOnClickListener(myListener);
//        btn_winner.setOnClickListener(myListener);
//        helper = new MyDatabaseOpenHelper(MainActivity.this, MyDatabaseOpenHelper.tableName, null, version);
//        database = helper.getWritableDatabase();
//        sb = new StringBuffer();
//    }
//
//    View.OnClickListener myListener = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//            switch (v.getId())
//            {
//                case R.id.btn_add_people :
//                    sb.setLength(0);
//                    helper.insertName(database,(et_name.getText().toString()) );
//                    nameList();
//                    break;
//
//                case R.id.btn_winner :
//                    //랜덤 뽑기
//                    break;
//            }
//        }
//    };
//
//    private void nameList()
//    {
//        sql = "select name from " + helper.tableName;
//        cursor = database.rawQuery(sql, null);
//        if (cursor != null)
//        {
//            count = cursor.getCount();
//            for (int i = 0; i < count; i++)
//            {
//                cursor.moveToNext();
//                String participant = cursor.getString(0);
//                participants[i] = participant;
//                sb.append(participants[i] + " ");
//            }
//            tv_people.setText("" + sb);
//            cursor.close();
//        }
//    }
//}
//
//
//
//
