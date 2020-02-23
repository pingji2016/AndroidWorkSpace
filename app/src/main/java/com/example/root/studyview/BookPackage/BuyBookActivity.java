package com.example.root.studyview.BookPackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.root.studyview.R;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class BuyBookActivity  extends AppCompatActivity {
    private static int count = 0;
    private static final String TAG = "BuyBookActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_book);
        Button buyBookBtn = (Button) findViewById(R.id.buyBookBtn);
        buyBookBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Connector.getDatabase();

                count ++;
                Book book = new Book();
                book.setAuthor("MO Yan");
                book.setName("Hewllo World");
                book.setPrice(16.2);
                book.save();
                book.setPrice(16 + count);
                book.save();
            }
        });

        Button updateBtn = (Button) findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setPress("XXXXX");
                book.setPrice(120);
                book.updateAll("name = ? and price = ?","Hewllo World", "16.2");

                book.updateAll("name = ? and price > ?","Hewllo World", "20");
            }
        });

        Button quaryBtn = (Button) findViewById(R.id.quaryBtn);
        quaryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book:books){
                    String content = "Name = " + book.getName();
                    content = content + " author = " + book.getAuthor();
                    content = content + " pages = " + book.getPages();
                    content = content + " Prices = " + book.getPrice();

                    Toast.makeText(BuyBookActivity.this, content, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onClick: "+ content);
                }
            }
        });

        Button delBtn = (Button) findViewById(R.id.delBtn);
        delBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(Book.class, "price = ?", "120");
            }
        });

    }
}
