package uz.alexits.cargostar.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import uz.alexits.cargostar.R;

public class CreateParcelHeadingViewHolder extends RecyclerView.ViewHolder {
    public TextView headingTextView;

    public CreateParcelHeadingViewHolder(@NonNull View itemView) {
        super(itemView);
        headingTextView = itemView.findViewById(R.id.heading_text_view);
    }
}