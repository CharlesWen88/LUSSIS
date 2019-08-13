package sg.edu.nus.lussis.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.model.RequisitionDetailDTO;

public class DisbursementItemDetailsListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<RequisitionDetailDTO> detailsList;

    public DisbursementItemDetailsListViewAdapter(Context mContext, List<RequisitionDetailDTO> detailsList) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.detailsList = detailsList;
    }

    public class ViewHolder{
        TextView tvId, tvName, tvQuantity;
    }

    @Override
    public int getCount() { return detailsList.size(); }

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
        DisbursementItemDetailsListViewAdapter.ViewHolder holder;
        if (convertView==null){
            holder = new DisbursementItemDetailsListViewAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.disbursement_item_details_list_content, null);

            //locate the views in xml file
            holder.tvId = convertView.findViewById(R.id.id);
            holder.tvName = convertView.findViewById(R.id.name);
            holder.tvQuantity = convertView.findViewById(R.id.quantity);

            convertView.setTag(holder);
        }
        else {
            holder = (DisbursementItemDetailsListViewAdapter.ViewHolder)convertView.getTag();
        }

        //set the results into textViews
        holder.tvId.setText(String.valueOf(position+1));
        holder.tvName.setText(detailsList.get(position).getRequisition().getEmployee().getName());
        holder.tvQuantity.setText(detailsList.get(position).getQuantityDelivered());

        return convertView;
    }
}
