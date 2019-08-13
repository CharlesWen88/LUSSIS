package sg.edu.nus.lussis.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import sg.edu.nus.lussis.R;
import sg.edu.nus.lussis.model.RequisitionDetailDTO;

public class StoreDisbursementDetailsListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<RequisitionDetailDTO> detailsList;

    public StoreDisbursementDetailsListViewAdapter(Context mContext, List<RequisitionDetailDTO> detailsList) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.detailsList = detailsList;
    }

    public class ViewHolder{
        TextView tvSN, tvName, tvQuantity;
        EditText etQuantity;
        private MutableWatcher mWatcher;
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
        StoreDisbursementDetailsListViewAdapter.ViewHolder holder;
        if (convertView==null){
            holder = new StoreDisbursementDetailsListViewAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.retrieval_list_content, null);

            //locate the views in xml file
            holder.tvSN = convertView.findViewById(R.id.bin);
            holder.tvName = convertView.findViewById(R.id.name);
            holder.tvQuantity = convertView.findViewById(R.id.qty_needed);
            holder.mWatcher = new MutableWatcher();
            holder.etQuantity = convertView.findViewById(R.id.qty_retrieved);

            convertView.setTag(holder);
        }
        else {
            holder = (StoreDisbursementDetailsListViewAdapter.ViewHolder)convertView.getTag();
        }

        //set the results into textViews
        holder.tvSN.setText(String.valueOf(position+1));
        holder.tvName.setText(detailsList.get(position).getStationery().getDescription());
        holder.tvQuantity.setText(detailsList.get(position).getQuantityDelivered());
        holder.mWatcher.setActive(false);
        holder.etQuantity.setText(detailsList.get(position).getQuantityDelivered());
        holder.mWatcher.setPosition(position);
        holder.mWatcher.setActive(true);
        holder.etQuantity.addTextChangedListener(holder.mWatcher);

        return convertView;
    }

    class MutableWatcher implements TextWatcher {

        private int mPosition;
        private boolean mActive;

        void setPosition(int position) {
            mPosition = position;
        }

        void setActive(boolean active) {
            mActive = active;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            if(mActive)
                detailsList.get(mPosition).setQuantityDelivered(s.toString());
        }
    }
}