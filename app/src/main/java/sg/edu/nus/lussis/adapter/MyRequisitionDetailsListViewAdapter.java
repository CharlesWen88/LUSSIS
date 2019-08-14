package sg.edu.nus.lussis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import sg.edu.nus.lussis.model.RequisitionDetailDTO;
import sg.edu.nus.lussis.R;

public class MyRequisitionDetailsListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<RequisitionDetailDTO> detailsList;

    public MyRequisitionDetailsListViewAdapter(Context mContext, List<RequisitionDetailDTO> detailsList) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.detailsList = detailsList;
    }

    public class ViewHolder{
        TextView tvName, tvQuantity, tvStatus;
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
        ViewHolder holder;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.requisition_details_list_content, null);

            //locate the views in xml file
            holder.tvName = convertView.findViewById(R.id.rd_name);
            holder.tvQuantity = convertView.findViewById(R.id.rd_quantity);
            holder.tvStatus = convertView.findViewById(R.id.rd_status2);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        //set the results into textViews
        holder.tvName.setText(detailsList.get(position).getStationery().getDescription());
        holder.tvQuantity.setText(detailsList.get(position).getQuantityOrdered());

        String status;
        switch (detailsList.get(position).getStatus()){
            case "PREPARING":
                status = "Preparing";
                break;
            case "WAITLIST_APPROVED":
                status = "Waitlisted";
                break;
            case "PENDING_COLLECTION":
                status = "Pending";
                break;
            case "CANCELLED":
                status = "Cancelled";
                break;
            case "REJECTED":
                status = "Rejected";
                break;
            case "COLLECTED":
                status = "Fulfilled";
                break;
            case "WAITLIST_PENDING":
                status = "Waitlist Pending";
                break;
            default:
                status = "Reserved Pending";
        }

        holder.tvStatus.setText(status);

        return convertView;
    }
}
