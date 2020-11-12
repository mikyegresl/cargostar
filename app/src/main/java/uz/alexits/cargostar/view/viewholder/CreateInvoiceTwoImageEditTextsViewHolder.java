package uz.alexits.cargostar.view.viewholder;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import uz.alexits.cargostar.R;
import uz.alexits.cargostar.view.callback.CreateInvoiceCallback;

public class CreateInvoiceTwoImageEditTextsViewHolder extends RecyclerView.ViewHolder {
    public TextView firstTextView;
    public TextView secondTextView;
    public RelativeLayout firstField;
    public RelativeLayout secondField;
    public EditText firstEditText;
    public EditText secondEditText;
    public ImageView firstImageView;
    public ImageView secondImageView;
    public ImageView firstResultImageView;
    public ImageView secondResultImageView;

    private TextWatcher textWatcher;

    public CreateInvoiceTwoImageEditTextsViewHolder(@NonNull View itemView) {
        super(itemView);
        firstTextView = itemView.findViewById(R.id.first_text_view);
        secondTextView = itemView.findViewById(R.id.second_text_view);
        firstField = itemView.findViewById(R.id.first_field);
        secondField = itemView.findViewById(R.id.second_field);
        firstEditText = itemView.findViewById(R.id.first_edit_text);
        secondEditText = itemView.findViewById(R.id.second_edit_text);
        firstImageView = itemView.findViewById(R.id.first_image_view);
        secondImageView = itemView.findViewById(R.id.second_image_view);
        firstResultImageView = itemView.findViewById(R.id.first_result_image_view);
        secondResultImageView = itemView.findViewById(R.id.second_result_image_view);
    }

    public void bindImageViews(final CreateInvoiceCallback callback) {
        firstImageView.setOnClickListener(v -> {
            callback.onSenderSignatureClicked();
        });

        secondImageView.setOnClickListener(v -> {
            callback.onRecipientSignatureClicked();
        });
    }

    public void bindWatchers(final int position, final CreateInvoiceCallback callback) {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                callback.afterSecondEditTextChanged(position, editable);
            }
        };
        firstEditText.addTextChangedListener(textWatcher);
        secondEditText.addTextChangedListener(textWatcher);
    }

    public void unbindWatchers() {
        firstEditText.removeTextChangedListener(textWatcher);
        secondEditText.removeTextChangedListener(textWatcher);
    }

    public void unbindImageViews() {
        firstImageView.setOnClickListener(null);
        secondImageView.setOnClickListener(null);
    }
}
