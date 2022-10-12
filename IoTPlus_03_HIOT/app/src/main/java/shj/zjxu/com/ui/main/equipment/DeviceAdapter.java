package shj.zjxu.com.ui.main.equipment;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import shj.zjxu.com.R;
import shj.zjxu.com.entity.HolderDeviceEntity;
import shj.zjxu.com.http.HttpService;


public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>
        implements View.OnClickListener {
    private final Context mContext;
    private final ArrayList<HolderDeviceEntity> mData;
    private OnItemClickListener onItemClickListener;

    public DeviceAdapter(Context context){
        this.mContext = context;
        this.mData = new ArrayList<HolderDeviceEntity>();
    }

    @NotNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.adapter_device_item,parent,false);
        view.setOnClickListener(this);
        return new DeviceViewHolder(view);
    }


    @Override
    public void onBindViewHolder( @NotNull DeviceAdapter.DeviceViewHolder holder, int position) {
         //设置数据
        holder.itemView.setTag(position);
        HolderDeviceEntity deviceEntity = mData.get(position);
        holder.tv_name.setText(deviceEntity.getTitle());
        holder.tv_desc.setText(deviceEntity.getDescription());
       Glide.with(mContext)
              //  .load(HttpService.IMAGE_BASE_URL + deviceEntity.getDeviceimg())
                .load(deviceEntity.getDeviceimg())
              // .error(R.drawable.image_error)
                .into(holder.iv_image);

    }
/*这里出现问题，into无法使用，需要自定义才能使用*/
    private void into(ImageView iv_image) {
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }
    @Override
    public void onClick(View v) {
        if (onItemClickListener != null){
            int pos = (int) v.getTag();
            HolderDeviceEntity deviceEntity = mData.get(pos);
            onItemClickListener.onItemClick(pos,deviceEntity);
        }
    }

    public void resetDataAndNotifyDataSetChanged(List<HolderDeviceEntity> data){
        if (data != null && data.size() != 0){
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }



    public class DeviceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_image)   ImageView iv_image;
        @BindView(R.id.tv_name) TextView tv_name;
        @BindView(R.id.tv_desc) TextView tv_desc;


        public DeviceViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener =  listener;
    }
    public interface OnItemClickListener{
        void onItemClick (int position, HolderDeviceEntity item);
    }

}
