package annikatsai.flixster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        String title = getIntent().getStringExtra("title");
        String overview = getIntent().getStringExtra("overview");
        String posterPath = getIntent().getStringExtra("posterPath");

        String rating = getIntent().getStringExtra("rating");
        String popularity = getIntent().getStringExtra("popularity");

        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvDescription.setMovementMethod(new ScrollingMovementMethod());
        TextView tvRating = (TextView) findViewById(R.id.tvRating);
        TextView tvPopularity = (TextView) findViewById(R.id.tvPopularity);

        Double ratingNumber = getIntent().getDoubleExtra("ratingNumber", 0);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.rbratingBar);
        Float finalRating = ratingNumber.floatValue();
        Double step = 0.01;
        Float stepSize = step.floatValue();
        ratingBar.setStepSize(stepSize);
        ratingBar.setRating(finalRating);

        tvTitle.setText(title);
        tvDescription.setText(overview);
        tvRating.setText(rating);
        tvPopularity.setText(popularity);

        String poster = String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        Picasso.with(this).load(poster).placeholder(R.mipmap.ic_launcher).transform(new RoundedCornersTransformation(10, 10)).into(ivImage);
    }

    public void onSubmit(View v) {
        this.finish();
    }

}
