
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailViewOfTopic extends AppCompatActivity {

    TextView sub_topic_txt;
    TextView sub_topic_reason;
    TextView sub_topic_example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view_of_topic);


        sub_topic_example = findViewById(R.id.detail_example_detail);
        sub_topic_reason = findViewById(R.id.detail_reson_detail);
        sub_topic_txt = findViewById(R.id.detail_sub_cat_name);


        // to get the data from the recycler_view_fro_sub_cat

        Intent SUB_KEYS_DETAIL = getIntent();
        sub_topic_txt.setText(SUB_KEYS_DETAIL.getStringExtra("SUB_NAME"));
        sub_topic_reason.setText(SUB_KEYS_DETAIL.getStringExtra("SUB_REASON"));
        sub_topic_example.setText(SUB_KEYS_DETAIL.getStringExtra("SUB_EXAMPLE"));
    }
}
