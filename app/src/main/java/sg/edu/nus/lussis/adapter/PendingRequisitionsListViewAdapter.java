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
import sg.edu.nus.lussis.model.RequisitionDTO;

public class PendingRequisitionsListViewAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<RequisitionDTO> requisitionList;
    private ArrayList<RequisitionDTO> arrayList;

    public PendingRequisitionsListViewAdapter(Context mContext, List<RequisitionDTO> requisitionList) {
        this.mContext = mContext;
        this.requisitionList = requisitionList;
        inflater = LayoutInflater.from(mContext);
        arrayList = new ArrayList<>();
        arrayList.addAll(requisitionList);
    }

    public class ViewHolder{
        TextView tvId, tvDate, tvName;
    }

    @Override
    public int getCount() {
        return requisitionList.size();
    }

    @Override
    public Object getItem(int position) {
        return requisitionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PendingRequisitionsListViewAdapter.ViewHolder holder;
        //       if (view==null){
        holder = new PendingRequisitionsListViewAdapter.ViewHolder();
        view = inflater.inflate(R.layout.pending_requisitions_list_content, null);

        //locate the views in xml file
        holder.tvId = view.findViewById(R.id.requisition_order);
        holder.tvDate = view.findViewById(R.id.requisition_date);
        holder.tvName = view.findViewById(R.id.requisition_name);

        view.setTag(holder);

//        }
//        else {
//            holder = (ViewHolder)view.getTag();
//        }

        //set the results into textViews
        holder.tvId.setText(requisitionList.get(position).getId());
        holder.tvDate.setText(requisitionList.get(position).getDateTime());
        holder.tvName.setText(requisitionList.get(position).getStatus());

        return view;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        requisitionList.clear();
        if (charText.length()==0){
            requisitionList.addAll(arrayList);
        }
        else{
            for(RequisitionDTO requisition : arrayList) {
                if(requisition.getId().toLowerCase(Locale.getDefault()).contains(charText) ||
                        requisition.getDateTime().toLowerCase(Locale.getDefault()).contains(charText)){
                    requisitionList.add(requisition);
                }
            }
        }
        notifyDataSetChanged();
    }
}
