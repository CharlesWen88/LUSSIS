package sg.edu.nus.lussis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sg.edu.nus.lussis.model.RequisitionDetails;
import sg.edu.nus.lussis.R;

public class PendingRequisitionDetailsListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<RequisitionDetails> detailsList;

    public PendingRequisitionDetailsListViewAdapter(Context mContext, List<RequisitionDetails> detailsList) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.detailsList = detailsList;
    }

    public class ViewHolder{
        TextView tvSN, tvName, tvQuantity;
    }

    @Override
    public int getCount() {
        return detailsList.size();
    }

    @Override
    public Object getItem(int position) {
        return detailsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PendingRequisitionDetailsListViewAdapter.ViewHolder holder;
        if (convertView==null){
            holder = new PendingRequisitionDetailsListViewAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.disbursement_details_list_content, null);

            //locate the views in xml file
            holder.tvSN = convertView.findViewById(R.id.dd_sn);
            holder.tvName = convertView.findViewById(R.id.dd_name);
            holder.tvQuantity = convertView.findViewById(R.id.dd_quantity);

            convertView.setTag(holder);
        }
        else {
            holder = (PendingRequisitionDetailsListViewAdapter.ViewHolder)convertView.getTag();
        }

        //set the results into textViews
        holder.tvSN.setText(String.valueOf(position+1));
        holder.tvName.setText(detailsList.get(position).getStationery().getDescription());
        holder.tvQuantity.setText(detailsList.get(position).getQuantityOrdered());

        return convertView;
    }
}
