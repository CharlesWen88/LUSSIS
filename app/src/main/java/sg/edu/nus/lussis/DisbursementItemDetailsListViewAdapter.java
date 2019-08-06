package sg.edu.nus.lussis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DisbursementItemDetailsListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;


    public class ViewHolder{
        TextView tvId, tvName, tvQuantity;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
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
//        holder.tvId.setText(String.valueOf(position+1));
//        holder.tvName.setText(detailsList.get(position).getStationery().getDescription());
//        holder.tvQuantity.setText(detailsList.get(position).getQuantityDelivered());

        return convertView;
    }
}
