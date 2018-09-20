package umbandung.com.digitalhomecare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arkhan on 7/24/2018.
 */

public class ListLayanan extends Fragment {

    private List<LayananUtil> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdapterLayanan mAdapter;

    public ListLayanan(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mAdapter = new AdapterLayanan(movieList);

//      prepareMovieData();

        View view = inflater.inflate(R.layout.list_layanan, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        return recyclerView;
//        return inflater.inflate(R.layout.list_layanan, container, false);
    }


//    private void prepareMovieData() {
//        LayananUtil movie = new LayananUtil("Mad Max: Fury Road", "Action & Adventure", "2015");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Inside Out", "Animation, Kids & Family", "2015");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Shaun the Sheep", "Animation", "2015");
//        movieList.add(movie);
//
//        movie = new LayananUtil("The Martian", "Science Fiction & Fantasy", "2015");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Mission: Impossible Rogue Nation", "Action", "2015");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Up", "Animation", "2009");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Star Trek", "Science Fiction", "2009");
//        movieList.add(movie);
//
//        movie = new LayananUtil("The LEGO Movie", "Animation", "2014");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Iron Man", "Action & Adventure", "2008");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Aliens", "Science Fiction", "1986");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Chicken Run", "Animation", "2000");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Back to the Future", "Science Fiction", "1985");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Raiders of the Lost Ark", "Action & Adventure", "1981");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Goldfinger", "Action & Adventure", "1965");
//        movieList.add(movie);
//
//        movie = new LayananUtil("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
//        movieList.add(movie);
//
//        mAdapter.notifyDataSetChanged();
//    }
}
