package edu.upc.dsa.andoroid_dsa.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.squareup.picasso.Picasso;

import java.util.List;

import edu.upc.dsa.andoroid_dsa.R;
import edu.upc.dsa.andoroid_dsa.models.Gadget;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView id,cost,description;
        ImageView fotoGadget;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.idGadget);
            cost=(TextView)itemView.findViewById(R.id.costGadget);
            description=(TextView)itemView.findViewById(R.id.descriptionGadget);
            fotoGadget=(ImageView) itemView.findViewById(R.id.imgGadget);
        }
    }
    public List<Gadget> gadgets;

    public RecyclerViewAdapter(List<Gadget> gadgets) {
        this.gadgets = gadgets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.provisional_tablegadgetitem,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(gadgets.get(position).getId());
        holder.description.setText(gadgets.get(position).getDescription());
        holder.cost.setText(Integer.toString(gadgets.get(position).getCost()));
        holder.fotoGadget.setImageResource(R.drawable.g);
    }

    @Override
    public int getItemCount() {
        return gadgets.size();
    }
}
