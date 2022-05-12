package com.example.ibrahimr.movieum.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ibrahimr.movieum.ApiInterface;
import com.example.ibrahimr.movieum.Screens.CategoriesResult;
import com.example.ibrahimr.movieum.Model.MovieCredits;
import com.example.ibrahimr.movieum.Model.Movies;
import com.example.ibrahimr.movieum.R;
import com.example.ibrahimr.movieum.RetrofitBuilder;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by IbrahimR on 8/13/2020.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<String> movieTitle,movieCategories,movieRate,moviePoster;
    private Context context;
    private List<Movies> listOfMovies;
    private MovieCredits movieCredits = new MovieCredits();

    public MovieAdapter(Context context, List<String> movieTitle, List<String> movieCategories, List<String> moviePoster, List<String> movieRate, List<Movies> listOfMovies) {
        this.movieTitle = movieTitle;
        this.movieCategories = movieCategories;
        this.moviePoster = moviePoster;
        this.movieRate = movieRate;
        this.context = context;
        this.listOfMovies = listOfMovies;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(context)
                .load(moviePoster.get(position))
                .transform(new RoundedCornersTransformation(30,0))
                .into(holder.imageView);
        holder.textView1.setText(movieTitle.get(position));
        holder.textView2.setText(movieCategories.get(position));
        holder.textView3.setText(movieRate.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getMovieDetails(position);
            }
        });
    }

    private void getMovieDetails(final int position) {

        ApiInterface apiInterface = RetrofitBuilder.getRetrofitInstance(ApiInterface.BASE_URL).create(ApiInterface.class);
        Call<MovieCredits> call = apiInterface.getMovieCredits(listOfMovies.get(position).getId(),ApiInterface.API_KEY);
        call.enqueue(new Callback<MovieCredits>() {
            @Override
            public void onResponse(@NonNull Call<MovieCredits> call, @NonNull Response<MovieCredits> response) {
                if (response.isSuccessful()){
                    movieCredits = response.body();

                    goToResultActivity(position,movieCredits);
                }else{
                    Toast.makeText(context,"Response Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieCredits> call, @NonNull Throwable t) {
                Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goToResultActivity(int position, MovieCredits movieCredits) {

        Intent intent = new Intent(context, CategoriesResult.class);
        intent.putExtra("chosenMovie", listOfMovies.get(position));
        intent.putExtra("movieCredits", movieCredits);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return moviePoster.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.movieItemPoster);
            textView1 = itemView.findViewById(R.id.movieItemTitle);
            textView2 = itemView.findViewById(R.id.movieItemCategories);
            textView3 = itemView.findViewById(R.id.movieItemRate);
            parentLayout = itemView.findViewById(R.id.parentLayout);

        }
    }

}
