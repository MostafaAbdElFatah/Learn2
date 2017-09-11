package com.mostafaabdel_fatah.learn2;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ActionMode actionMode;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (actionMode != null){
                    return false;
                }else {
                    actionMode =  MainActivity.this.startActionMode(callback);
                    return true;
                }

            }
        });
        /********************************************************************************/
        constraintLayout = (ConstraintLayout) findViewById(R.id.layout);
        registerForContextMenu(constraintLayout);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.layoutmenu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                imageView.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.share:
                Toast.makeText(getBaseContext(),"Share item",Toast.LENGTH_SHORT).show();
                break;
            case R.id.help:
                Toast.makeText(getBaseContext(),"Help item",Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            getMenuInflater().inflate(R.menu.menu_items,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            actionMode.setTitle("title");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.delete:
                    imageView.setBackgroundColor(Color.DKGRAY);
                    break;
                case R.id.share:
                    Toast.makeText(getBaseContext(),"Share item",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.help:
                    Toast.makeText(getBaseContext(),"Help item",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    return false;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            actionMode = null;
        }
    };
}
