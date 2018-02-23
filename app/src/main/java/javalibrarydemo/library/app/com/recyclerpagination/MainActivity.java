package javalibrarydemo.library.app.com.recyclerpagination;

import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView rc1;
    RcAdapter rcAdapter;
    LinearLayoutManager layoutManager;
    boolean loading = false;
    int pageCount = 2;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rc1 = (RecyclerView)findViewById(R.id.rc);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        rc1.setLayoutManager(layoutManager);
        // set true if your RecyclerView is finite and has fixed size
        rc1.setHasFixedSize(false);
        handleView();
    }

    public void handleView(){

        list = new ArrayList<>();

        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");
        list.add("A");

        fetchData(pageCount);

        rc1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                int itemLastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                Toast.makeText(MainActivity.this, "Last Count : "+itemLastVisiblePosition, Toast.LENGTH_SHORT).show();

                if(itemLastVisiblePosition == rcAdapter.getItemCount() - 1){

                    if (!loading && itemLastVisiblePosition <= list.size()) {
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                            boolean canScrollDownMore = recyclerView.canScrollVertically(1);
                            if (!canScrollDownMore) {

                                fetchData((itemLastVisiblePosition + 1));

                                onScrolled(recyclerView, 0, 1);
                            }
                        }
                    }
                }
            }
        });

    }

    public void fetchData(int pageCount){

        Toast.makeText(MainActivity.this, "Count : "+pageCount , Toast.LENGTH_SHORT).show();

        rcAdapter = new RcAdapter(MainActivity.this, list, pageCount);
        rc1.setAdapter(rcAdapter);
        rcAdapter.notifyDataSetChanged();
    }
}
