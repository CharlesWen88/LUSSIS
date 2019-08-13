package sg.edu.nus.lussis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.model.DisbursementDTO;

public class StoreDisbursementListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<DisbursementDTO> disbursementList;
    private ArrayList<DisbursementDTO> arrayList;

    public StoreDisbursementListViewAdapter(Context context, List<DisbursementDTO> disbursementList) {
        mContext = context;
        this.disbursementList = disbursementList;
        inflater = LayoutInflater.from(mContext);
        arrayList = new ArrayList<>();
        arrayList.addAll(disbursementList);
    }

    public class ViewHolder{
        TextView tvDepartment, tvLocation;
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
        convertView = inflater.inflate(R.layout.store_disbursement_list_content, parent,false);

        //locate the views in xml file
        holder.tvDepartment = convertView.findViewById(R.id.department);
        holder.tvLocation = convertView.findViewById(R.id.location);

        convertView.setTag(holder);

        //set the results into textViews
        holder.tvDepartment.setText("Department of " + disbursementList.get(position).getDepartmentName());
        holder.tvLocation.setText(disbursementList.get(position).getCollectionPoint());

        return convertView;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        disbursementList.clear();
        if (charText.length()==0){
            disbursementList.addAll(arrayList);
        }
        else{
            for(DisbursementDTO disbursement : arrayList) {
                if(disbursement.getDepartmentName().toLowerCase(Locale.getDefault()).contains(charText)
                    || disbursement.getCollectionPoint().toLowerCase(Locale.getDefault()).contains(charText)){
                    disbursementList.add(disbursement);
                }
            }
        }
        notifyDataSetChanged();
    }
}
