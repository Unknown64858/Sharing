package com.ammar.sharing.activities.AddFilesActivity2;

import static com.ammar.sharing.activities.MainActivity.MainActivity.darkMode;

import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ammar.sharing.R;
import com.ammar.sharing.activities.AddFilesActivity2.adaptersR.FilesViewerAdapter.FilesViewerAdapter;
import com.ammar.sharing.common.Utils;

public class AddFilesActivity2 extends AppCompatActivity {
    private Toolbar toolbar;
    public LinearLayout directoryEmptyLL;
    private int spanCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_files_2);
        initItems();
    }

    private void initTheme() {
        if (darkMode) {
            setTheme(R.style.AppThemeDark);
            getWindow().setBackgroundDrawableResource(R.drawable.gradient_background_dark);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            setTheme(R.style.AppTheme);
            getWindow().setBackgroundDrawableResource(R.drawable.gradient_background_light);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


    private void initItems() {
        toolbar = findViewById(R.id.TB_AddFilesActivityToolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.internal_storage);

        directoryEmptyLL = findViewById(R.id.LL_DirectoryEmpty);

        RecyclerView recyclerView = findViewById(R.id.RC_FilesRecyclerView);
        recyclerView.post(() -> {
            setSpanCount(recyclerView.getMeasuredWidth() / (int) Utils.dpToPx(150));
            recyclerView.setLayoutManager(new GridLayoutManager(this, getSpanCount()));
            recyclerView.setAdapter(new FilesViewerAdapter(this, recyclerView));
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_files, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.MI_SearchFiles).getActionView();
        return super.onCreateOptionsMenu(menu);
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        if( spanCount == 0 ) spanCount = 1;
        this.spanCount = spanCount;
    }
}