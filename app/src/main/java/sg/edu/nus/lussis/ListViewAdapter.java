package sg.edu.nus.lussis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sg.edu.nus.lussis.Model.Requisition;

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Requisition> requisitionlList;
    private ArrayList<Requisition> arrayList;

    public ListViewAdapter(Context context, List<Requisition> requisitionlList) {
        mContext = context;
        this.requisitionlList = requisitionlList;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Requisition>();
        this.arrayList.addAll(requisitionlList);
    }

    public class ViewHolder{
        TextView mIdTv, mDateTv, mStatusTv;
    }

    @Override
    public int getCount() {
        return requisitionlList.size();
    }

    @Override
    public Object getItem(int position) {
        return requisitionlList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
 //       if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.my_requisitions_list_content, null);

            //locate the views in row.xml
            holder.mIdTv = view.findViewById(R.id.requisition_order);
            holder.mDateTv = view.findViewById(R.id.requisition_date);
            holder.mStatusTv = view.findViewById(R.id.requisition_status);

            view.setTag(holder);

//        }
//        else {
//            holder = (ViewHolder)view.getTag();
//        }

        //set the results into textViews
        holder.mIdTv.setText(requisitionlList.get(position).getId());
        holder.mDateTv.setText(requisitionlList.get(position).getDate());
        holder.mStatusTv.setText(requisitionlList.get(position).getStatus());

        //listView item clicks
        view.setTag(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code later
                Intent intent = new Intent(mContext, RequisitionDetailsActivity.class);
                Intent jsonObj = ((MyRequisitionsActivity)view.getContext()).getIntent();

//                intent.putExtra("requisitionList", jsonObj.toString());
//                intent.putExtra("requisitionDetail", requisitionlList.get((Integer)view.getTag()).getRequisitionDetail().toString());

                mContext.startActivity(intent);
            }
        });

        return view;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        requisitionlList.clear();
        if (charText.length()==0){
            requisitionlList.addAll(arrayList);
        }
        else{
            for(Requisition requisition : arrayList) {
                if(requisition.getId().toLowerCase(Locale.getDefault()).contains(charText) ||
                        requisition.getDate().toLowerCase(Locale.getDefault()).contains(charText)){
                    requisitionlList.add(requisition);
                }
            }
        }
        notifyDataSetChanged();
    }
}
