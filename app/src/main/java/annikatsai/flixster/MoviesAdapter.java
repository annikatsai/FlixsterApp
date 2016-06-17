package annikatsai.flixster;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MoviesAdapter extends ArrayAdapter<Movie> {

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
        //super(context, 0, movies);
    }

    @Override
    public int getItemViewType(int position) {
        Movie movie = getItem(position);
        if (movie.rating <= 5) {
            return Movie.MovieRatings.BELOWFIVESTARS.ordinal();
        }
        else {
            return Movie.MovieRatings.ABOVEFIVESTARS.ordinal();
        }
    }

    @Override
    public int getViewTypeCount() {
        return Movie.MovieRatings.values().length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        int type = getItemViewType(position);
        ViewHolder mHolder;
        if (convertView == null) {
            convertView = getInflatedLayoutForType(type);

            mHolder =  new ViewHolder();
            mHolder.mTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            mHolder.mOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            mHolder.mPoster = (ImageView) convertView.findViewById(R.id.ivPoster);

            convertView.setTag(mHolder);
        }
        else
            mHolder = (ViewHolder) convertView.getTag();

        boolean isLandscape = getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape){
            Picasso.with(getContext()).load(movie.getBackdropPath()).placeholder(R.mipmap.ic_launcher).transform(new RoundedCornersTransformation(10, 10)).into(mHolder.mPoster);
        } else {
            Picasso.with(getContext()).load(movie.getPosterPath()).placeholder(R.mipmap.ic_launcher).transform(new RoundedCornersTransformation(10, 10)).into(mHolder.mPoster);

        }

        mHolder.mTitle.setText(movie.getTitle());
        mHolder.mOverview.setText(movie.getOverview());

        return convertView;
    }

    public View getInflatedLayoutForType(int type) {

        if (type == Movie.MovieRatings.BELOWFIVESTARS.ordinal()) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie, null);
        }
        else if (type == Movie.MovieRatings.ABOVEFIVESTARS.ordinal()) {
            return LayoutInflater.from(getContext()).inflate(R.layout.item_movie2, null);
        }
        else
            return null;
    }

    private static class ViewHolder {
        private TextView mTitle;
        private TextView mOverview;
        private ImageView mPoster;
    }

}
