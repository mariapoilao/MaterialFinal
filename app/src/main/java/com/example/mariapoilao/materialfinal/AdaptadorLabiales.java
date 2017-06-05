package com.example.mariapoilao.materialfinal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by mariapoilao on 4/06/17.
 */

public class AdaptadorLabiales extends RecyclerView.Adapter<AdaptadorLabiales.LabialViewHolder> {

    private ArrayList<Labial> labiales;
    private OnLabialClickListener clickListener;

    public AdaptadorLabiales(ArrayList<Labial> labiales, OnLabialClickListener clickListener) {
        this.labiales = labiales;
        this.clickListener = clickListener;
    }

    @Override
    public AdaptadorLabiales.LabialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_lips, parent, false);
        return new LabialViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorLabiales.LabialViewHolder holder, int position) {
        final Labial p = labiales.get(position);
        //holder.foto.setImageResource(Integer.parseInt(p.getUrlfoto()));
        Picasso.with(holder.view.getContext()).load(p.getUrlfoto()).into(holder.foto);

        holder.idlabial.setText(p.getIdlabial());
        holder.marca.setText(p.getMarca());
        holder.precio.setText(p.getPrecio());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onLabialClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return labiales.size();
    }

    public static class LabialViewHolder extends RecyclerView.ViewHolder {
        private ImageView foto;
        private TextView idlabial;
        private TextView marca;
        private TextView precio;
        private View view;

        public LabialViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            foto = (ImageView) itemView.findViewById(R.id.foto);
            idlabial = (TextView) itemView.findViewById(R.id.txtIdLabial);
            marca = (TextView) itemView.findViewById(R.id.txtMarcaLabial);
            precio = (TextView) itemView.findViewById(R.id.txtPrecioLabial);
        }
    }

    public interface OnLabialClickListener {
        void onLabialClick(Labial p);
    }
}