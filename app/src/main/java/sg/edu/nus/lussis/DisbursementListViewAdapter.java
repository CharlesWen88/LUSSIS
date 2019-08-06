package sg.edu.nus.lussis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sg.edu.nus.lussis.Model.Disbursement;
import sg.edu.nus.lussis.Model.Requisition;

public class DisbursementListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<Disbursement> disbursementList;
    private ArrayList<Disbursement> arrayList;

    public DisbursementListViewAdapter(Context context, List<Disbursement> disbursementList) {
        mContext = context;
        this.disbursementList = disbursementList;
        inflater = LayoutInflater.from(mContext);
        arrayList = new ArrayList<>();
        arrayList.addAll(disbursementList);
    }

    public class ViewHolder{
        TextView tvLocation, tvDate, tvStatus;
    }

    @Override
    public int getCount() {
        return disbursementList.size();
    }

    @Override
    public Object getItem(int position) {
        return disbursementList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        convertView = inflater.inflate(R.layout.disbursement_list_content, parent,false);

        //locate the views in xml file
        holder.tvLocation = convertView.findViewById(R.id.requisition_order);
        holder.tvDate = convertView.findViewById(R.id.requisition_date);
        holder.tvStatus = convertView.findViewById(R.id.requisition_status);

        convertView.setTag(holder);

        //set the results into textViews
        holder.tvLocation.setText(disbursementList.get(position).getCollectionPoint());
        holder.tvDate.setText(disbursementList.get(position).getDeliveryDateTime());
        holder.tvStatus.setText(disbursementList.get(position).getRequisitionDetails().get(0).getStatus());

        return convertView;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        disbursementList.clear();
        if (charText.length()==0){
            disbursementList.addAll(arrayList);
        }
        else{
            for(Disbursement disbursement : arrayList) {
                if(disbursement.getDeliveryDateTime().toLowerCase(Locale.getDefault()).contains(charText)){
                    disbursementList.add(disbursement);
                }
            }
        }
        notifyDataSetChanged();
    }
}
