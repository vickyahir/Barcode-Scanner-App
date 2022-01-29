package com.example.qrcodebarcodescanner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.BarcodeModel;

import java.util.List;


public class BarcodeAdapter extends RecyclerView.Adapter<BarcodeAdapter.Myviewholder> {
    Context context;
    List<BarcodeModel> muscles;
    private TableLayout table;
    private Object BarcodeModel;


    public BarcodeAdapter(Activity activity, List<BarcodeModel> muscles) {
        this.context = activity;
        this.muscles = muscles;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Myviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder holder, @SuppressLint("RecyclerView") final int position) {
        final BarcodeModel bean = muscles.get(position);
        table = new TableLayout(context);


        LoadTableData(2, 5, holder.TableLayout, bean);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeletePopup(muscles, position);
            }
        });


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (bean.getItem_type().equalsIgnoreCase("order")) {
//                    Intent intent = new Intent(context, OrderDetailsActivity.class);
//                    intent.putExtra("orderId", String.valueOf(bean.getItem_type_id()));
//                    context.startActivity(intent);
//                    Functions.animNext(context);
//                }
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return muscles.size();
    }


    public class Myviewholder extends RecyclerView.ViewHolder {

        private TextView tvTicketSubject;
        private ImageView imgDelete;
        private LinearLayout TableLayout;


        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tvTicketSubject = itemView.findViewById(R.id.tvTicketSubject);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            TableLayout = itemView.findViewById(R.id.TableLayout);


        }
    }

    private void LoadTableData(int Columnumber, int rownumber, LinearLayout tableLayout, BarcodeModel barcodeModel) {

        table.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        table.setShrinkAllColumns(true);
        table.setStretchAllColumns(true);
        table.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        table.setBackgroundColor(context.getResources().getColor(R.color.gray));


        try {

            for (int i = 0; i < rownumber; i++) {
                TableRow row = new TableRow(context);
                row.setBackgroundColor(context.getResources().getColor(R.color.white));

                TableLayout.LayoutParams tableRowParams =
                        new TableLayout.LayoutParams
                                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                int leftMargin = 1;
                int topMargin = 1;
                int rightMargin = 1;
                int bottomMargin = 1;
                tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

                row.setLayoutParams(tableRowParams);

                for (int j = 0; j < Columnumber; j++) {
                    TextView tv = new TextView(context);
//                    tv.setBackgroundColor(getResources().getColor(R.color.white));
                    tv.setPadding(8, 10, 8, 10);
                    tv.setTextColor(context.getResources().getColor(R.color.black));
                    tv.setGravity(Gravity.CENTER);

                    if (j == 0) {
                        if (i == 0) {
                            tv.setText("Index Number");
                        } else if (i == 1) {
                            tv.setText("Part Number");
                        } else if (i == 2) {
                            tv.setText("Unique Number");
                        } else if (i == 3) {
                            tv.setText("Qty");
                        } else if (i == 4) {
                            tv.setText("MRP");
                        }
                    } else {
                        if (i == 0) {
                            tv.setText(barcodeModel.getIndexNumber());
                        } else if (i == 1) {
                            tv.setText(barcodeModel.getProdNumber());
                        } else if (i == 2) {
                            tv.setText(barcodeModel.getUnicNumber());
                        } else if (i == 3) {
                            tv.setText(barcodeModel.getQty());
                        } else if (i == 4) {
                            tv.setText(barcodeModel.getMrp());
                        }
                    }


                    if (j == 0) {
                        tv.setTextSize(10);
                        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
                        Typeface typeface = ResourcesCompat.getFont(context, R.font.montserrat_medium);
                        tv.setTypeface(typeface);
                    } else {
                        tv.setTextSize(10);
                        tv.setTypeface(tv.getTypeface(), Typeface.NORMAL);
                        Typeface typeface = ResourcesCompat.getFont(context, R.font.montserrat_regular);
                        tv.setTypeface(typeface);
                    }
                    row.addView(tv);
                }
                table.addView(row);
            }


            tableLayout.addView(table);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tableLayout.setVisibility(View.VISIBLE);

    }


    public void showDeletePopup(List<com.example.model.BarcodeModel> muscles, int position) {
        final Dialog builder = new Dialog(context);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view1 = LayoutInflater.from(context).inflate(R.layout.logout_dialog, null);

        TextView tvDialogok = (TextView) view1.findViewById(R.id.tvDialogok);
        TextView tvDialogCancel = (TextView) view1.findViewById(R.id.tvDialogCancel);
        TextView tvDialogMessage = (TextView) view1.findViewById(R.id.tvDialogMessage);

        tvDialogok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
                muscles.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Record deleted Successfully.", Toast.LENGTH_SHORT).show();
                ((MainActivity) context).LoadData();
            }
        });


        tvDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });


        builder.setCancelable(false);
        builder.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.dialog_round));
        // builder.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        builder.setContentView(view1);
        builder.show();


    }


}
