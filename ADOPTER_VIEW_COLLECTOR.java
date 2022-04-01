
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ADOPTER_VIEW_COLLECTOR extends RecyclerView.Adapter {




    String Name[] = { "ALI","BILAL","AHMED"};
    String Age[] = {"15","12","19"};
    //String PICS[] = {"ALI_PIC","BILAL_PIC","ahmed_PIC"} ;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_adopter,parent,false);

        return new VIEW_HOLDER_FOR_ADOPTER(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        ((VIEW_HOLDER_FOR_ADOPTER)holder).onBind_print(position);
    }
    @Override
    public int getItemCount()
    {
        return Name.length;
    }


    public class VIEW_HOLDER_FOR_ADOPTER extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        public ImageView adopter_image;
        public TextView adopter_name_text;
        public TextView adopter_age_text;


        public VIEW_HOLDER_FOR_ADOPTER(@NonNull View itemView) {
            super(itemView);

            adopter_image = itemView.findViewById(R.id.image_custom_x_adopter);
            adopter_name_text = itemView.findViewById(R.id.name_custom_x_adopter);
            adopter_age_text = itemView.findViewById(R.id.age_custom_x_adopter);
        }

        public void onBind_print(int position)
        {
            adopter_name_text.setText(Name[position]);
            adopter_age_text.setText(Age[position]);
            //name[postion] = name[1] = Bilal
        }

        @Override
        public void onClick(View view) {

        }
    }





//    public  class VIEW_HOLDER_FOR_ADOPTER extends RecyclerView.ViewHolder implements View.OnClickListener
//    {
//        public ImageView adopter_image;
//        public TextView adopter_name_text;
//        public TextView adopter_age_text;
//
//        public VIEW_HOLDER_FOR_ADOPTER(@NonNull View itemView) {
//            super(itemView);
//
//            adopter_image = itemView.findViewById(R.id.image_custom_x_adopter);
//            adopter_name_text = itemView.findViewById(R.id.name_custom_x_adopter);
//            adopter_age_text = itemView.findViewById(R.id.age_custom_x_adopter);
//        }
//
//        @Override
//        public void onClick(View view) {
//
//        }
//    }
}
